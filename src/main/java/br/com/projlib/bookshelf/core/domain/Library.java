package br.com.projlib.bookshelf.core.domain;

import lombok.Value;

@Value
public class Library {
    long id;
    String name;
    boolean active;
    Institution institution;
}
