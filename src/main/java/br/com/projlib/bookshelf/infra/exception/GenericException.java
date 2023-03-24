package br.com.projlib.bookshelf.infra.exception;

public class GenericException extends RuntimeException{
    public GenericException(String messageCode) {
        super(messageCode);
    }

    public GenericException(String messageCode, Object[] objects) {
        super("Translator.toLocale(messageCode, objects)");
    }
}
