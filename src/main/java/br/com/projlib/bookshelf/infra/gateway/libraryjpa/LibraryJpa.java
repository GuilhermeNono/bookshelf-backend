package br.com.projlib.bookshelf.infra.gateway.libraryjpa;

import br.com.projlib.bookshelf.infra.gateway.bookcopyjpa.BookCopyJpa;
import br.com.projlib.bookshelf.infra.gateway.coursejpa.CourseJpa;
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

    @OneToMany(mappedBy = "library")
    private List<BookCopyJpa> bookCopies;

    @OneToMany(mappedBy = "library")
    private List<CourseJpa> courses;

    @ManyToOne
    @JoinColumn(name = "fk_libary_institution", referencedColumnName = "id")
    private InstitutionJpa institution;

    public LibraryJpa() {
    }

//    public LibraryJpa(Library library) {
//        this.id = library.getId();
//        this.active = library.isActive();
//        this.name = library.getName();
////      this.libraryUsers;
////      this.institution;
//    }

//        public Library toDomain() {
//        return new Library(
//                this.getId(),
//                this.getName(),
//                this.isActive(),
//                this.getInstitution()
//        );


//    public static LibraryJpa fromDomain(Library library){
//        final LibraryJpa libraryJpa = new LibraryJpa();
//
//        libraryJpa.setId(libraryJpa.getId());
//        libraryJpa.setName(library.getName());
//        libraryJpa.setActive(library.isActive());
//        libraryJpa.setInstitution(library.getInstitution());
//
//        return null;
//    }
//
//    public void setInstitution(InstitutionJpa institution) {
//        this.institution = institution;
//        institution.getLibraries().add(this);
//    }
}
