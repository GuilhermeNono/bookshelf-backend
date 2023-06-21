package br.com.projlib.bookshelf.entrypoint.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@JsonPropertyOrder({"userLibId", "rmRa", "profilePicture", "active", "account", "courses"})
public class ListUserLibraryResponse {
    @JsonProperty("userLibId")
    long id;
    @JsonProperty("account")
    UserAccountRawResponse userAccount;
    String profilePicture;
    String rmRa;
    boolean active;
    List<CourseResponse> courses;
}
