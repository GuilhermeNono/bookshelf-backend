package br.com.projlib.bookshelf.entrypoint.http.request;

import br.com.projlib.bookshelf.infra.query.SearchCriteria;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SearchRequest {
    private List<SearchCriteria> searchCriteriaList;
    private String dataOption;
}
