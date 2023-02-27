package br.com.projlib.bookshelf.infra.gateway.libraryjpa;

import br.com.projlib.bookshelf.infra.gateway.institutionjpa.InstitutionJpa;
import br.com.projlib.bookshelf.infra.gateway.userlibraryjpa.UserLibraryJpa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "library")
@Getter
@Setter
public class LibraryJpa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TINYINT", length = 1, nullable = false)
    private boolean active;

    @OneToMany(mappedBy = "library")
    private Set<UserLibraryJpa> libraryUsers;

    @ManyToOne
    @JoinColumn(name = "fk_libary_institution", referencedColumnName = "id")
    private InstitutionJpa institution;

}
