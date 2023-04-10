package br.com.projlib.bookshelf.infra.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Bookshelf API",
                version = "${api.version}",
                contact = @Contact(
                        name = "ProjLib", email = "projlib@support.com", url = "projlib.com.br"
                ),
                license = @License(
                        name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"
                ),
                description = "API oficial dos serviços da Bookshelf."
        ),
        servers = @Server(
                url = "https://bookshelf-preview.vercel.app/",
                description = "Site Preview"
        )
)
public class OpenAPISecurityConfiguration {
}
