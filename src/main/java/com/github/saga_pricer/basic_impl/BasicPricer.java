package com.github.saga_pricer.basic_impl;

import com.github.saga_pricer.abstractions.AbstractPricer;
import com.github.saga_pricer.abstractions.BasketParser;
import com.github.saga_pricer.abstractions.PriceLookup;
import com.github.saga_pricer.abstractions.ReductionComputer;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 */
public class BasicPricer extends AbstractPricer<String> {

    protected static final String BACK_TO_THE_FUTURE = "BACK TO THE FUTURE";
    public BasicPricer(BasketParser<String> basketParser, ReductionComputer<String> reductionComputer, PriceLookup<String> priceLookup) {
        super(basketParser, reductionComputer, priceLookup);
    }

    private Map<String, Integer> aggregateTitles(List<String> titles) {
        return titles.stream().collect(Collectors.toMap(
                title -> {
                    if (title.toUpperCase(Locale.ROOT).contains(BACK_TO_THE_FUTURE)) {
                        return BACK_TO_THE_FUTURE;
                    }
                    return title;
                },
                value -> 1,
                (oldValue, newValue) -> oldValue + newValue
        ));
    }
    public Double computeBasketPrice(List<String> basket) {
        Map<String, Integer> basketAggregator = aggregateTitles(basket);
        return basketAggregator.entrySet().stream().reduce(
                0.0, (partialPrice, entry) -> {
                    Double reduction = reductionComputer.getReduction(entry.getValue(), entry.getKey());
                    return priceLookup.getPrice(entry.getKey()) * entry.getValue() * (1 - reduction) + partialPrice;
                }
                , Double::sum);
    }
}
