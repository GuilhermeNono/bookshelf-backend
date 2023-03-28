package br.com.projlib.bookshelf.infra.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class QueryCriteria implements Serializable {

    private FilterCriteria filter;
    private PageCriteria page;
    private SortCriteria sortCriteria;
    private Sort sort;

    public QueryCriteria withFilter(FilterCriteria filter) {
        this.setFilter(filter);
        return this;
    }

    public QueryCriteria withPage(PageCriteria page) {
        this.setPage(page);
        return this;
    }

    public QueryCriteria withSortCriteria(SortCriteria sort) {
        this.setSortCriteria(sort);
        return this;
    }

    public QueryCriteria withSort(Sort sort) {
        this.setSort(sort);
        return this;
    }

    public Pageable getPageable() {
        Sort sortProperties = null;
        if (Objects.nonNull(this.getSortCriteria())) {
            sortProperties = Sort.by(this.getSortCriteria().getSortDirection(), this.getSortCriteria().getSortAttribute());
        } else if (Objects.nonNull(this.getSort())) {
            sortProperties = this.getSort();
        }

        if (Objects.nonNull(this.getPage())) {
            if (Objects.nonNull(sortProperties)) {
                return PageRequest.of(this.getPage().getPage(), this.getPage().getSize(), sortProperties);
            } else {
                return PageRequest.of(this.getPage().getPage(), this.getPage().getSize());
            }

        } else {
            return PageRequest.of(0, 10);
        }
    }
}
