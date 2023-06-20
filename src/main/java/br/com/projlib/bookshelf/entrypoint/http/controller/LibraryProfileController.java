package br.com.projlib.bookshelf.entrypoint.http.controller;

import br.com.projlib.bookshelf.core.usecase.FindAllProfiles;
import br.com.projlib.bookshelf.core.usecase.FindOneProfile;
import br.com.projlib.bookshelf.entrypoint.http.response.LibProfileResponse;
import br.com.projlib.bookshelf.infra.gateway.userlibraryprofileJpa.UserLibraryProfileJpa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/library/profile")
@Tag(name = "Library Profile")
@Slf4j
public class LibraryProfileController {

    private final ModelMapper modelMapper;
    private final FindAllProfiles findAllProfiles;
    private final FindOneProfile findOneProfile;

    @Operation(summary = "Get all permissions of the all profile")
    @GetMapping
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<LibProfileResponse>> getAllProfiles() {
        try {
            List<LibProfileResponse> profiles = findAllProfiles.process().stream()
                    .map(p -> modelMapper.map(p, LibProfileResponse.class))
                    .toList();
            return new ResponseEntity<>(profiles, HttpStatus.OK);
        } catch (RuntimeException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Get all permissions of a profile by name")
    @GetMapping(value = "/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<LibProfileResponse> getAProfile(@PathVariable long id) {
        try {
            UserLibraryProfileJpa profile = findOneProfile.process(id);
            LibProfileResponse response = modelMapper.map(profile, LibProfileResponse.class);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
