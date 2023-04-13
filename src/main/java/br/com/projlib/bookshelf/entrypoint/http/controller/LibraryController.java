package br.com.projlib.bookshelf.entrypoint.http.controller;

import br.com.projlib.bookshelf.core.usecase.GetAllLibraries;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/library")
@RequiredArgsConstructor
public class LibraryController {

    private final GetAllLibraries getAllLibraries;

    @GetMapping
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<?> getAllLibraries() {
        return getAllLibraries.process();
    }
}
