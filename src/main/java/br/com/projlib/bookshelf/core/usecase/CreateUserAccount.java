package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.domain.Profile;
import br.com.projlib.bookshelf.entrypoint.http.request.UserAccountCreateRequest;
import br.com.projlib.bookshelf.infra.gateway.useraccountjpa.UserAccountJpa;
import br.com.projlib.bookshelf.infra.gateway.usercontact.UserContactJpa;
import br.com.projlib.bookshelf.infra.gateway.usercontacttype.UserContactTypeJpa;
import br.com.projlib.bookshelf.infra.gateway.userprofile.UserProfileJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateUserAccount {

    private final FindProfileById findProfileById;
    private final FindContactTypeById findContactTypeById;
    private final FindUserById findUserById;

    private final SaveUser saveUser;
    private final SaveContact saveContact;

    public UserAccountJpa process(UserAccountCreateRequest userAccountCreateRequest) {
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
        userAccount.setGender(userAccountCreateRequest.getGender());
        userAccount.setBirthDay(userAccountCreateRequest.getBirthDay());
        userAccount.setCreatedAt(LocalDateTime.now());
        userAccount.setUpdatedAt(LocalDateTime.now());

        final Profile profile = findProfileById.process(userAccountCreateRequest.getProfileId());

        userAccount.setUserProfile(UserProfileJpa.fromDomain(profile));

        UserAccountJpa newUser = saveUser.process(userAccount);

        // Criando User Contact - Email

        final UserContactTypeJpa EmailContactType = findContactTypeById.process(1);
        final UserContactTypeJpa PhoneContactType = findContactTypeById.process(2);

        userContactEmail.setActive(true);
        userContactEmail.setContact(userAccountCreateRequest.getEmail());
        userContactEmail.setCreatedAt(LocalDateTime.now());
        userContactEmail.setUpdatedAt(LocalDateTime.now());
        userContactEmail.setUserAccount(userAccount);
        userContactEmail.setUserContactType(EmailContactType);
        UserContactJpa emailContactCreated = saveContact.process(userContactEmail);

        // Criando User Contact - Phone

        userContactPhone.setActive(true);
        userContactPhone.setContact(userAccountCreateRequest.getPhone());
        userContactPhone.setCreatedAt(LocalDateTime.now());
        userContactPhone.setUpdatedAt(LocalDateTime.now());
        userContactPhone.setUserAccount(userAccount);
        userContactPhone.setUserContactType(PhoneContactType);
        UserContactJpa phoneContactCreated = saveContact.process(userContactPhone);

        List<UserContactJpa> contacts = new ArrayList<>();
        contacts.add(phoneContactCreated);
        contacts.add(emailContactCreated);

        final UserAccountJpa userCreated = findUserById.process(newUser.getId());
        userCreated.setUserContact(contacts);

        return saveUser.process(userCreated);
    }
}
