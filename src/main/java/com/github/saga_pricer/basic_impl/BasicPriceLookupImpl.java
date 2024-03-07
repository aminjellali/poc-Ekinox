package com.github.saga_pricer.basic_impl;

import com.github.saga_pricer.abstractions.PriceLookup;

/**
 * Basic implementation of the price searcher.
 */
public class BasicPriceLookupImpl implements PriceLookup<String> {
    private static final String BACK_TO_THE_FUTURE = "BACK TO THE FUTURE";

    /**
     * If the DVD is from the back to the future saga meaning
     * after collection if the saga name is BACK TO THE FUTURE
     * the price will be 15 else 20.
     * @param saga The product in question.
     * @return
     */
    @Override
    public Double getPrice(String saga) {
        if(BACK_TO_THE_FUTURE.equals(saga)){
            return 15.;
        }
        return 20.;
    }
}
