package br.com.projlib.bookshelf.entrypoint.http.request;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
public class DeleteBookCopyRequest {
    String libCode;
}
