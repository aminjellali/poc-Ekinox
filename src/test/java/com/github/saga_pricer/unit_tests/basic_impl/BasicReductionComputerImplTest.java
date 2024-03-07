package com.github.saga_pricer.unit_tests.basic_impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.saga_pricer.basic_impl.BasicReductionComputerImpl;
import org.junit.jupiter.api.Test;

class BasicReductionComputerImplTest {
   @Test
    void testGetReduction() {
        assertEquals(0.0d, (new BasicReductionComputerImpl()).getReduction(10, "Saga").doubleValue());
        assertEquals(0.2d, (new BasicReductionComputerImpl()).getReduction(10, "BACK TO THE FUTURE").doubleValue());
        assertEquals(0.0d, (new BasicReductionComputerImpl()).getReduction(1, "BACK TO THE FUTURE").doubleValue());
        assertEquals(0.1d, (new BasicReductionComputerImpl()).getReduction(2, "BACK TO THE FUTURE").doubleValue());
    }
}
