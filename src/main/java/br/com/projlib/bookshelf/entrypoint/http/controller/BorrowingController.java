package br.com.projlib.bookshelf.entrypoint.http.controller;

import br.com.projlib.bookshelf.core.usecase.GetAllBorrowings;
import br.com.projlib.bookshelf.entrypoint.http.response.BorrowingListResponse;
import br.com.projlib.bookshelf.infra.gateway.borrowingjpa.BorrowingJpa;
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
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/borrowing")
@Tag(name = "Borrowing")
@Slf4j
public class BorrowingController {

    private final GetAllBorrowings getAllBorrowings;
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
}
