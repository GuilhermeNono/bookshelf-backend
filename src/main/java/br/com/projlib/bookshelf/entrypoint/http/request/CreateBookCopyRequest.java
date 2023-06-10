package br.com.projlib.bookshelf.entrypoint.http.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CreateBookCopyRequest {
    long bookId;
    long libId;
    long userId;
    String code;
}
