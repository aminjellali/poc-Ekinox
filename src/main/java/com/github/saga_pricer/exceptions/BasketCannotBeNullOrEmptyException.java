package com.github.saga_pricer.exceptions;

/**
 * Thrown when a provided text input in either null or empty as this is considered as an illegal
 * argument.
 */
public class BasketCannotBeNullOrEmptyException extends IllegalArgumentException{
    public  BasketCannotBeNullOrEmptyException(){
        super("Basket argument can neither be null or empty exception");
    }
}
