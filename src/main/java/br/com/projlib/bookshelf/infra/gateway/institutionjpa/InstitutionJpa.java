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
    private Set<LibraryJpa> libraries;

}
