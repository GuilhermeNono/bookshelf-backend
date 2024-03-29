package br.com.projlib.bookshelf.infra.gateway.passwordjpa;

import br.com.projlib.bookshelf.core.domain.Password;
import br.com.projlib.bookshelf.infra.gateway.useraccountjpa.UserAccountJpa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serializable;

@Entity
@Table(name = "password")
@Getter
@Setter
public class PasswordJpa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String password;

    @Column(columnDefinition = "TINYINT", length = 1, nullable = false)
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "fk_password_user_account", referencedColumnName = "id")
    private UserAccountJpa userAccount;

    public Password toDomain() {
        return new Password(
                this.getId(),
                this.getPassword(),
                this.isActive()
        );
    }

    private PasswordJpa() {
        super();
        this.setActive(false);
    }

    public PasswordJpa(UserAccountJpa userAccount, String password) {
        this();
        this.setUserAccount(userAccount);
        this.setPassword(password);
    }

    public PasswordJpa(UserAccountJpa userAccount, String password, boolean active) {
        this();
        this.setUserAccount(userAccount);
        this.setPassword(password);
        this.setActive(active);
    }

    public void setPassword(String password) {
        final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }
}
