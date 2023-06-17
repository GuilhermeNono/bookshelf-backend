package br.com.projlib.bookshelf.entrypoint.http.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class PublisherRequest {
    @NotBlank
    String name;
}
