package br.com.projlib.bookshelf.entrypoint.http.controller;

import br.com.projlib.bookshelf.core.usecase.FindAllBooks;
import br.com.projlib.bookshelf.entrypoint.http.response.ListBooksResponse;
import br.com.projlib.bookshelf.infra.gateway.bookjpa.BookJpa;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/book")
@RequiredArgsConstructor
@Tag(name = "Book")
@Slf4j
public class BookController {

    private final FindAllBooks findAllBooks;
    private final ModelMapper modelMapper;

    @GetMapping
//    @SecurityRequirement(name = "Bearer Authentication")
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

}
