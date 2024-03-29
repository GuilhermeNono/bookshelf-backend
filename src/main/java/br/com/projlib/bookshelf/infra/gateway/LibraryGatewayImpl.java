package br.com.projlib.bookshelf.infra.gateway;

import br.com.projlib.bookshelf.core.domain.UserAccount;
import br.com.projlib.bookshelf.core.gateway.LibraryGateway;
import br.com.projlib.bookshelf.core.usecase.FindAuthenticatedUserAccount;
import br.com.projlib.bookshelf.core.usecase.FindUserById;
import br.com.projlib.bookshelf.infra.command.LibraryUserInfo;
import br.com.projlib.bookshelf.infra.gateway.libraryjpa.LibraryJpa;
import br.com.projlib.bookshelf.infra.gateway.libraryjpa.LibraryRepository;
import br.com.projlib.bookshelf.infra.gateway.useraccountjpa.UserAccountJpa;
import br.com.projlib.bookshelf.infra.gateway.userlibraryjpa.UserLibraryJpa;
import br.com.projlib.bookshelf.infra.gateway.userlibraryjpa.UserLibraryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibraryGatewayImpl implements LibraryGateway {

    private final LibraryRepository libraryRepository;
    private final UserLibraryRepository userLibraryRepository;
    private final FindAuthenticatedUserAccount findAuthenticatedUserAccount;
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
    public List<LibraryUserInfo> getAllLibrariesOfUser(UserAccount userAccount) {
        try {
            UserAccountJpa user = findUserById.process(userAccount.getId());
            List<LibraryUserInfo> response = new ArrayList<>();

            for (UserLibraryJpa userLibraryJpa: user.getUserLibraries()) {
                response.add(modelMapper.map(userLibraryJpa, LibraryUserInfo.class));
            }
            return response;
        } catch (RuntimeException e){
            throw e;
        }
    }
}
