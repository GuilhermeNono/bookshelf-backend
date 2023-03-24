package br.com.projlib.bookshelf.infra.query;

import org.springframework.data.domain.Sort;

import java.io.Serializable;

public class SortCriteria implements Serializable {

    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private String sortAttribute = "id";

    private SortCriteria() {
        super();
    }

    protected SortCriteria(Sort.Direction sortOrder, String sortAttribute) {
        this();
        this.setSortDirection(sortOrder);
        this.setSortAttribute(sortAttribute);
    }

    public Sort.Direction getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(Sort.Direction sortDirection) {
        this.sortDirection = sortDirection;
    }

    public String getSortAttribute() {
        return sortAttribute;
    }

    public void setSortAttribute(String sortAttribute) {
        this.sortAttribute = sortAttribute;
    }
}
