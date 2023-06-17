package br.com.projlib.bookshelf.entrypoint.http.controller;

import br.com.projlib.bookshelf.core.usecase.FindAllCategories;
import br.com.projlib.bookshelf.entrypoint.http.response.CategoryResponse;
import io.swagger.v3.oas.annotations.Operation;
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
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/category")
@Tag(name = "Category")
@Slf4j
public class CategoryController {

    private final FindAllCategories findAllCategories;
    private final ModelMapper modelMapper;

    @Operation(summary = "Get all categories")
    @GetMapping
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<CategoryResponse>> getall() {
        try {
            List<CategoryResponse> response = findAllCategories.process().stream()
                    .map(c -> modelMapper.map(c, CategoryResponse.class))
                    .toList();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
