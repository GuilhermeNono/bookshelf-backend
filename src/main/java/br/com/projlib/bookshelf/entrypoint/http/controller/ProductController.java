package br.com.projlib.bookshelf.entrypoint.http.controller;

import br.com.projlib.bookshelf.core.usecase.GetProd;
import br.com.projlib.bookshelf.entrypoint.http.response.ProductResponse;
import br.com.projlib.bookshelf.infra.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/product")
public class ProductController {

    private final GetProd getProd;

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping()
    public ResponseEntity<ProductResponse> getProd() {
            ProductResponse productResponse = ProductResponse.fromDomain(getProd.process());
            return ResponseEntity.ok(productResponse);
    }

}
