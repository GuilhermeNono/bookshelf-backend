package br.com.projlib.bookshelf.core.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
public class UserAccount {
    private long id;
    private String cpf;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String personName;
    private Date birthDay;
    private Profile profile;
    private List<Contact> contacts;
    private List<Password> passwords;
}
