package br.com.projlib.bookshelf.entrypoint.http.controller;

import br.com.projlib.bookshelf.core.usecase.FindBorrowingBySearchCriteria;
import br.com.projlib.bookshelf.core.usecase.GetAllBorrowings;
import br.com.projlib.bookshelf.entrypoint.http.response.BorrowingListResponse;
import br.com.projlib.bookshelf.entrypoint.http.response.ListBooksResponse;
import br.com.projlib.bookshelf.infra.command.BookDTO;
import br.com.projlib.bookshelf.infra.command.BorrowingDTO;
import br.com.projlib.bookshelf.infra.gateway.borrowingjpa.BorrowingJpa;
import br.com.projlib.bookshelf.infra.query.SearchCriteria;
import br.com.projlib.bookshelf.infra.specification.BookSpecificationBuilder;
import br.com.projlib.bookshelf.infra.specification.BorrowingSpecificationBuilder;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/borrowing")
@Tag(name = "Borrowing")
@Slf4j
public class BorrowingController {

    private final GetAllBorrowings getAllBorrowings;
    private final FindBorrowingBySearchCriteria findBorrowingBySearchCriteria;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<BorrowingListResponse>> getAllBorrowing() {
        try {
            List<BorrowingListResponse> response = getAllBorrowings.process()
                    .stream()
                    .map(b -> modelMapper.map(b, BorrowingListResponse.class))
                    .toList();

            return new ResponseEntity<>(response, HttpStatus.OK);
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

        Pageable page = PageRequest.of(pageNum, pageSize);

        Page<BorrowingListResponse> borrowingPage =
                findBorrowingBySearchCriteria.process(builder.build(),
                        page);

        return new ResponseEntity<>(borrowingPage, HttpStatus.OK);
    }
}
