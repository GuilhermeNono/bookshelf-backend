package br.com.projlib.bookshelf.entrypoint.http.response;

import br.com.projlib.bookshelf.core.domain.Product;
import lombok.Value;

@Value
public class ProductResponse {
    String name;
    Long price;

    public static ProductResponse fromDomain(final Product product) {
        return new ProductResponse(product.getName(), product.getPrice());
    }
}
