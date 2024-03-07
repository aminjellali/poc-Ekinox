package com.github.saga_pricer.test_utils.seperate_pricer;

public class ServerDownException extends RuntimeException{
    public ServerDownException(){
        super("Server is temporally down.");
    }
}
