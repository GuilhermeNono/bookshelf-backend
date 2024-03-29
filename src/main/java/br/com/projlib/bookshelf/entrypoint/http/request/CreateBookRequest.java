package br.com.projlib.bookshelf.entrypoint.http.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CreateBookRequest {
    @NotBlank
    String name;
    @NotBlank
    String language;
    @NotNull
    Date publicationDate;
    @NotBlank
    String isbn;
    @NotBlank
    String sinopse;
    @NotBlank
    String edition;
    @NotBlank
    String capeType;
    @NotNull
    Integer numberPages;
    @NotBlank
    String cape;
    @NotNull
    PublisherRequest publisher;
    @NotEmpty
    List<CategoryRequest> categories;
    @NotEmpty
    List<AuthorRequest> authors;
}
