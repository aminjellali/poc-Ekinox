package com.github.saga_pricer.unit_tests.basic_impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.saga_pricer.basic_impl.BasicPriceLookupImpl;
import org.junit.jupiter.api.Test;

class BasicPriceLookupImplTest {
    @Test
    void testGetPrice() {
        assertEquals(20.0d, (new BasicPriceLookupImpl()).getPrice("Saga").doubleValue());
        assertEquals(15.0d, (new BasicPriceLookupImpl()).getPrice((String) "BACK TO THE FUTURE").doubleValue());
    }
}
