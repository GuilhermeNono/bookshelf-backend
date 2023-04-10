package br.com.projlib.bookshelf.entrypoint.http.response;

import br.com.projlib.bookshelf.core.domain.Contact;
import br.com.projlib.bookshelf.core.domain.UserAccount;
import br.com.projlib.bookshelf.core.usecase.FindUserByEmail;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserAccountResponse {

    private FindUserByEmail findUserByEmail;

    String email;
    boolean active;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public UserAccountResponse(String email, boolean active, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.email = email;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static UserAccountResponse fromDomain(final UserAccount userAccount) {

        String email = null;

        for (Contact contact : userAccount.getContacts()) {
            if(contact.getType().equals("email")) {
                email = contact.getContact();
            }
        }


        return new UserAccountResponse(
                email,
                userAccount.isActive(),
                userAccount.getCreatedAt(),
                userAccount.getUpdatedAt()
        );
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
