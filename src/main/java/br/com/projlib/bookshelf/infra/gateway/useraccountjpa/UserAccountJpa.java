package br.com.projlib.bookshelf.infra.gateway.useraccountjpa;

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

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "user_account")
@Getter
@Setter
public class UserAccountJpa implements Serializable {

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

}
