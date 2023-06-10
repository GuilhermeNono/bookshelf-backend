package br.com.projlib.bookshelf.infra.gateway.publisherjpa;

import br.com.projlib.bookshelf.entrypoint.http.request.PublisherRequest;
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
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "publisher")
@Getter
@Setter
public class PublisherJpa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "publisher")
    private List<BookJpa> books;

    public PublisherJpa() {
    }

    public PublisherJpa(PublisherRequest publisherRequest, BookJpa bookJpa) {
        this.name = publisherRequest.getName();
        this.books = this.addBooks(bookJpa);
    }

    public List<BookJpa> addBooks(BookJpa bookJpa) {
         if(books == null) {
             books = new ArrayList<BookJpa>();
         }
         books.add(bookJpa);
         return books;
    }
}
