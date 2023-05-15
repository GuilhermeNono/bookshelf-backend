package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.BookGateway;
import br.com.projlib.bookshelf.entrypoint.http.response.ListBooksResponse;
import br.com.projlib.bookshelf.infra.gateway.bookjpa.BookJpa;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindBookBySearchCriteria {
    private final BookGateway bookGateway;
    private final ModelMapper modelMapper;

    public Page<ListBooksResponse> process(Specification<BookJpa> spec, Pageable page){
        Page<BookJpa> all = bookGateway.findAll(spec, page);
        return all.map(m -> modelMapper.map(m, ListBooksResponse.class));
    }
}
