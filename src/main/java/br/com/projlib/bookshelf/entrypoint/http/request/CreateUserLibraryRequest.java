package br.com.projlib.bookshelf.entrypoint.http.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
public class CreateUserLibraryRequest {
    UserAccountCreateRequest account;
    Long accountId;
    @NotEmpty
    @NotBlank
    String rmRa;
    String profilePicture;
    @NotNull
    Long libProfileId;
    @NotNull
    Long libId;
    @NotEmpty
    List<Long> coursesId;
}
