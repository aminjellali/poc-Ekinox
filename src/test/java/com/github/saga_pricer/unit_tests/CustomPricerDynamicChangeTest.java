package com.github.saga_pricer.unit_tests;

import com.github.saga_pricer.abstractions.AbstractPricer;
import com.github.saga_pricer.exceptions.BasketCannotBeNullOrEmptyException;
import com.github.saga_pricer.test_utils.DvdInfo;
import com.github.saga_pricer.test_utils.seperate_pricer.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomPricerDynamicChangeTest {
    TestSeperateBasketParser parser = new TestSeperateBasketParser();
    TestSeperatePriceLookup searcher = new TestSeperatePriceLookup();
    TestSeperateReductionComputer reductionComputer = new TestSeperateReductionComputer();
    TestSeperateReductionComputerNoReduction noReductionComputer= new TestSeperateReductionComputerNoReduction();
    TestSeperatePriceLookupDestockSeason destockSeason = new TestSeperatePriceLookupDestockSeason();
    TestSeperateFailingBasketParser serverDownParser = new TestSeperateFailingBasketParser();
    @Test
    @DisplayName("Out of season reduction dynamic change on season should return 75 off season should return 100")
    void SingletonProvidesCustomImplSeperatedWithCorrespondingClassType() throws BasketCannotBeNullOrEmptyException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
       String basket = "[{\"family\":\"The Lord Of The Rings\", \"name\":\"The Lord Of The Rings the ring master\", \"price\": 20},\n" +
               "{\"family\":\"The Lord Of The Rings\", \"name\":\"The Lord Of The Rings the return of the devil\", \"price\": 30},\n" +
               "{\"family\":\"Harry Potter\", \"name\":\"potter and the seven sees\", \"price\":50}]";
        AbstractPricer<DvdInfo> pricer = new TestPricerSeperateBehavioursImpl(parser, reductionComputer, searcher);
        assertEquals(75.0d, pricer.getTotalPrice(basket));
        pricer.setReductionComputer(noReductionComputer);
        assertEquals(100.0d, pricer.getTotalPrice(basket));
        pricer.setPriceLookup(destockSeason);
        assertEquals(15.0d, pricer.getTotalPrice(basket));
        pricer.setPriceLookup(destockSeason);
        assertEquals(15.0d, pricer.getTotalPrice(basket));
        pricer.setBasketParser(serverDownParser);
        assertThrows(ServerDownException.class,() ->  pricer.getTotalPrice(basket));
    }
}
