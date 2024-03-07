package com.github.saga_pricer.test_utils.seperate_pricer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.saga_pricer.abstractions.BasketParser;
import com.github.saga_pricer.test_utils.DvdInfo;

import java.util.List;

public class TestSeperateBasketParser implements BasketParser<DvdInfo> {
    @Override
    public List<DvdInfo> parseBasket(String basketJson) {
        ObjectMapper om = new ObjectMapper();
        try {
            return om.readValue(basketJson, new TypeReference<List<DvdInfo>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse provided json.");
        }
    }
}
