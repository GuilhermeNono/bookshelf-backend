package br.com.projlib.bookshelf.entrypoint.http.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorResponse {
    private String firstName;
    private String lastName;
    private String avatar;
}
