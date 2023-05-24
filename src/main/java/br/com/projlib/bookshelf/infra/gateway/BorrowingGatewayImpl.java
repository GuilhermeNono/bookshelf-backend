package br.com.projlib.bookshelf.infra.gateway;

import br.com.projlib.bookshelf.core.gateway.BorrowingGateway;
import br.com.projlib.bookshelf.infra.gateway.borrowingjpa.BorrowingJpa;
import br.com.projlib.bookshelf.infra.gateway.borrowingjpa.BorrowingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public Optional<BorrowingJpa> findById(long id) {
        return borrowingRepository.findById(id);
    }

    @Override
    @Transactional
    public void save(BorrowingJpa borrowing) {
        borrowingRepository.save(borrowing);
    }

}
