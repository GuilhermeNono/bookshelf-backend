package br.com.projlib.bookshelf.entrypoint.http.request;

import br.com.projlib.bookshelf.core.domain.UserAccount;
import br.com.projlib.bookshelf.core.usecase.FindUserByUsername;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserAccountRequest {

    private FindUserByUsername findUserByUsername;

    String username;
    boolean active;

    public UserAccount toDomain() {
        UserAccount userAccount = findUserByUsername.process(this.getUsername());
        return new UserAccount(userAccount.getId(),
                userAccount.getCpf(),
                userAccount.getUsername(),
                userAccount.getEmail(),
                userAccount.isActive(),
                userAccount.getCreatedAt(),
                userAccount.getUpdatedAt(),
                userAccount.getProfile());
    }
}
