package br.com.projlib.bookshelf.core.domain;

import br.com.projlib.bookshelf.infra.gateway.useraccountjpa.UserAccountJpa;
import lombok.Value;

@Value
public class LinkContactAccount {

    String phone;
    String email;
    UserAccountJpa userAccount;

}
