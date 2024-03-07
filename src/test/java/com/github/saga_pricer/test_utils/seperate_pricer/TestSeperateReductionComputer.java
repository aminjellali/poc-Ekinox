package com.github.saga_pricer.test_utils.seperate_pricer;

import com.github.saga_pricer.abstractions.ReductionComputer;
import com.github.saga_pricer.test_utils.DvdInfo;

public class TestSeperateReductionComputer implements ReductionComputer<DvdInfo> {
    @Override
    public Double getReduction(Integer numberOfItemsPurchased, DvdInfo saga) {
        if(saga.family.equals("The Lord Of The Rings")){
            return 0.5;
        }
        return 0.;
    }
}
