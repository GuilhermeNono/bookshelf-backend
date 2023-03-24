package br.com.projlib.bookshelf.entrypoint.http.request;

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
public class UserAccountRequest {
    String username;
    boolean active;

    public UserAccount toDomain() {



//        return new UserAccount(1,
//                false,
//                this.getUsername(),
//                this.isActive(),
//                LocalDateTime.now(),
//                LocalDateTime.now(),
//                null);
    }
}
