package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.UserLibraryGateway;
import br.com.projlib.bookshelf.entrypoint.http.response.UserLibraryResponse;
import br.com.projlib.bookshelf.infra.gateway.userlibraryjpa.UserLibraryJpa;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindUserLibraryBySearchCriteria {
    private final UserLibraryGateway userLibraryGateway;
    private final ModelMapper modelMapper;

    public Page<UserLibraryResponse> process(Specification<UserLibraryJpa> spec, Pageable page){
        Page<UserLibraryJpa> all = userLibraryGateway.findAll(spec, page);
        return all.map(m -> modelMapper.map(m, UserLibraryResponse.class));
    }
}
