package br.com.projlib.bookshelf.entrypoint.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
public class UserAccountResponse {

    long id;
    String personName;
    String cpf;
    String email;
    boolean active;
    LocalDateTime updatedAt;
    @JsonProperty("contacts")
    List<UserContactResponse> userContact;
}
