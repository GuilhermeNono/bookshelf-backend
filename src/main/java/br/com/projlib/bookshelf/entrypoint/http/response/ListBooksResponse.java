package br.com.projlib.bookshelf.entrypoint.http.response;

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
public class ListBooksResponse {

    private String name;
    private String language;
    private Date publicationDate;
    private String publisherName;

}
