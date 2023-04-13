package br.com.projlib.bookshelf.core.domain;

import lombok.Value;

@Value
public class Institution {
    long id;
    String name;
    String cnpj;
}
