package br.com.projlib.bookshelf.infra.gateway.borrowingjpa;

import br.com.projlib.bookshelf.infra.gateway.bookjpa.BookJpa;
import br.com.projlib.bookshelf.infra.gateway.userlibraryjpa.UserLibraryJpa;
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
import java.util.Date;

@Entity
@Table(name = "borrowing")
@Getter
@Setter
public class BorrowingJpa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Date loanDate;

    @Column(nullable = false)
    private Date returnDate;

    @Column(nullable = true)
    private Date renewalDate;

    @Column(columnDefinition = "TINYINT", length = 1, nullable = false)
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "fk_borrowing_user_library", referencedColumnName = "id")
    private UserLibraryJpa userLibrary;

    @ManyToOne
    @JoinColumn(name = "fk_borrowing_book", referencedColumnName = "id")
    private BookJpa book;

}
