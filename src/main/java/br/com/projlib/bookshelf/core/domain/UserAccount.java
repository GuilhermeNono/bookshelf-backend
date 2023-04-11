package br.com.projlib.bookshelf.core.domain;

import lombok.Value;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Value

public class UserAccount {
    long id;
    String cpf;
    boolean active;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    String personName;
    Date birthDay;
    Profile profile;
    List<Contact> contacts;
    List<Password> passwords;
}
