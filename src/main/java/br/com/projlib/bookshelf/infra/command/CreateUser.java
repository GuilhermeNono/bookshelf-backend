package br.com.projlib.bookshelf.infra.command;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CreateUser {

    @NotBlank
    String username;
    @NotBlank
    String password;
    @NotBlank
    String confirmPassword;
    @NotBlank
    String personName;
    @NotNull
    UUID profileId;
    @NotNull
    @NotEmpty
    List<UUID> establishments;
}
