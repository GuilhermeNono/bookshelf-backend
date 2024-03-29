package br.com.projlib.bookshelf.infra.gateway.borrowingjpa;

import br.com.projlib.bookshelf.infra.gateway.bookcopyjpa.BookCopyJpa;
import br.com.projlib.bookshelf.infra.gateway.penalityjpa.PenalityJpa;
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
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "borrowing")
@Getter
@Setter
public class BorrowingJpa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private LocalDate loanDate;

    @Column(nullable = false)
    private LocalDate returnDate;

    @Column
    private LocalDate renewalDate;

    @Column
    private boolean overdue;

    @Column(columnDefinition = "TINYINT", length = 1, nullable = false)
    private boolean active;

    @OneToMany(mappedBy = "borrowing")
    private List<PenalityJpa> penalties;

    @ManyToOne
    @JoinColumn(name = "fk_borrowing_user_library", referencedColumnName = "id")
    private UserLibraryJpa userLibrary;

    @ManyToOne
    @JoinColumn(name = "fk_borrowing_book_copy", referencedColumnName = "id")
    private BookCopyJpa bookCopy;

}
