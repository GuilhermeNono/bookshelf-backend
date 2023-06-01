package br.com.projlib.bookshelf.infra.gateway.userlibraryprofileJpa;

import br.com.projlib.bookshelf.infra.gateway.libpermissionjpa.LibPermissionJpa;
import br.com.projlib.bookshelf.infra.gateway.userlibraryjpa.UserLibraryJpa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "user_library_profile")
@Getter
@Setter
public class UserLibraryProfileJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "profile")
    private List<UserLibraryJpa> userLibrary;

    @ManyToMany
    @JoinTable(
            name = "user_library_profile_lib_permission",
            joinColumns = @JoinColumn(name = "fk_user_library_profile_lib_permission", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "fk_lib_permission_user_library_profile", referencedColumnName = "id"))
    private List<LibPermissionJpa> libraryPermissions;
}
