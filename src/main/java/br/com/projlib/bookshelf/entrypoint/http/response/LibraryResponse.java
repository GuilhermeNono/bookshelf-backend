package br.com.projlib.bookshelf.entrypoint.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibraryResponse {
    private String name;
    @JsonProperty(value = "institution_name")
    private String institutionName;
    @JsonProperty(value = "institution_cnpj")
    private String institutionCnpj;
}
