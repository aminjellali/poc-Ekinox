package com.github.saga_pricer.basic_impl;

import com.github.saga_pricer.abstractions.ReductionComputer;

/**
 * Basic implementation of the reduction computer
 */
public class BasicReductionComputerImpl implements ReductionComputer<String> {
    private static final String BACK_TO_THE_FUTURE = "BACK TO THE FUTURE";

    /**
     * Will compute the reduction to be applied based on the provided input.
     * rule is as follows <br>
     * <ul>
     *     <li> item(s) not from saga movie or is from saga but only one item is purchased = 0</li>
     *     <li> item(s) from saga movie =>
     *         <ul>
     *             <li> two items are purchased = 0.1 </li>
     *             <li> more than two items are purchased = 0.2</li>
     *         </ul>
     *         </li>
     * </ul>
     * @param numberOfItemsPurchased Optional in case product reduction depends on the number of purchased items.
     * @param saga Provided in case the reduction is based on the products' nature.
     * @return reduction amount between 0 - 0.2
     */
    @Override
    public Double getReduction(Integer numberOfItemsPurchased, String saga) {
        boolean isEligible = isEligeable(saga);
        if (!isEligible || numberOfItemsPurchased <= 1) {
            return 0.;
        } else if (numberOfItemsPurchased == 2) {
            return 0.1;
        }
        return 0.2;
    }

    private boolean isEligeable(String name){
        return BACK_TO_THE_FUTURE.equals(name);
    }
}
