package br.com.projlib.bookshelf.infra.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@JsonPropertyOrder({"userLibraryId", "libraryId", "library", "profile"})
public class LibraryUserInfo {

    @JsonProperty("userLibraryId")
    long id;
    @JsonProperty("library")
    String libraryName;
    @JsonProperty("libraryId")
    long libraryId;
    @JsonProperty("profile")
    String profileName;
}
