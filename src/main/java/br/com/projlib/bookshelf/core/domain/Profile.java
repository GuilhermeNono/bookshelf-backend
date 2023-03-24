package br.com.projlib.bookshelf.core.domain;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class Profile {
    long id;
    String personName;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

}
