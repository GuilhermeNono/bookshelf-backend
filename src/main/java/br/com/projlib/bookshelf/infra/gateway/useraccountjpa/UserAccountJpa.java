package br.com.projlib.bookshelf.infra.gateway.useraccountjpa;

import br.com.projlib.bookshelf.core.domain.UserAccount;
import br.com.projlib.bookshelf.infra.gateway.passwordjpa.PasswordJpa;
import br.com.projlib.bookshelf.infra.gateway.userlibraryjpa.UserLibraryJpa;
import br.com.projlib.bookshelf.infra.gateway.userprofile.UserProfileJpa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "user_account")
@Getter
@Setter
public class UserAccountJpa implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(columnDefinition = "TINYINT", length = 1, nullable = false)
    private boolean active;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "userAccount")
    private Set<PasswordJpa> passwords;

    @OneToOne
    @JoinColumn(name = "fk_user_account_user_profile", referencedColumnName = "id")
    private UserProfileJpa userProfile;

    @OneToMany(mappedBy = "userAccount")
    private Set<UserLibraryJpa> userLibraries;

    public UserAccount toDomain() {
        return new UserAccount(
                this.getId(),
                this.getCpf(),
                this.getUsername(),
                this.getEmail(),
                this.isActive(),
                this.getCreatedAt(),
                this.getUpdatedAt(),
                this.getUserProfile().toDomain()
        );
    }

    public static UserAccountJpa fromDomain(final UserAccount userAccount) {
        final UserAccountJpa userAccountJpa = new UserAccountJpa();

        userAccountJpa.setId(userAccount.getId());
        userAccountJpa.setUsername(userAccount.getUsername());
        userAccountJpa.setActive(userAccount.isActive());
        userAccountJpa.setCpf(userAccount.getCpf());
        userAccountJpa.setEmail(userAccount.getEmail());
        userAccountJpa.setCreatedAt(userAccount.getCreatedAt());
        userAccountJpa.setUpdatedAt(userAccount.getUpdatedAt());
        userAccountJpa.setUserProfile(UserProfileJpa.fromDomain(userAccount.getProfile()));

        return userAccountJpa;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getUserProfile().getSystemPermissions();
    }

    @Override
    public String getPassword() {
        Optional<PasswordJpa> userPasswordJpa = this.getPasswords()
                .stream()
                .filter(passwords -> {
                    return passwords.isActive() && (!passwords.getPassword().isEmpty());
                })
                .findFirst();

        return userPasswordJpa.map(PasswordJpa::getPassword).orElse(null);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
