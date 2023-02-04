package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.domain.Product;
import br.com.projlib.bookshelf.core.gateway.ProductGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetProd {

    private final ProductGateway productGateway;

    public Product process(){
        return productGateway.getProd();
    }
}
