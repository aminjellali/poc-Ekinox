package com.github.saga_pricer.unit_tests.basic_impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.github.saga_pricer.basic_impl.BasicBasketParserImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BasicBasketParserImplTest {
    @Test
    @DisplayName("Basket has one item should return a list with one item.")
    void testBasketWithOneItem() {
        List<String> actualParseBasketResult = (new BasicBasketParserImpl()).parseBasket("Basket");

        assertEquals(1, actualParseBasketResult.size());
        assertEquals("Basket", actualParseBasketResult.get(0));
    }
    @Test
    @DisplayName("Basket has one item should return a list with one item.")
    void testBasketTwoItemsAndConsecutiveSkipLine() {
        List<String> actualParseBasketResult = (new BasicBasketParserImpl()).parseBasket("Basket1\n\nBasket2");

        assertEquals(2, actualParseBasketResult.size());
        assertEquals("Basket1", actualParseBasketResult.get(0));
        assertEquals("Basket2", actualParseBasketResult.get(1));
    }
    @Test
    @DisplayName("Basket has one line separator should be empty.")
    void testParseBasket2() {
        assertTrue((new BasicBasketParserImpl()).parseBasket("\n").isEmpty());
    }

}
