package br.com.projlib.bookshelf.infra.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPISecurityConfiguration {

    @Value("${api.version}")
    private String version;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Bookshelf API")
                        .version(version)
                        .description("API oficial dos serviços da Bookshelf.")
                        .contact(new Contact()
                                .name("ProjLib")
                                .url("projlib.com.br")
                                .email("projlib@support.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));

    }
}
