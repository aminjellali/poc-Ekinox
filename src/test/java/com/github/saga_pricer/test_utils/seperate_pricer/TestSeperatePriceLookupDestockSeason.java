package com.github.saga_pricer.test_utils.seperate_pricer;

import com.github.saga_pricer.abstractions.PriceLookup;
import com.github.saga_pricer.test_utils.DvdInfo;

public class TestSeperatePriceLookupDestockSeason implements PriceLookup<DvdInfo> {
    @Override
    public Double getPrice(DvdInfo saga) {
        return 5.;
    }
}
