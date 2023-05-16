package br.com.projlib.bookshelf.entrypoint.http.controller;

import br.com.projlib.bookshelf.core.usecase.FindAllBooks;
import br.com.projlib.bookshelf.core.usecase.FindBookById;
import br.com.projlib.bookshelf.core.usecase.FindBookByIsbn;
import br.com.projlib.bookshelf.core.usecase.FindBookByName;
import br.com.projlib.bookshelf.core.usecase.FindBookBySearchCriteria;
import br.com.projlib.bookshelf.entrypoint.http.response.ListBooksResponse;
import br.com.projlib.bookshelf.infra.command.BookDTO;
import br.com.projlib.bookshelf.infra.gateway.bookjpa.BookJpa;
import br.com.projlib.bookshelf.infra.query.SearchCriteria;
import br.com.projlib.bookshelf.infra.specification.BookSpecificationBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    private final FindBookBySearchCriteria findBookBySearchCriteria;

    private final ModelMapper modelMapper;

    @Operation(summary = "Get All books")
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

    @Operation(summary = "Get Book by Id")
    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<ListBooksResponse> getBookById(@PathVariable long id) {
        try {
            BookJpa book = findBookById.process(id);
            var response = modelMapper.map(book, ListBooksResponse.class);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Search Book")
    @PostMapping("/search")
    public ResponseEntity<Page<ListBooksResponse>> searchBooks
            (@RequestParam(name = "pageNum",
                    defaultValue = "0") int pageNum,
             @RequestParam(name = "pageSize",
                     defaultValue = "10") int pageSize,
             @RequestBody BookDTO
                     bookDTO){
        BookSpecificationBuilder builder = new
                BookSpecificationBuilder();
        List<SearchCriteria> criteriaList =
                bookDTO.getSearchCriteriaList();
        if(criteriaList != null){
            criteriaList.forEach(x->
            {x.setDataOption(bookDTO
                    .getDataOption());
                builder.with(x);
            });
        }

        Pageable page = PageRequest.of(pageNum, pageSize,
                Sort.by("name")
                        .ascending()
                        .and(Sort.by("publicationDate"))
                        .ascending());
//                        .and(Sort.by("authors"))
//                        .ascending());

        Page<ListBooksResponse> employeePage =
                findBookBySearchCriteria.process(builder.build(),
                        page);

        return new ResponseEntity<>(employeePage, HttpStatus.OK);
    }



}
