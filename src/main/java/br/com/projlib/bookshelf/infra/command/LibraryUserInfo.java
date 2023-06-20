package br.com.projlib.bookshelf.infra.command;

import br.com.projlib.bookshelf.entrypoint.http.response.LibraryPermissionsResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@JsonPropertyOrder({"userLibraryId", "libraryId", "library", "profile", "permissions"})
public class LibraryUserInfo {

    @JsonProperty("userLibraryId")
    long id;
    @JsonProperty("library")
    String libraryName;
    @JsonProperty("libraryId")
    long libraryId;
    @JsonProperty("profile")
    String profileName;
    @JsonProperty("permissions")
    List<LibraryPermissionsResponse> profileLibraryPermissions;

}
