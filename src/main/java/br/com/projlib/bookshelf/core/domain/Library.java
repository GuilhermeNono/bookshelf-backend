package br.com.projlib.bookshelf.core.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
public class Library {
    private long id;
    private String name;
    private boolean active;
    private Institution institution;
}
