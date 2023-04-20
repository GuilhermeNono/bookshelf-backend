package br.com.projlib.bookshelf.core.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Contact {
    private long id;
    private boolean active;
    private String contact;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String type;
}
