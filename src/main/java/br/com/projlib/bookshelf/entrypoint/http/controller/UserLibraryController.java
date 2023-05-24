package br.com.projlib.bookshelf.entrypoint.http.controller;

import br.com.projlib.bookshelf.core.usecase.FindAllLibrariesOfUser;
import br.com.projlib.bookshelf.infra.command.LibraryUserInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/user/library")
@RequiredArgsConstructor
@Tag(name = "User Library")
@Slf4j
public class UserLibraryController {
    private final FindAllLibrariesOfUser findAllLibrariesOfUser;
    @Operation(summary = "Get all libraries of the authenticated user")
    @GetMapping
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<LibraryUserInfo>> getAllUserLibrary(){
        try {
            return new ResponseEntity<>(null, HttpStatus.OK) ;
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
