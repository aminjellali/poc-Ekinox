package com.github.saga_pricer.unit_tests.basic_impl;

import com.github.saga_pricer.abstractions.AbstractPricer;
import com.github.saga_pricer.PricerSingleton;
import com.github.saga_pricer.exceptions.BasketCannotBeNullOrEmptyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

class BasicPricerImplTest {

    AbstractPricer pricerInstance = PricerSingleton.getBasicPricer();

    @DisplayName("Nominal cases a price is expected.")
    @ParameterizedTest
    @MethodSource("com.github.saga_pricer.test_utils.PricerTestCasesProvider#nominalTestCases")
    void nominalPricerTest(String basketMock, Double expectedOutput) {
        Double actualPriceToPay = pricerInstance.getTotalPrice(basketMock);
        assertEquals(expectedOutput, actualPriceToPay);
    }


    @DisplayName("Expect an exception will be thrown as basket is empty.")
    @Test
    void throwsExceptionOnEmptyBasket() {
        Exception exception = assertThrows(BasketCannotBeNullOrEmptyException.class, () -> {
            pricerInstance.getTotalPrice("");
        });
        String expectedMessage = "Basket argument can neither be null or empty exception";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @DisplayName("Expect an exception will be thrown as basket is null.")
    @Test
    void throwsExceptionOnNullBasket() {
        Exception exception = assertThrows(BasketCannotBeNullOrEmptyException.class, () -> {
            pricerInstance.getTotalPrice(null);
        });
        String expectedMessage = "Basket argument can neither be null or empty exception";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }


}
