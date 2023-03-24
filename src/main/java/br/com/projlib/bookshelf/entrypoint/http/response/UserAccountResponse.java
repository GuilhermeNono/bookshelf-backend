package br.com.projlib.bookshelf.entrypoint.http.response;

import br.com.projlib.bookshelf.core.domain.UserAccount;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserAccountResponse {
    String username;
    boolean active;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public static UserAccountResponse fromDomain(final UserAccount userAccount) {
        return new UserAccountResponse(
                userAccount.getUsername(),
                userAccount.isActive(),
                userAccount.getCreatedAt(),
                userAccount.getUpdatedAt()
        );
    }
}
