package br.com.projlib.bookshelf.infra.gateway.institutionjpa;


import br.com.projlib.bookshelf.core.domain.Institution;
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
import java.util.Set;

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

    public InstitutionJpa(Institution institution, LibraryJpa libraryJpa) {
        //TODO: Analisar a melhor maneira de construir um objeto utlizando uma VO como base.
        this.id = institution.getId();
        this.cnpj = institution.getCnpj();
//        this.libraries = libraryJpa;
    }

    public Institution toDomain() {
        return new Institution(this.getId(),
                this.getName(),
                this.getCnpj());
    }
}
