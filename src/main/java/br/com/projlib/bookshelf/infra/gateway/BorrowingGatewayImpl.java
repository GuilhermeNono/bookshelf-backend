package br.com.projlib.bookshelf.infra.gateway;

import br.com.projlib.bookshelf.core.gateway.BorrowingGateway;
import br.com.projlib.bookshelf.infra.gateway.bookjpa.BookJpa;
import br.com.projlib.bookshelf.infra.gateway.borrowingjpa.BorrowingJpa;
import br.com.projlib.bookshelf.infra.gateway.borrowingjpa.BorrowingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowingGatewayImpl implements BorrowingGateway {

    private final BorrowingRepository borrowingRepository;

    @Override
    public List<BorrowingJpa> findAll() {
        return borrowingRepository.findAll();
    }

    @Override
    public Page<BorrowingJpa> findAll(Specification<BorrowingJpa> spec, Pageable page) {
        return borrowingRepository.findAll(spec, page);
    }
    @Override
    public List<BorrowingJpa> getAll() {
        return borrowingRepository.findAll();
    }
}
