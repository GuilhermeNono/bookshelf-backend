package br.com.projlib.bookshelf.entrypoint.http.response;

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
@JsonPropertyOrder({"id", "name", "classroom", "active", "period", "module"})
public class CourseResponse {
    long id;
    String name;
    String classroom;
    boolean active;
    String period;
    String module;
}
