package br.com.projlib.bookshelf.infra.gateway.bookcopyjpa;

import br.com.projlib.bookshelf.infra.gateway.bookjpa.BookJpa;
import br.com.projlib.bookshelf.infra.gateway.borrowingjpa.BorrowingJpa;
import br.com.projlib.bookshelf.infra.gateway.libraryjpa.LibraryJpa;
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
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "book_copy")
@Getter
@Setter
public class BookCopyJpa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String code;

    @Column(columnDefinition = "TINYINT", length = 1, nullable = false)
    private boolean active;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "bookCopy")
    private List<BorrowingJpa> borrowings;
    
    @ManyToOne
    @JoinColumn(name = "fk_book_copy_book", referencedColumnName = "id")
    private BookJpa book;

    @ManyToOne
    @JoinColumn(name = "fk_book_copy_library", referencedColumnName = "id")
    private LibraryJpa library;

    public BookCopyJpa() {
    }

    public BookCopyJpa(String code, BookJpa book, LibraryJpa library) {
        this.code = code;
        this.book = book;
        this.library = library;
    }
}
