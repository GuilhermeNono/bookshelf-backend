package br.com.projlib.bookshelf.infra.gateway.borrowingjpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BorrowingRepository extends JpaRepository<BorrowingJpa, Long>, JpaSpecificationExecutor<BorrowingJpa> {
}
