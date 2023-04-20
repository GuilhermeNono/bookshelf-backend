package br.com.projlib.bookshelf.core.domain;

import br.com.projlib.bookshelf.infra.gateway.useraccountjpa.UserAccountJpa;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
public class LinkContactAccount {
    private String phone;
    private String email;
    private UserAccountJpa userAccount;
}
