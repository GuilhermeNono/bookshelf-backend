package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.BorrowingGateway;
import br.com.projlib.bookshelf.entrypoint.http.response.BorrowingListResponse;
import br.com.projlib.bookshelf.infra.gateway.borrowingjpa.BorrowingJpa;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindBorrowingBySearchCriteria {
    private final BorrowingGateway borrowingGateway;
    private final ModelMapper modelMapper;

    public Page<BorrowingListResponse> process(Specification<BorrowingJpa> spec, Pageable page){
        Page<BorrowingJpa> all = borrowingGateway.findAll(spec, page);
        return all.map(m -> modelMapper.map(m, BorrowingListResponse.class));
    }
}
