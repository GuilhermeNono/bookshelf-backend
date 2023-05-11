package br.com.projlib.bookshelf.entrypoint.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonPropertyOrder({"lib_code", "book", "language", "publication_date", "publisher", "sinopse", "isbn", "edition", "number_pages", "cape"})
public class ListBookCopyResponse {

    @JsonProperty("lib_code")
    private String code;
    @JsonProperty("book")
    private String bookName;
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

}
