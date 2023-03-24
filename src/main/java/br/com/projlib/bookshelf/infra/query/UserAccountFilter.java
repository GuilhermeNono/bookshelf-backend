package br.com.projlib.bookshelf.infra.query;

import lombok.Value;

@Value
public class UserAccountFilter implements FilterCriteria {
    String username;
}
