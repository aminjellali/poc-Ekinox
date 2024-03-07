package com.github.saga_pricer.integration_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.saga_pricer.abstractions.AbstractPricer;
import com.github.saga_pricer.PricerSingleton;
import com.github.saga_pricer.exceptions.BasketCannotBeNullOrEmptyException;

import com.github.saga_pricer.test_utils.DvdInfo;
import com.github.saga_pricer.test_utils.TestPricerImpl;
import com.github.saga_pricer.test_utils.seperate_pricer.TestPricerSeperateBehavioursImpl;
import com.github.saga_pricer.test_utils.seperate_pricer.TestSeperateBasketParser;
import com.github.saga_pricer.test_utils.seperate_pricer.TestSeperatePriceLookup;
import com.github.saga_pricer.test_utils.seperate_pricer.TestSeperateReductionComputer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

class PricerSingletonTest {

    @BeforeEach
    void init() throws NoSuchFieldException, IllegalAccessException {
        // only allowed in test context as the instance is already set to an instance.
        Field pricer = PricerSingleton.class.getDeclaredField("pricerInstance");
        pricer.setAccessible(true);
        pricer.set(PricerSingleton.class, null);
        pricer.setAccessible(false);
    }

    @ParameterizedTest
    @ValueSource(strings = {"[{\"family\":\"The Lord Of The Rings\", \"name\":\"The Lord Of The Rings the ring master\", \"price\": 20},\n" +
            "{\"family\":\"The Lord Of The Rings\", \"name\":\"The Lord Of The Rings the return of the devil\", \"price\": 30},\n" +
            "{\"family\":\"Harry Potter\", \"name\":\"potter and the seven sees\", \"price\":50}]"})
    @DisplayName("Singleton pricer should provide the new implementation dynamically and should output 75 as price.")
    void SingletonProvidesCustomImplWithCorrespondingClassType(String basket) throws BasketCannotBeNullOrEmptyException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        AbstractPricer<DvdInfo> pricer = (AbstractPricer<DvdInfo>) PricerSingleton.getCustomPricerWithInnerImpl(TestPricerImpl.class);
        assertEquals("TestPricerImpl", pricer.getClass().getSimpleName());
        assertEquals(75.0d, pricer.getTotalPrice(basket));
    }

    @Test
    @DisplayName("Singleton provide basic and price to 20.")
    void testGetBasicPricer() throws BasketCannotBeNullOrEmptyException {
        AbstractPricer actualBasicPricer = PricerSingleton.getBasicPricer();
        assertEquals(20.0d, actualBasicPricer.getTotalPrice("Basket"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"[{\"family\":\"The Lord Of The Rings\", \"name\":\"The Lord Of The Rings the ring master\", \"price\": 20},\n" +
            "{\"family\":\"The Lord Of The Rings\", \"name\":\"The Lord Of The Rings the return of the devil\", \"price\": 30},\n" +
            "{\"family\":\"Harry Potter\", \"name\":\"potter and the seven sees\", \"price\":50}]"})
    @DisplayName("Singleton with separate implementations should provide the new implementation dynamically and should output 75 as price.")
    void SingletonProvidesCustomImplSeperatedWithCorrespondingClassType(String basket) throws BasketCannotBeNullOrEmptyException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        TestSeperateBasketParser parser = new TestSeperateBasketParser();
        TestSeperatePriceLookup searcher = new TestSeperatePriceLookup();
        TestSeperateReductionComputer reductionComputer = new TestSeperateReductionComputer();
        AbstractPricer<DvdInfo> pricer = (AbstractPricer<DvdInfo>) PricerSingleton.getCustomPricer(
                TestPricerSeperateBehavioursImpl.class, parser, reductionComputer, searcher);
        assertEquals("TestPricerSeperateBehavioursImpl", pricer.getClass().getSimpleName());
        assertEquals(75.0d, pricer.getTotalPrice(basket));
    }

}
