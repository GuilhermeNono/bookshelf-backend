package br.com.projlib.bookshelf.entrypoint.http.controller;

import br.com.projlib.bookshelf.core.usecase.FindAllBooksOfLibrary;
import br.com.projlib.bookshelf.core.usecase.FindAllBooksOfMonth;
import br.com.projlib.bookshelf.core.usecase.FindAllLibraries;
import br.com.projlib.bookshelf.core.usecase.FindBookById;
import br.com.projlib.bookshelf.core.usecase.FindBookCopyBySearchCriteria;
import br.com.projlib.bookshelf.core.usecase.FindBookOnLibraryByIsbn;
import br.com.projlib.bookshelf.core.usecase.FindBookOnLibraryByName;
import br.com.projlib.bookshelf.core.usecase.FindOneLibrary;
import br.com.projlib.bookshelf.core.usecase.FindUserLibraryById;
import br.com.projlib.bookshelf.core.usecase.SaveBookCopy;
import br.com.projlib.bookshelf.entrypoint.http.request.CreateBookCopyRequest;
import br.com.projlib.bookshelf.entrypoint.http.request.SearchRequest;
import br.com.projlib.bookshelf.entrypoint.http.response.BookCopyOfMonthResponse;
import br.com.projlib.bookshelf.entrypoint.http.response.BookCopyResponse;
import br.com.projlib.bookshelf.entrypoint.http.response.LibraryResponse;
import br.com.projlib.bookshelf.infra.gateway.bookcopyjpa.BookCopyJpa;
import br.com.projlib.bookshelf.infra.gateway.bookjpa.BookJpa;
import br.com.projlib.bookshelf.infra.gateway.libraryjpa.LibraryJpa;
import br.com.projlib.bookshelf.infra.query.SearchCriteria;
import br.com.projlib.bookshelf.infra.specification.BookCopySpecificationBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/library")
@RequiredArgsConstructor
@Tag(name = "Library")
@Slf4j
public class LibraryController {

    private final FindAllLibraries findAllLibraries;
    private final FindOneLibrary findOneLibrary;
    private final FindAllBooksOfLibrary findAllBooksOfLibrary;
    private final FindBookCopyBySearchCriteria findBookCopyBySearchCriteria;
    private final FindBookOnLibraryByName findBookOnLibraryByName;
    private final FindAllBooksOfMonth findAllBooksOfMonth;
    private final FindBookOnLibraryByIsbn findBookOnLibraryByIsbn;
    private final FindUserLibraryById findUserLibraryById;
    private final FindBookById findBookById;

    private final SaveBookCopy saveBookCopy;

    private final ModelMapper modelMapper;

    /**
     * <h1>Buscar todos os usuarios</h1>
     * <br/>
     * <p>Método responsavel por buscar todas as bibliotecas disponiveis no sistema.</p>
     *
     * @return Uma lista com todas as bibliotecas.
     * @throws org.springframework.web.client.HttpClientErrorException.BadRequest
     * @throws RuntimeException
     */
    @Operation(summary = "Get all libraries of system")
    @GetMapping
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<LibraryResponse>> getAllLibraries() {
        try {
            List<LibraryResponse> libraries = findAllLibraries
                    .process().stream()
                    .map(lib -> modelMapper.map(lib, LibraryResponse.class))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(libraries, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @Operation(summary = "Get a library by id")
    @GetMapping(value = "/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<LibraryResponse> getLibrary(@PathVariable long id) {
        try {
            LibraryJpa library = findOneLibrary.process(id);
            LibraryResponse libResponse = modelMapper.map(library, LibraryResponse.class);
            return new ResponseEntity<>(libResponse, HttpStatus.OK);
        } catch (RuntimeException e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @Operation(summary = "Get all books of a library")
    @GetMapping(value = "/{id}/books")
    @Deprecated
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<BookCopyResponse>> getAllBooks(@PathVariable long id) {
        try {
            List<BookCopyResponse> books = findAllBooksOfLibrary.process(id)
                    .stream()
                    .map(lb -> modelMapper.map(lb, BookCopyResponse.class))
                    .toList();
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Get all books of the month")
    @GetMapping(value = "/{id}/books/month")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<BookCopyOfMonthResponse>> getAllBooksOfMonth(@PathVariable long id) {
        try {
            LibraryJpa library = findOneLibrary.process(id);
            List<BookCopyOfMonthResponse> books = findAllBooksOfMonth.process(library)
                    .stream()
                    .map(b -> modelMapper.map(b, BookCopyOfMonthResponse.class))
                    .toList();
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (RuntimeException e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Search Book")
    @PostMapping("/books/search")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Page<BookCopyResponse>> searchBookCopies
            (@RequestParam(name = "pageNum",
                    defaultValue = "0") int pageNum,
             @RequestParam(name = "pageSize",
                     defaultValue = "10") int pageSize,
             @RequestBody SearchRequest
                     bookCopyDTO) {
        BookCopySpecificationBuilder builder = new
                BookCopySpecificationBuilder();
        List<SearchCriteria> criteriaList =
                bookCopyDTO.getSearchCriteriaList();
        if (criteriaList != null) {
            criteriaList.forEach(x ->
            {
                x.setDataOption(bookCopyDTO
                        .getDataOption());
                builder.with(x);
            });
        }

        Pageable page = PageRequest.of(pageNum, pageSize, Sort.by("createdAt")
                .descending());


        Page<BookCopyResponse> employeePage =
                findBookCopyBySearchCriteria.process(builder.build(),
                        page);

        return new ResponseEntity<>(employeePage, HttpStatus.OK);
    }

    @Operation(summary = "Add a book copy")
    @PostMapping(value = "/book/add")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> addNewCopy(@Valid @RequestBody CreateBookCopyRequest bookCopyRequest) {
        try {
            LibraryJpa library = findOneLibrary.process(bookCopyRequest.getLibId());
            //UserLibraryJpa user = findUserLibraryById.process(bookCopyRequest.getUserId());
            BookJpa book = findBookById.process(bookCopyRequest.getBookId());

            BookCopyJpa newBook = new BookCopyJpa(bookCopyRequest.getCode(),
                    book, library);

            newBook.setActive(true);
            newBook.setCreatedAt(LocalDateTime.now());
            newBook.setUpdatedAt(LocalDateTime.now());

            saveBookCopy.process(newBook);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (RuntimeException e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
