package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.BookCopyGateway;
import br.com.projlib.bookshelf.entrypoint.http.response.BookCopyResponse;
import br.com.projlib.bookshelf.infra.gateway.bookcopyjpa.BookCopyJpa;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindBookCopyBySearchCriteria {
    private final BookCopyGateway bookCopyGateway;
    private final ModelMapper modelMapper;

    public Page<BookCopyResponse> process(Specification<BookCopyJpa> spec, Pageable page){
        Page<BookCopyJpa> all = bookCopyGateway.findAll(spec, page);
        return all.map(m -> modelMapper.map(m, BookCopyResponse.class));
    }
}
