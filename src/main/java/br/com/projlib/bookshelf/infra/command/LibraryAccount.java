package br.com.projlib.bookshelf.infra.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LibraryAccount {
    private long libUserId;
    private long libId;
}
