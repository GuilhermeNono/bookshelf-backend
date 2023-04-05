package br.com.projlib.bookshelf.infra.gateway.bookcopyjpa;

import br.com.projlib.bookshelf.infra.gateway.bookjpa.BookJpa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "book_copy")
@Getter
@Setter
public class BookCopyJpa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String code;

    @ManyToOne
    @JoinColumn(name = "fk_book_copy_book", referencedColumnName = "id")
    private BookJpa book;

}
