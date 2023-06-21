package br.com.projlib.bookshelf.entrypoint.http.controller;

import br.com.projlib.bookshelf.core.usecase.FindAllLibrariesOfUser;
import br.com.projlib.bookshelf.core.usecase.FindUserLibraryBySearchCriteria;
import br.com.projlib.bookshelf.entrypoint.http.response.ListUserLibraryResponse;
import br.com.projlib.bookshelf.infra.command.LibraryUserInfo;
import br.com.projlib.bookshelf.infra.command.UserLibraryDTO;
import br.com.projlib.bookshelf.infra.query.SearchCriteria;
import br.com.projlib.bookshelf.infra.specification.UserLibrarySpecificationBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@RestController
@RequestMapping("/api/v1/user/library")
@RequiredArgsConstructor
@Tag(name = "User Library")
@Slf4j
public class UserLibraryController {
    private final FindAllLibrariesOfUser findAllLibrariesOfUser;
    private final FindUserLibraryBySearchCriteria findUserLibraryBySearchCriteria;
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

    @Operation(summary = "Search user libraries")
    @PostMapping("/search")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Page<ListUserLibraryResponse>> searchUserLibs
            (@RequestParam(name = "pageNum",
                    defaultValue = "0") int pageNum,
             @RequestParam(name = "pageSize",
                     defaultValue = "10") int pageSize,
             @RequestBody UserLibraryDTO
                     userLibraryDTO){
            try {
                UserLibrarySpecificationBuilder builder = new
                        UserLibrarySpecificationBuilder();
                List<SearchCriteria> criteriaList =
                        userLibraryDTO.getSearchCriteriaList();
                if(criteriaList != null){
                    criteriaList.forEach(x->
                    {x.setDataOption(userLibraryDTO
                            .getDataOption());
                        builder.with(x);
                    });
                }

                Pageable page = PageRequest.of(pageNum, pageSize,
                        Sort.by("rmRa")
                                .ascending());

                Page<ListUserLibraryResponse> userLibPage =
                        findUserLibraryBySearchCriteria.process(builder.build(),
                                page);

                return new ResponseEntity<>(userLibPage, HttpStatus.OK);
            } catch (RuntimeException e) {
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                throw e;
            }
    }
}
