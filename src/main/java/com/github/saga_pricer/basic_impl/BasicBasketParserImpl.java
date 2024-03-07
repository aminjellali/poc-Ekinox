package com.github.saga_pricer.basic_impl;

import com.github.saga_pricer.abstractions.BasketParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

/**
 * A basic parser that takes a basket text and separates it based on return to line.
 * and return the list of the products titles.
 */
public class BasicBasketParserImpl implements BasketParser<String> {
    private static final Logger log = LogManager.getLogger(BasicBasketParserImpl.class);
    private static final String BASKET_SEPARATOR = "\n";

    /**
     * Split input by return to line, will ignore any multiple skip lines
     * and warn.
     * @param basket the customers purchase items text.
     * @return List of basket products names.
     */
    @Override
    public List<String> parseBasket(String basket) {
        log.trace("Parsing the following basket {} with separator {}", basket, BASKET_SEPARATOR);
        return Arrays.stream(basket.split(BASKET_SEPARATOR)).
                filter(title -> {
                    if (title.isBlank()) {
                        log.warn("The file is mal formatted, some empty lines were ignored.");
                        return false;
                    }
                    return true;
                }).toList();
    }
}
