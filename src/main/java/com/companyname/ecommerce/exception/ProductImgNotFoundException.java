package com.companyname.ecommerce.exception;

public class ProductImgNotFoundException extends RuntimeException {
    public ProductImgNotFoundException() {
        super("Product's image is not available.");
    }

    public ProductImgNotFoundException(String message) {
        super(message);
    }
}
