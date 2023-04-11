package br.com.projlib.bookshelf.infra.gateway.userprofile;

import br.com.projlib.bookshelf.core.domain.Profile;
import br.com.projlib.bookshelf.infra.gateway.syspermissionjpa.SysPermissionJpa;
import br.com.projlib.bookshelf.infra.gateway.useraccountjpa.UserAccountJpa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user_profile")
@Getter
@Setter
public class UserProfileJpa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @ManyToMany
    @JoinTable(
            name = "sys_permission_user_profile",
            joinColumns = @JoinColumn(name = "fk_user_profile_sys_permission", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "fk_sys_permission_user_profile", referencedColumnName = "id"))
    private Set<SysPermissionJpa> systemPermissions;

    @OneToMany(mappedBy = "userProfile")
    private List<UserAccountJpa> userAccounts;

    public Profile toDomain() {
        return new Profile(
                this.getId(),
                this.getName()
        );
    }

    public static UserProfileJpa fromDomain(final Profile userProfile) {
        UserProfileJpa userProfileJpa = new UserProfileJpa();

        userProfileJpa.setId(userProfile.getId());
        userProfileJpa.setName(userProfile.getName());

        return userProfileJpa;
    }

}
