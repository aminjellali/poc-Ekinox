package com.github.saga_pricer.test_utils.seperate_pricer;

import com.github.saga_pricer.abstractions.AbstractPricer;
import com.github.saga_pricer.abstractions.BasketParser;
import com.github.saga_pricer.abstractions.PriceLookup;
import com.github.saga_pricer.abstractions.ReductionComputer;
import com.github.saga_pricer.test_utils.DvdInfo;

import java.util.List;

public class TestPricerSeperateBehavioursImpl extends AbstractPricer<DvdInfo> {
    public TestPricerSeperateBehavioursImpl(BasketParser<DvdInfo> basketParser,
                                               ReductionComputer<DvdInfo> reductionComputer,
                                               PriceLookup<DvdInfo> priceLookup) {
        super(basketParser, reductionComputer, priceLookup);
    }

    @Override
    protected Double computeBasketPrice(List<DvdInfo> basket) {
        Double price = 0.;
        for (DvdInfo dvd: basket) {
            price += priceLookup.getPrice(dvd) * (1 - reductionComputer.getReduction(null, dvd));
        }
        return price;
    }
}
