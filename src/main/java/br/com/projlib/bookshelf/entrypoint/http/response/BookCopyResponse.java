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
@JsonPropertyOrder({"lib_code", "name", "cape_type", "available", "language", "publication_date", "publisher", "sinopse", "isbn", "edition", "number_pages", "cape", "author", "categories"})
public class BookCopyResponse {

    @JsonProperty("lib_code")
    private String code;
    @JsonProperty("name")
    private String bookName;
    @JsonProperty("cape_type")
    private String bookCapeType;
    @JsonProperty("available")
    private boolean active;
    @JsonProperty("language")
    private String bookLanguage;
    @JsonProperty("publication_date")
    private Date bookPublicationDate;
    @JsonProperty("publisher")
    private String bookPublisherName;
    @JsonProperty("sinopse")
    private String bookSinopse;
    @JsonProperty("isbn")
    private String bookIsbn;
    @JsonProperty("edition")
    private String bookEdition;
    @JsonProperty("number_pages")
    private int bookNumberPages;
    @JsonProperty("cape")
    private String bookCape;
    @JsonProperty("authors")
    private List<AuthorResponse> bookAuthors;
    @JsonProperty("categories")
    private List<CategoryResponse> bookCategories;

}
