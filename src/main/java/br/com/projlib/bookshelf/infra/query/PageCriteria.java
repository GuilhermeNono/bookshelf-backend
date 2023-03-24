package br.com.projlib.bookshelf.infra.query;

import java.io.Serializable;

public class PageCriteria implements Serializable {

    private int page = 0;
    private int size = 10;

    public PageCriteria() {
        super();
    }

    public PageCriteria(Integer page, Integer size) {
        this();
        this.setPage(page);
        this.setSize(size);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
