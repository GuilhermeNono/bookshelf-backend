package br.com.projlib.bookshelf.core.domain;

import lombok.Value;

@Value
public class Password {
    long id;
    String password;
    boolean active;
}
