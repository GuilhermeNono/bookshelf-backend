package br.com.projlib.bookshelf.infra.gateway.libpermissionjpa;

import br.com.projlib.bookshelf.infra.gateway.userlibraryprofileJpa.UserLibraryProfileJpa;
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
import java.util.List;

@Entity
@Table(name = "lib_permission")
@Getter
@Setter
public class LibPermissionJpa implements GrantedAuthority, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String code;

    @ManyToMany(mappedBy = "libraryPermissions")
    private List<UserLibraryProfileJpa> libraryUserProfile;

    @Override
    public String getAuthority() {
        return null;
    }
}
