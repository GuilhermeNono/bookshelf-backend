package br.com.projlib.bookshelf.entrypoint.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@JsonPropertyOrder({"name", "classroom", "period", "module", "active"})
public class BorrowingCourseResponse {
    @JsonProperty("name")
    String courseName;
    @JsonProperty("class")
    String courseClassRoom;
    @JsonProperty("active")
    boolean courseActive;
    @JsonProperty("period")
    String coursePeriod;
    @JsonProperty("module")
    String module;
}
