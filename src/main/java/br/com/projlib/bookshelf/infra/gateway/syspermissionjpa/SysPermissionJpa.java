package br.com.projlib.bookshelf.infra.gateway.syspermissionjpa;

import br.com.projlib.bookshelf.infra.gateway.userprofile.UserProfileJpa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "sys_permission")
@Getter
@Setter
public class SysPermissionJpa implements GrantedAuthority, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String code;

    @ManyToMany(mappedBy = "systemPermissions")
    private Set<UserProfileJpa> profiles;

    @Override
    public String getAuthority() {
        return this.getCode();
    }
}
