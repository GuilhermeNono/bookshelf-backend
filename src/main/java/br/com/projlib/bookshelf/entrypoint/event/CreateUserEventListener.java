package br.com.projlib.bookshelf.entrypoint.event;

import br.com.projlib.bookshelf.core.domain.Contact;
import br.com.projlib.bookshelf.core.usecase.CreateUserContact;
import br.com.projlib.bookshelf.core.domain.LinkContactAccount;
import br.com.projlib.bookshelf.core.usecase.FindContactTypeById;
import br.com.projlib.bookshelf.infra.gateway.useraccountjpa.UserAccountJpa;
import br.com.projlib.bookshelf.infra.gateway.useraccountjpa.UserAccountRepository;
import br.com.projlib.bookshelf.infra.gateway.usercontact.UserContactJpa;
import br.com.projlib.bookshelf.infra.gateway.usercontact.UserContactRepository;
import br.com.projlib.bookshelf.infra.gateway.usercontacttype.UserContactTypeJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CreateUserEventListener {

    private final FindContactTypeById findContactTypeById;
    private final CreateUserContact createUserContact;
    private final UserAccountRepository userAccountRepository;

    @TransactionalEventListener
    public void eventListener(final LinkContactAccount linkContactAccount) {

        List<UserContactJpa> contacts = new ArrayList<>();

        if(Objects.nonNull(linkContactAccount.getPhone())){

            UserContactJpa userContact = new UserContactJpa();

            userContact.setCreatedAt(LocalDateTime.now());
            userContact.setUpdatedAt(LocalDateTime.now());

            UserContactTypeJpa contactType = UserContactTypeJpa.fromDomain(findContactTypeById.process(1));

            userContact.setUserAccount(linkContactAccount.getUserAccount());
            userContact.setContact(linkContactAccount.getEmail());
            userContact.setActive(true);
            userContact.setUserContactType(contactType);

            UserContactJpa contact1 = createUserContact.process(userContact);
            contacts.add(contact1);
        }

        if(Objects.nonNull(linkContactAccount.getEmail())){
            UserContactJpa userContact = new UserContactJpa();

            userContact.setCreatedAt(LocalDateTime.now());
            userContact.setUpdatedAt(LocalDateTime.now());

            UserContactTypeJpa contactType = UserContactTypeJpa.fromDomain(findContactTypeById.process(2));

            userContact.setUserAccount(linkContactAccount.getUserAccount());
            userContact.setContact(linkContactAccount.getEmail());
            userContact.setActive(true);
            userContact.setUserContactType(contactType);

            UserContactJpa contact2 = createUserContact.process(userContact);
            contacts.add(contact2);
        }

        linkContactAccount.getUserAccount().setUserContact(contacts);
        UserAccountJpa userAccount = linkContactAccount.getUserAccount();
        userAccountRepository.save(userAccount);
    }
}
