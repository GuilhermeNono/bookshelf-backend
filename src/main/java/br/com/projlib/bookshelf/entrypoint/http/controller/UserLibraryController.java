package br.com.projlib.bookshelf.entrypoint.http.controller;

import br.com.projlib.bookshelf.core.usecase.CreateUserAccount;
import br.com.projlib.bookshelf.core.usecase.FindCourseById;
import br.com.projlib.bookshelf.core.usecase.FindLibraryById;
import br.com.projlib.bookshelf.core.usecase.FindLibraryProfileById;
import br.com.projlib.bookshelf.core.usecase.FindUserById;
import br.com.projlib.bookshelf.core.usecase.FindUserLibraryBySearchCriteria;
import br.com.projlib.bookshelf.core.usecase.SaveUserLibrary;
import br.com.projlib.bookshelf.entrypoint.http.request.CreateUserLibraryRequest;
import br.com.projlib.bookshelf.entrypoint.http.response.ListUserLibraryResponse;
import br.com.projlib.bookshelf.entrypoint.http.response.UserLibraryCreatedResponse;
import br.com.projlib.bookshelf.infra.command.LibraryUserInfo;
import br.com.projlib.bookshelf.infra.command.UserLibraryDTO;
import br.com.projlib.bookshelf.infra.gateway.coursejpa.CourseJpa;
import br.com.projlib.bookshelf.infra.gateway.libraryjpa.LibraryJpa;
import br.com.projlib.bookshelf.infra.gateway.useraccountjpa.UserAccountJpa;
import br.com.projlib.bookshelf.infra.gateway.userlibraryjpa.UserLibraryJpa;
import br.com.projlib.bookshelf.infra.gateway.userlibraryprofileJpa.UserLibraryProfileJpa;
import br.com.projlib.bookshelf.infra.query.SearchCriteria;
import br.com.projlib.bookshelf.infra.specification.UserLibrarySpecificationBuilder;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/user/library")
@RequiredArgsConstructor
@Tag(name = "User Library")
@Slf4j
public class UserLibraryController {
    private final FindUserById findUserById;
    private final FindUserLibraryBySearchCriteria findUserLibraryBySearchCriteria;
    private final FindLibraryProfileById findLibraryProfileById;
    private final FindCourseById findCourseById;
    private final FindLibraryById findLibraryById;
    private final SaveUserLibrary saveUserLibrary;
    private final ModelMapper modelMapper;
    private final CreateUserAccount createUserAccount;
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

    @Operation(summary = "Create a new library user")
    @PostMapping
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<UserLibraryCreatedResponse> createUser(@RequestBody @Valid CreateUserLibraryRequest userLibraryRequest) {

        try {
            UserAccountJpa userAccount = null;
            final UserLibraryJpa userLibrary = new UserLibraryJpa();
            final List<CourseJpa> courses = new ArrayList<>();

            if(Objects.isNull(userLibraryRequest.getAccountId())) {
                userAccount = createUserAccount.process(userLibraryRequest.getAccount());
            } else {
                userAccount = findUserById.process(userLibraryRequest.getAccountId());
            }

            userLibrary.setUserAccount(userAccount);

            if(userLibraryRequest.getRmRa().isEmpty() ||userLibraryRequest.getRmRa().isBlank()) {
                throw new RuntimeException("O RM/RA não pode ser vazio");
            }

            userLibrary.setRmRa(userLibraryRequest.getRmRa());
            userLibrary.setProfilePicture(userLibraryRequest.getProfilePicture());

            UserLibraryProfileJpa profile = findLibraryProfileById.process(userLibraryRequest.getLibProfileId());

            if(Objects.isNull(profile)) {
                throw new RuntimeException("Id de profile não encontrado");
            }

            userLibrary.setProfile(profile);

            LibraryJpa library = findLibraryById.process(userLibraryRequest.getLibId());

            if(Objects.isNull(library)) {
                throw new RuntimeException("Id da biblioteca não encontrado");
            }

            userLibrary.setLibrary(library);

            userLibrary.setActive(true);
            userLibrary.setCreatedAt(LocalDateTime.now());
            userLibrary.setUpdatedAt(LocalDateTime.now());

            for (Long id: userLibraryRequest.getCoursesId()) {
                CourseJpa course = findCourseById.process(id);
                if(Objects.nonNull(course)) {
                    courses.add(course);
                }
            }

            userLibrary.setCourses(courses);

            UserLibraryJpa newUserLibrary = saveUserLibrary.process(userLibrary);
            UserLibraryCreatedResponse resp = modelMapper.map(newUserLibrary, UserLibraryCreatedResponse.class);

            return new ResponseEntity<>(resp, HttpStatus.CREATED);

        } catch (RuntimeException e) {
            log.warn(e.getMessage());
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
