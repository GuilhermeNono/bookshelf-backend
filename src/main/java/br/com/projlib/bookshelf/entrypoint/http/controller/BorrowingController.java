package br.com.projlib.bookshelf.entrypoint.http.controller;

import br.com.projlib.bookshelf.core.usecase.FindAllBorrowingOfMonth;
import br.com.projlib.bookshelf.core.usecase.FindAllBorrowings;
import br.com.projlib.bookshelf.core.usecase.FindBookCopyByCode;
import br.com.projlib.bookshelf.core.usecase.FindBorrowingById;
import br.com.projlib.bookshelf.core.usecase.FindBorrowingBySearchCriteria;
import br.com.projlib.bookshelf.core.usecase.FindOneLibrary;
import br.com.projlib.bookshelf.core.usecase.FindUserLibraryById;
import br.com.projlib.bookshelf.core.usecase.SaveBookCopy;
import br.com.projlib.bookshelf.core.usecase.SaveBorrowing;
import br.com.projlib.bookshelf.entrypoint.http.request.BorrowingCreateRequest;
import br.com.projlib.bookshelf.entrypoint.http.request.BorrowingRenewalRequest;
import br.com.projlib.bookshelf.entrypoint.http.response.BorrowingListResponse;
import br.com.projlib.bookshelf.infra.command.BorrowingDTO;
import br.com.projlib.bookshelf.infra.gateway.bookcopyjpa.BookCopyJpa;
import br.com.projlib.bookshelf.infra.gateway.borrowingjpa.BorrowingJpa;
import br.com.projlib.bookshelf.infra.gateway.libraryjpa.LibraryJpa;
import br.com.projlib.bookshelf.infra.gateway.userlibraryjpa.UserLibraryJpa;
import br.com.projlib.bookshelf.infra.query.SearchCriteria;
import br.com.projlib.bookshelf.infra.specification.BorrowingSpecificationBuilder;
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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/borrowing")
@Tag(name = "Borrowing")
@Slf4j
public class BorrowingController {

    private final FindAllBorrowings findAllBorrowings;
    private final FindBorrowingById findBorrowingById;

    private final SaveBorrowing saveBorrowing;
    private final SaveBookCopy saveBookCopy;

    private final FindBorrowingBySearchCriteria findBorrowingBySearchCriteria;
    private final FindUserLibraryById findUserLibraryById;
    private final FindBookCopyByCode findBookCopyByCode;
    private final FindOneLibrary findOneLibrary;
    private final FindAllBorrowingOfMonth findAllBorrowingOfMonth;


    private final ModelMapper modelMapper;

    @GetMapping
    @Operation(summary = "Get All Borrowings")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<BorrowingListResponse>> getAllBorrowing() {
        try {
            List<BorrowingListResponse> response = findAllBorrowings.process()
                    .stream()
                    .map(b -> modelMapper.map(b, BorrowingListResponse.class))
                    .toList();

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a Borrowing")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<BorrowingListResponse> getABorrowing(@PathVariable long id) {
        try {
            BorrowingListResponse borrowing = modelMapper.map(findBorrowingById.process(id), BorrowingListResponse.class);
            return new ResponseEntity<>(borrowing, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Search Borrowing")
    @PostMapping("/search")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Page<BorrowingListResponse>> searchBorrowing
            (@RequestParam(name = "pageNum",
                    defaultValue = "0") int pageNum,
             @RequestParam(name = "pageSize",
                     defaultValue = "10") int pageSize,
             @RequestBody BorrowingDTO
                     borrowingDTO){
        BorrowingSpecificationBuilder builder = new
                BorrowingSpecificationBuilder();
        List<SearchCriteria> criteriaList =
                borrowingDTO.getSearchCriteriaList();
        if(criteriaList != null){
            criteriaList.forEach(x->
            {x.setDataOption(borrowingDTO
                    .getDataOption());
                builder.with(x);
            });
        }

        Pageable page = PageRequest.of(pageNum, pageSize,
                Sort.by("active")
                        .descending()
                        .and(Sort.by("overdue")
                                .ascending()));

        Page<BorrowingListResponse> borrowingPage =
                findBorrowingBySearchCriteria.process(builder.build(),
                        page);

        return new ResponseEntity<>(borrowingPage, HttpStatus.OK);
    }

    @Operation(summary = "Create new borrowing")
    @PostMapping
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> createBorrowing(@RequestBody @Valid BorrowingCreateRequest borrowingCreate) {
        try {
            final BorrowingJpa borrowing = new BorrowingJpa();
            final UserLibraryJpa user = findUserLibraryById.process(borrowingCreate.getUserId());
            final BookCopyJpa bookCopy = findBookCopyByCode.process(borrowingCreate.getBookCode());

            borrowing.setActive(true);
            borrowing.setLoanDate(borrowingCreate.getLoanDate());
            borrowing.setRenewalDate(null);
            borrowing.setReturnDate(borrowingCreate.getReturnDate());
            borrowing.setPenalties(null);
            borrowing.setBookCopy(bookCopy);
            borrowing.setUserLibrary(user);

            saveBorrowing.process(borrowing);

            bookCopy.setActive(false);

            saveBookCopy.process(bookCopy);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Renewal a existent borrowing")
    @PatchMapping("/renewal")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> updateRenewalBorrowing(@RequestBody @Valid BorrowingRenewalRequest borrowingRenewal){
        try {
            final BorrowingJpa borrowing = findBorrowingById.process(borrowingRenewal.getBorrowingId());

            borrowing.setRenewalDate(LocalDate.now());
            borrowing.setReturnDate(borrowingRenewal.getDateToReturn());

            saveBorrowing.process(borrowing);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Get all borrowings of the month")
    @GetMapping(value = "/{id}/month")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<BorrowingListResponse>> getAllLoanOfMonth(@PathVariable long id){
        try {
            LibraryJpa library = findOneLibrary.process(id);
            List<BorrowingListResponse> loanList = findAllBorrowingOfMonth.process(library)
                    .stream()
                    .map(b -> modelMapper.map(b, BorrowingListResponse.class))
                    .toList();
            return new ResponseEntity<>(loanList, HttpStatus.OK);
        } catch (RuntimeException e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Close the loan")
    @PostMapping("/close/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> closeLoan(@PathVariable long id) {
        try {
            final BorrowingJpa borrowing = findBorrowingById.process(id);

            borrowing.setActive(false);

            saveBorrowing.process(borrowing);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
