package com.github.saga_pricer.abstractions;

/**
 * Reductions could be seasonal or even evolve to better deals
 * we externalised into this interface that will allow you to
 * change it when ever you wish.
 * @param <T> The products type.
 */
public interface ReductionComputer<T> {
    /**
     * Get the reduction that has to be applied to the purchased item.
     *
     * @param numberOfItemsPurchased Optional in case product reduction depends on the number of purchased items.
     * @param saga Provided in case the reduction is based on the products' nature.
     * @return Between 0 and 1 the reduction to be applied.
     */
    Double getReduction(Integer numberOfItemsPurchased, T saga);
}
