package br.com.projlib.bookshelf.infra.gateway.libpermissionjpa;

import br.com.projlib.bookshelf.infra.gateway.userlibraryjpa.UserLibraryJpa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "lib_permission")
@Getter
@Setter
public class LibPermissionJpa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String description;

    @ManyToMany(mappedBy = "libraryPermissions")
    private Set<UserLibraryJpa> libraryUsers;

}
