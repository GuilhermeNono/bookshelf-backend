package br.com.projlib.bookshelf.infra.command;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

public class LoginCommand implements Serializable {

    @NotNull
    @Size(min = 1, max = 60)
    @JsonProperty("email")
    private String email;

    @NotNull
    @Size(min = 1, max = 60)
    @JsonProperty("password")
    private String password;

    private LoginCommand() {
        super();
    }

    public LoginCommand(String email, String password) {
        this();
        this.setEmail(email);
        this.setPassword(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
