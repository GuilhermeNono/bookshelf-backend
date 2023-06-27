package br.com.projlib.bookshelf.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/v1/**")
                        .allowedOrigins("*")
                        .allowedMethods("*");
            }

            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addRedirectViewController("/", "/swagger-ui/index.html");
            }
        };
    }
}
