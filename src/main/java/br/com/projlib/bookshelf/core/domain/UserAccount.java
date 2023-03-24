package br.com.projlib.bookshelf.core.domain;

import lombok.Value;

import java.time.LocalDateTime;

@Value

public class UserAccount {
    long id;
    String cpf;
    String username;
    String email;
    boolean active;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    Profile profile;
}
