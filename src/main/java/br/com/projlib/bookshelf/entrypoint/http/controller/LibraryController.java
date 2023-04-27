package br.com.projlib.bookshelf.entrypoint.http.controller;

import br.com.projlib.bookshelf.core.usecase.GetAllLibraries;
import br.com.projlib.bookshelf.core.usecase.GetOneLibrary;
import br.com.projlib.bookshelf.entrypoint.http.response.ListLibraryResponse;
import br.com.projlib.bookshelf.infra.gateway.libraryjpa.LibraryJpa;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/library")
@RequiredArgsConstructor
@Tag(name = "Library")
@Slf4j
public class LibraryController {

    private final GetAllLibraries getAllLibraries;
    private final GetOneLibrary getOneLibrary;

    private final ModelMapper modelMapper;

    /**
     *  <h1>Buscar todos os usuarios</h1>
     *  <br/>
     *  <p>Método responsavel por buscar todas as bibliotecas disponiveis no sistema.</p>
     *  @return Uma lista com todas as bibliotecas.
     *  @throws org.springframework.web.client.HttpClientErrorException.BadRequest
     *  @throws RuntimeException
     * */
    @GetMapping
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<ListLibraryResponse>> getAllLibraries() {
        try{
            List<ListLibraryResponse> libraries = getAllLibraries
                    .process().stream()
                    .map(lib -> modelMapper.map(lib, ListLibraryResponse.class))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(libraries, HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<ListLibraryResponse> getLibrary(@PathVariable long id) {
        try{
            LibraryJpa library = getOneLibrary.process(id);
            ListLibraryResponse libResponse = modelMapper.map(library, ListLibraryResponse.class);
            return new ResponseEntity<>(libResponse, HttpStatus.OK);
        }catch (RuntimeException e){
            log.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


}
