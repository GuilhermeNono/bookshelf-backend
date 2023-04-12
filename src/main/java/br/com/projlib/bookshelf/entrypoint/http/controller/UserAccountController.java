package br.com.projlib.bookshelf.entrypoint.http.controller;

import br.com.projlib.bookshelf.core.domain.Profile;
import br.com.projlib.bookshelf.core.usecase.CreateContact;
import br.com.projlib.bookshelf.core.usecase.CreateUser;
import br.com.projlib.bookshelf.core.usecase.FindAllUser;
import br.com.projlib.bookshelf.core.usecase.FindContactTypeById;
import br.com.projlib.bookshelf.core.usecase.FindProfileById;
import br.com.projlib.bookshelf.core.usecase.FindUserById;
import br.com.projlib.bookshelf.entrypoint.http.request.UserAccountCreateRequest;
import br.com.projlib.bookshelf.entrypoint.http.response.UserAccountResponse;
import br.com.projlib.bookshelf.infra.gateway.useraccountjpa.UserAccountJpa;
import br.com.projlib.bookshelf.infra.gateway.usercontact.UserContactJpa;
import br.com.projlib.bookshelf.infra.gateway.usercontacttype.UserContactTypeJpa;
import br.com.projlib.bookshelf.infra.gateway.userprofile.UserProfileJpa;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
@Tag(name = "User Account")
public class UserAccountController {

    private final FindAllUser findAllUser;
    private final CreateUser createUser;
    private final FindProfileById findProfileById;
    private final FindUserById findUserById;
    private final FindContactTypeById findContactTypeById;
    private final CreateContact createContact;

    @GetMapping
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<UserAccountResponse>> getAll() {
        final List<UserAccountResponse> responses = findAllUser.process().stream()
                .map(UserAccountResponse::fromDomain).toList();

        return ResponseEntity.ok(responses);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UserAccountResponse> createUser(@RequestBody @Valid UserAccountCreateRequest userAccountCreateRequest) {

        //Criando User Account

        final UserAccountJpa userAccount = new UserAccountJpa(
                userAccountCreateRequest.getCPF(),
                userAccountCreateRequest.getPassword()
        );
        final UserContactJpa userContactEmail = new UserContactJpa();
        final UserContactJpa userContactPhone = new UserContactJpa();

        userAccount.setActive(true);
        userAccount.setPersonName(userAccountCreateRequest.getFirstName()
                .concat(" ")
                .concat(userAccountCreateRequest.getLastName()));
        userAccount.setBirthDay(userAccountCreateRequest.getBirthDay());
        userAccount.setCreatedAt(LocalDateTime.now());
        userAccount.setUpdatedAt(LocalDateTime.now());

        final Profile profile = findProfileById.process(1);

        userAccount.setUserProfile(UserProfileJpa.fromDomain(profile));

        UserAccountJpa newUser = createUser.process(userAccount);

        // Criando User Contact - Email

        final UserContactTypeJpa EmailContactType = findContactTypeById.process(1);
        final UserContactTypeJpa PhoneContactType = findContactTypeById.process(2);

        userContactEmail.setActive(true);
        userContactEmail.setContact(userAccountCreateRequest.getEmail());
        userContactEmail.setCreatedAt(LocalDateTime.now());
        userContactEmail.setUpdatedAt(LocalDateTime.now());
        userContactEmail.setUserAccount(userAccount);
        userContactEmail.setUserContactType(EmailContactType);
        UserContactJpa emailContactCreated = createContact.process(userContactEmail);

        // Criando User Contact - Phone

        userContactPhone.setActive(true);
        userContactPhone.setContact(userAccountCreateRequest.getPhone());
        userContactPhone.setCreatedAt(LocalDateTime.now());
        userContactPhone.setUpdatedAt(LocalDateTime.now());
        userContactPhone.setUserAccount(userAccount);
        userContactPhone.setUserContactType(PhoneContactType);
        UserContactJpa phoneContactCreated = createContact.process(userContactPhone);

        List<UserContactJpa> contacts = new ArrayList<>();
        contacts.add(phoneContactCreated);
        contacts.add(emailContactCreated);

        final UserAccountJpa userCreated = findUserById.process(newUser.getId());
        userCreated.setUserContact(contacts);
        UserAccountJpa userUpdated = createUser.process(userCreated);

        final UserAccountResponse response = UserAccountResponse.fromDomain(userUpdated.toDomain());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
