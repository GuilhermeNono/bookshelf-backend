package br.com.projlib.bookshelf.infra.exception;

public class ProductNotFoundException extends Exception{
    public ProductNotFoundException() {
        super("Product not found.");
    }
}
