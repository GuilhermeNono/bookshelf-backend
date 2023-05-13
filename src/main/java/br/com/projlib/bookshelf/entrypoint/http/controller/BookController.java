package br.com.projlib.bookshelf.entrypoint.http.controller;

import br.com.projlib.bookshelf.core.usecase.FindAllBooks;
import br.com.projlib.bookshelf.core.usecase.FindBookById;
import br.com.projlib.bookshelf.core.usecase.FindBookByIsbn;
import br.com.projlib.bookshelf.core.usecase.FindBookByName;
import br.com.projlib.bookshelf.entrypoint.http.response.ListBooksResponse;
import br.com.projlib.bookshelf.infra.gateway.bookjpa.BookJpa;
import br.com.projlib.bookshelf.infra.query.BookSearchQuery;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/book")
@RequiredArgsConstructor
@Tag(name = "Book")
@Slf4j
public class BookController {

    private final FindAllBooks findAllBooks;
    private final FindBookById findBookById;
    private final FindBookByName findBookByName;
    private final FindBookByIsbn findBookByIsbn;

    private final ModelMapper modelMapper;

    @GetMapping
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<ListBooksResponse>> getAllBooks() {
        try {
            var list = findAllBooks.process()
                    .stream()
                    .map(book -> modelMapper.map(book, ListBooksResponse.class))
                    .toList();

            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<ListBooksResponse>> getBookByName(@RequestBody BookSearchQuery book) {
        try {
            if(book.getName() != null) {
                List<ListBooksResponse> books = findBookByName.process(book.getName()).stream()
                        .map(p -> modelMapper.map(p, ListBooksResponse.class))
                        .toList();

                return new ResponseEntity<>(books, HttpStatus.OK);
            } else if(book.getIsbn() != null) {
                List<ListBooksResponse> books = findBookByIsbn.process(book.getIsbn()).stream()
                        .map(p -> modelMapper.map(p, ListBooksResponse.class))
                        .toList();

                return new ResponseEntity<>(books, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<ListBooksResponse> getAllBooks(@PathVariable long id) {
        try {
            BookJpa book = findBookById.process(id);
            var response = modelMapper.map(book, ListBooksResponse.class);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
