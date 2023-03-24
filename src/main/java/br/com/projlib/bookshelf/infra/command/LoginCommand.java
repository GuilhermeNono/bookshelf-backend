package br.com.projlib.bookshelf.infra.command;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class LoginCommand implements Serializable {

    @NotNull
    @Size(min = 1, max = 60)
    @JsonProperty("username")
    private String username;

    @NotNull
    @Size(min = 1, max = 60)
    @JsonProperty("password")
    private String password;

    private LoginCommand() {
        super();
    }

    public LoginCommand(String username, String password) {
        this();
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
