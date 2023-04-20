package br.com.projlib.bookshelf.infra.exception;

public class LibraryNotFoundException extends GenericException {

    public LibraryNotFoundException(String message) {
        super(message);
        this.printStackTrace();
    }

    public LibraryNotFoundException() {
        super("Library not found.");
        this.printStackTrace();
    }

}
