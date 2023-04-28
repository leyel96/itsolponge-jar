package com.solponge.domain.payment;

public class OutOfStockException extends RuntimeException {
    public OutOfStockException(String m){
        super(m);
    }
}
