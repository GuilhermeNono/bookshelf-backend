package br.com.projlib.bookshelf.infra.gateway.institutionjpa;


import br.com.projlib.bookshelf.infra.gateway.libraryjpa.LibraryJpa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "institution")
@Getter
@Setter
public class InstitutionJpa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String cnpj;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "institution")
    private List<LibraryJpa> libraries;

    public InstitutionJpa() {
    }

    public InstitutionJpa(List<LibraryJpa> library) {
        this();
        this.setLibraries(library);
    }

//    public Institution toDomain(){
//        return new Institution(
//                this.getId(),
//                this.getName(),
//                this.getCnpj(),
//                this.getLibraries()
//                        .stream()
//                        .map(LibraryJpa::toDomain)
//                        .toList()
//        );
//    }

//    public static InstitutionJpa fromDomain(Institution institution) {
////        final InstitutionJpa institutionJpa = new InstitutionJpa();
////
////        institutionJpa.setCnpj(institutionJpa.getCnpj());
////        institutionJpa.setId(institution.getId());
////        institutionJpa.setName(institution.getName());
////        institutionJpa.setLibraries(institution.getLibraries());
//
//        return null;
//    }
//
//    public LibraryJpa createLibraryFromVO(Library vo) {
////        LibraryJpa library = new LibraryJpa();
////        library.setName(vo.getName());
////        library.setActive(vo.isActive());
////        library.setId(library.getId());
////        library.set
////        library.setInstitution(this);
//        return null;
//    }

}
