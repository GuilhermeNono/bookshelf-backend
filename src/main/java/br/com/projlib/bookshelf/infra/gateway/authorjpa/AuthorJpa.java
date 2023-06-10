package br.com.projlib.bookshelf.infra.gateway.authorjpa;

import br.com.projlib.bookshelf.entrypoint.http.request.AuthorRequest;
import br.com.projlib.bookshelf.infra.gateway.bookjpa.BookJpa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
@Getter
@Setter
public class AuthorJpa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column
    private String avatar;

    @ManyToMany(mappedBy = "authors")
    private List<BookJpa> books;

    public AuthorJpa() {
    }

    public AuthorJpa(String firstName, String lastName, String avatar) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
    }

    public AuthorJpa(AuthorRequest authorRequest, BookJpa bookJpa) {
        this.avatar = authorRequest.getAvatar();
        this.firstName = authorRequest.getFirstName();
        this.lastName = authorRequest.getLastName();
        this.books = this.addBooks(bookJpa);
    }

    public List<BookJpa> addBooks(BookJpa bookJpa) {
        if(books == null) {
            books = new ArrayList<>();
        }
        books.add(bookJpa);
        return books;
    }
}
