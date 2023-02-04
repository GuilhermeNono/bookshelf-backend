package br.com.projlib.bookshelf.infra.gateway;

import br.com.projlib.bookshelf.core.domain.Product;
import br.com.projlib.bookshelf.core.gateway.ProductGateway;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ProductGatewayImpl implements ProductGateway {
    @Override
    public Product getProd() {

        Product product = new Product("Todinho", 3L);

        return product;
    }
}
