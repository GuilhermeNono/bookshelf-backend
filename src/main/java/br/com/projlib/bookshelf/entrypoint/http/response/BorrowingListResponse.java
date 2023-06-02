package br.com.projlib.bookshelf.entrypoint.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@JsonPropertyOrder({
                "id",
                "user_id",
                "user_name",
                "book_identifier",
                "book",
                "loan_date",
                "return_date",
                "renewal_date",
                "is_active",
                "user_courses"
})
public class BorrowingListResponse {

    long id;
    @JsonProperty("loan_date")
    LocalDate loanDate;
    @JsonProperty("return_date")
    LocalDate returnDate;
    @JsonProperty("renewal_date")
    LocalDate renewalDate;
    @JsonProperty("user_id")
    long userLibraryId;
    @JsonProperty("user_name")
    String userLibraryUserAccountPersonName;
    @JsonProperty("book")
    String bookCopyBookName;
    @JsonProperty("book_identifier")
    String bookCopyCode;
    @JsonProperty("is_active")
    boolean active;
    @JsonProperty("overdue")
    boolean overdue;
    @JsonProperty("user_courses")
    List<BorrowingCourseResponse> userLibraryCourses;

}
