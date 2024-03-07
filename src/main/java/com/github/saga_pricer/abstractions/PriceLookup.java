package com.github.saga_pricer.abstractions;

/**
 * As database pricing systems are different once implemented this
 * class will provide a tool to get a products price from any
 * wished source.
 * @param <T> The wrapper of the product.
 */
public interface PriceLookup<T> {
    /**
     * Provided with the product object or key this function will search in
     * any part of your system that provides the products price.
     * @param saga The product in question.
     * @return The price of the provided product.
     */
    Double getPrice(T saga);
}
