package com.github.saga_pricer.test_utils.seperate_pricer;

import com.github.saga_pricer.abstractions.ReductionComputer;
import com.github.saga_pricer.test_utils.DvdInfo;

public class TestSeperateReductionComputerNoReduction implements ReductionComputer<DvdInfo> {
    @Override
    public Double getReduction(Integer numberOfItemsPurchased, DvdInfo saga) {
        return 0.;
    }
}
