package com.companyname.ecommerce.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super("Product is not available.");
    }
    public ProductNotFoundException(String msg) {
        super(msg);
    }
}
