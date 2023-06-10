package br.com.projlib.bookshelf.infra.gateway.categoryjpa;

import br.com.projlib.bookshelf.entrypoint.http.request.CategoryRequest;
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
@Table(name = "category")
@Getter
@Setter
public class CategoryJpa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<BookJpa> books;

    public CategoryJpa() {
    }

    public CategoryJpa(CategoryRequest categoryRequest, BookJpa bookJpa) {
        this.name = categoryRequest.getName();
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
