package br.com.projlib.bookshelf.infra.gateway.borrowingjpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingRepository extends JpaRepository<BorrowingJpa, Long> {
}
