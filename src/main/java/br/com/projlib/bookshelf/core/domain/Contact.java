package br.com.projlib.bookshelf.core.domain;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class Contact {
    long id;
    boolean active;
    String contact;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    String type;
}
