package br.com.projlib.bookshelf.infra.gateway.useraccountjpa;

import br.com.projlib.bookshelf.core.domain.Contact;
import br.com.projlib.bookshelf.core.domain.Password;
import br.com.projlib.bookshelf.core.domain.UserAccount;
import br.com.projlib.bookshelf.infra.gateway.passwordjpa.PasswordJpa;
import br.com.projlib.bookshelf.infra.gateway.usercontact.UserContactJpa;
import br.com.projlib.bookshelf.infra.gateway.userlibraryjpa.UserLibraryJpa;
import br.com.projlib.bookshelf.infra.gateway.userprofile.UserProfileJpa;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    @Column(columnDefinition = "TINYINT", length = 1, nullable = false)
    private boolean active;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String personName;

    @Column
    private Date birthDay;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "userAccount",orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<PasswordJpa> passwords;

    @ManyToOne
    @JoinColumn(name = "fk_user_account_user_profile", referencedColumnName = "id")
    private UserProfileJpa userProfile;

    @OneToMany(mappedBy = "userAccount", orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<UserContactJpa> userContact;

    @OneToMany(mappedBy = "userAccount",orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<UserLibraryJpa> userLibraries;

    private UserAccountJpa() {
        super();
    }
    public UserAccountJpa(String cpf, String password) {
        this();
        this.setCpf(cpf);
        this.addPassword(password);
    }

    public UserAccount toDomain() {
        List<Contact> contactList = this.getUserContact()
                .stream()
                .map(UserContactJpa::toDomain)
                .toList();

        List<Password> passwordList = this.getPasswords()
                .stream()
                .map(PasswordJpa::toDomain)
                .toList();

        return new UserAccount(
                this.getId(),
                this.getCpf(),
                this.isActive(),
                this.getCreatedAt(),
                this.getUpdatedAt(),
                this.getPersonName(),
                this.getBirthDay(),
                this.getUserProfile().toDomain(),
                contactList,
                passwordList
        );
    }

    public static UserAccountJpa fromDomain(final UserAccount userAccount) {
        final UserAccountJpa userAccountJpa = new UserAccountJpa();

        userAccountJpa.setId(userAccount.getId());
        userAccountJpa.setActive(userAccount.isActive());
        userAccountJpa.setCpf(userAccount.getCpf());
        userAccountJpa.setCreatedAt(userAccount.getCreatedAt());
        userAccountJpa.setUpdatedAt(userAccount.getUpdatedAt());
        userAccountJpa.setUserProfile(UserProfileJpa.fromDomain(userAccount.getProfile()));

        return userAccountJpa;
    }

    public String getEmail() {
        String email = "";
        for (UserContactJpa userContactJpa : this.getUserContact()) {
            if(userContactJpa.isActive())
                if(userContactJpa.getUserContactType().getId() == 1){
                    email = userContactJpa.getContact();
                }
        }
        return email;
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

    public void addPassword(final String password) {
        if (Objects.isNull(this.getPasswords())) {
            this.setUserPasswords(new ArrayList<>());
        }

        final PasswordJpa user = new PasswordJpa(this, password, true);

        for (PasswordJpa userPassword : this.getPasswords()) {
            if (userPassword.isActive()) {
                userPassword.setActive(false);
            }
        }

        this.getPasswords().add(user);
    }

    public void setUserPasswords(List<PasswordJpa> userPasswords) {
        this.passwords = userPasswords;
    }

    @Override
    public String getUsername() {
        return this.getCpf();
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
        return active;
    }
}
