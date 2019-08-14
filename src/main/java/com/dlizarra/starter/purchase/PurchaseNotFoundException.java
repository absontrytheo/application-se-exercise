package com.dlizarra.starter.purchase;

@SuppressWarnings("serial")
public class PurchaseNotFoundException extends RuntimeException {

    public PurchaseNotFoundException(final Integer id) {
        super("Could not find Purchase with id: " + id);
    }
}
