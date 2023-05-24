package br.com.projlib.bookshelf.infra.gateway.bookjpa;

import br.com.projlib.bookshelf.infra.gateway.authorjpa.AuthorJpa;
import br.com.projlib.bookshelf.infra.gateway.bookcopyjpa.BookCopyJpa;
import br.com.projlib.bookshelf.infra.gateway.borrowingjpa.BorrowingJpa;
import br.com.projlib.bookshelf.infra.gateway.categoryjpa.CategoryJpa;
import br.com.projlib.bookshelf.infra.gateway.publisherjpa.PublisherJpa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "book")
@Getter
@Setter
public class BookJpa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false)
    private Date publicationDate;

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private String sinopse;

    @Column(nullable = false)
    private String edition;

    @Column
    private String capeType;

    @Column(nullable = false)
    private int numberPages;

    @Column(nullable = false)
    private String cape;

    @ManyToOne
    @JoinColumn(name = "fk_book_publisher", referencedColumnName = "id")
    private PublisherJpa publisher;

    @OneToMany(mappedBy = "book")
    private List<BookCopyJpa> copies;

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "fk_book_author", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "fk_author_book", referencedColumnName = "id")
    )
    private List<AuthorJpa> authors;

    @ManyToMany
    @JoinTable(
            name = "book_category",
            joinColumns = @JoinColumn(name = "fk_book_category", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "fk_category_book", referencedColumnName = "id")
    )
    private List<CategoryJpa> categories;



}
