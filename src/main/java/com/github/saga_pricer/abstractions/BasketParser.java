package com.github.saga_pricer.abstractions;

import java.util.List;

/**
 * The interface describing the logic of how the input text should be parsed.
 * @param <T> In the basket items are meant to be objects rather than simple String.
 */
public interface BasketParser<T>{
    /**
     *  Provided with a basket text, this function
     *  will apply the implemented logic in order to provide a List of
     *  exploitable objects.
     * @param basket the customers purchase items text.
     * @return List with T the instance of the describing object.
     * for an example
     * @see com.github.saga_pricer.basic_impl.BasicBasketParserImpl
     */
    List<T> parseBasket(String basket);
}
