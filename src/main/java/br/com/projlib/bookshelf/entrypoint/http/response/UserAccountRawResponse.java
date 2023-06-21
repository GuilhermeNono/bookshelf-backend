package br.com.projlib.bookshelf.entrypoint.http.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserAccountRawResponse {
    long id;
    boolean active;
    String personName;
    List<UserContactResponse> userContact;
}
