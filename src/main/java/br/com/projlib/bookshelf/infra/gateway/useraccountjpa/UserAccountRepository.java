package br.com.projlib.bookshelf.infra.gateway.useraccountjpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccountJpa, Long> {
    List<UserAccountJpa> findAllByActiveIsTrue();

    Optional<UserAccountJpa> findByCpf(final String Cpf);

    @Query("select ua from UserAccountJpa ua Join ua.userContact uac where uac.contact like :email")
    Optional<UserAccountJpa> findByEmail(String email);

}
