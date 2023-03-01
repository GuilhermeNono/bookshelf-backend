package br.com.projlib.bookshelf.infra.gateway.bookcopyjpa;

import br.com.projlib.bookshelf.infra.gateway.bookjpa.BookJpa;
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
@Table(name = "book_copy")
@Getter
@Setter
public class BookCopyJpa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "copy")
    private Set<BookJpa> books;

}
