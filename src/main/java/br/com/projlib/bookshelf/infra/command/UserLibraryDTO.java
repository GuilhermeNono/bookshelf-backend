package br.com.projlib.bookshelf.infra.command;

import br.com.projlib.bookshelf.infra.query.SearchCriteria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLibraryDTO {

    private List<SearchCriteria> searchCriteriaList;
    private String dataOption;
}
