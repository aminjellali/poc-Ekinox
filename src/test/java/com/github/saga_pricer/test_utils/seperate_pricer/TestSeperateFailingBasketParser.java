package com.github.saga_pricer.test_utils.seperate_pricer;

import com.github.saga_pricer.abstractions.BasketParser;
import com.github.saga_pricer.test_utils.DvdInfo;

import java.util.List;

public class TestSeperateFailingBasketParser implements BasketParser<DvdInfo> {
    @Override
    public List<DvdInfo> parseBasket(String basket) {
        throw new ServerDownException();
    }
}
