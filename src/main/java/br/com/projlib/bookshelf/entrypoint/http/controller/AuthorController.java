package br.com.projlib.bookshelf.entrypoint.http.controller;

import br.com.projlib.bookshelf.core.usecase.FindAllAuthor;
import br.com.projlib.bookshelf.entrypoint.http.response.AuthorResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/author")
@Tag(name = "Author")
@Slf4j
public class AuthorController {

    private final FindAllAuthor findAllAuthor;

    private final ModelMapper modelMapper;

    public ResponseEntity<List<AuthorResponse>> getAll() {
        try{
            List<AuthorResponse> responses = findAllAuthor.process().stream()
                    .map(a -> modelMapper.map(a, AuthorResponse.class))
                    .toList();
            return new ResponseEntity<>(responses, HttpStatus.OK);
        } catch (RuntimeException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

