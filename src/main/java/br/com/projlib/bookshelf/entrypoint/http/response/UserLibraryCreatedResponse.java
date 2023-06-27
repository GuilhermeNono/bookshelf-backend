package br.com.projlib.bookshelf.entrypoint.http.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class UserLibraryCreatedResponse {
    long id;
    String email;
    LocalDateTime createdAt;
}
