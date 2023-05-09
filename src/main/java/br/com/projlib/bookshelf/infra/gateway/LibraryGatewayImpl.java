package br.com.projlib.bookshelf.infra.gateway;

import br.com.projlib.bookshelf.core.gateway.LibraryGateway;
import br.com.projlib.bookshelf.core.usecase.FindUserById;
import br.com.projlib.bookshelf.core.usecase.GetAllLibrariesOfUser;
import br.com.projlib.bookshelf.core.usecase.GetAuthenticatedUserAccount;
import br.com.projlib.bookshelf.core.usecase.ValidateAuthToken;
import br.com.projlib.bookshelf.infra.command.AuthenticationToken;
import br.com.projlib.bookshelf.infra.command.LibraryUserInfo;
import br.com.projlib.bookshelf.infra.gateway.libraryjpa.LibraryJpa;
import br.com.projlib.bookshelf.infra.gateway.libraryjpa.LibraryRepository;
import br.com.projlib.bookshelf.infra.gateway.useraccountjpa.UserAccountJpa;
import br.com.projlib.bookshelf.infra.gateway.userlibraryjpa.UserLibraryJpa;
import br.com.projlib.bookshelf.infra.gateway.userlibraryjpa.UserLibraryRepository;
import br.com.projlib.bookshelf.infra.query.UserAccountQuery;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.projlib.bookshelf.infra.config.AuthenticationFilter.TOKEN_BEARER;

@Service
@RequiredArgsConstructor
public class LibraryGatewayImpl implements LibraryGateway {

    private final LibraryRepository libraryRepository;
    private final UserLibraryRepository userLibraryRepository;
    private final GetAuthenticatedUserAccount getAuthenticatedUserAccount;
    private final FindUserById findUserById;
    private final ModelMapper modelMapper;
    @Override
    public List<LibraryJpa> getAll() {
        return libraryRepository.findAll();
    }

    @Override
    public Optional<LibraryJpa> getOne(long id) {
        return libraryRepository.findById(id);
    }

    @Override
    public List<List<LibraryUserInfo>> getAllLibrariesOfUser() {
        try {
            UserAccountQuery userQuery = getAuthenticatedUserAccount.process();
            UserAccountJpa user = findUserById.process(userQuery.getId());
            List<List<LibraryUserInfo>> response = new ArrayList<>();

            for (UserLibraryJpa userLibraryJpa: user.getUserLibraries()) {
                List<LibraryJpa> libraries = userLibraryRepository.findAllLibraryUser(userLibraryJpa);
                response.add(libraries.stream().map(lib -> modelMapper
                                .map(lib, LibraryUserInfo.class))
                        .collect(Collectors.toList()));
            }
            return response;
        } catch (RuntimeException e){
            throw e;
        }
    }
}
