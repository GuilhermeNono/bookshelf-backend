package br.com.projlib.bookshelf.infra.command;

import br.com.projlib.bookshelf.infra.gateway.libraryjpa.LibraryJpa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class LibraryUserInfo {

    long id;
    String name;
}
