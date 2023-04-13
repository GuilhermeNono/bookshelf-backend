package br.com.projlib.bookshelf.infra.gateway.libraryjpa;

import br.com.projlib.bookshelf.core.domain.Library;
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
import java.util.List;
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
    private List<UserLibraryJpa> libraryUsers;

    @ManyToOne
    @JoinColumn(name = "fk_libary_institution", referencedColumnName = "id")
    private InstitutionJpa institution;

    public LibraryJpa() {
    }

    public LibraryJpa(Library library) {
        this.active = library.isActive();
        this.id = library.getId();
        //TODO: Descobrir algum modo de montar esse objeto com os valores da VO
//        this.institution = new InstitutionJpa(library.getInstitution());
//        this.libraryUsers
    }

    public Library toDomain() {
        return new Library(
                this.getId(),
                this.getName(),
                this.isActive(),
                this.getInstitution().toDomain()
        );
    }

    public static LibraryJpa fromDomain(Library library){
        final LibraryJpa libraryJpa = new LibraryJpa();
        //TODO: Analisar a melhor maneira de construir um objeto utilizando uma VO como base.
//        libraryJpa.setId();
        return null;
    }


}
