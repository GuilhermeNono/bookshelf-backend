package br.com.projlib.bookshelf.infra.gateway.userprofile;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfileJpa, Long> {
}
