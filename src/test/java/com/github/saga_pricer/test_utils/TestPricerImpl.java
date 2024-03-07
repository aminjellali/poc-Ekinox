package com.github.saga_pricer.test_utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.saga_pricer.abstractions.AbstractPricer;
import com.github.saga_pricer.abstractions.BasketParser;
import com.github.saga_pricer.abstractions.PriceLookup;
import com.github.saga_pricer.abstractions.ReductionComputer;

import java.util.List;

public class TestPricerImpl extends AbstractPricer<DvdInfo> {

    public TestPricerImpl() {
        super(
                new BasketParser<DvdInfo>(){

            @Override
            public List<DvdInfo> parseBasket(String basketJson) {
                ObjectMapper om = new ObjectMapper();
                try {
                    return om.readValue(basketJson, new TypeReference<List<DvdInfo>>(){});
                } catch (JsonProcessingException e) {
                    throw new RuntimeException("Failed to parse provided json.");
                }
            }
        } ,
                new ReductionComputer<DvdInfo>(){

                    @Override
                    public Double getReduction(Integer numberOfItemsPurchased, DvdInfo saga) {
                        if(saga.family.equals("The Lord Of The Rings")){
                            return 0.5;
                        }
                        return 0.;
                    }
                }, new PriceLookup<DvdInfo>(){

                    @Override
                    public Double getPrice(DvdInfo saga) {
                        return saga.price;
                    }
                });
    }

    @Override
    protected Double computeBasketPrice(List<DvdInfo> basket) {
        Double price = 0.;
        for (DvdInfo dvd: basket) {
            price += priceLookup.getPrice(dvd) * (1 - reductionComputer.getReduction(null, dvd));
        }
        return price;
    }
}
