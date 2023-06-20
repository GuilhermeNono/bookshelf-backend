package br.com.projlib.bookshelf.infra.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"accountId", "accountProfile", "token", "librariesAccount"})
public class AuthenticationToken implements Serializable {

    @JsonProperty("token")
    private String token;

    @JsonProperty("accountId")
    private long userId;

    @JsonProperty("accountProfile")
    private String userProfileName;

    private List<LibraryUserInfo> librariesAccount;

    public AuthenticationToken(String token) {
        this.token = token;
    }

    public AuthenticationToken(String token, long userId, List<LibraryUserInfo> librariesAccount) {
        this.token = token;
        this.userId = userId;
        this.librariesAccount = librariesAccount;
    }
}
