package br.com.projlib.bookshelf.entrypoint.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonPropertyOrder({"name", "publisherName", "edition", "sinopse", "language", "publication_date", "isbn", "number_pages", "cape", "categories", "authors"})
public class ListBooksResponse {

    private String name;
    private String language;
    private List<AuthorResponse> authors;
    private List<CategoryResponse> categories;
    @JsonProperty("publication_date")
    private Date publicationDate;
    @JsonProperty("publisher")
    private String publisherName;
    private String sinopse;
    private String isbn;
    private String edition;
    @JsonProperty("number_pages")
    private int numberPages;
    private String cape;

}
