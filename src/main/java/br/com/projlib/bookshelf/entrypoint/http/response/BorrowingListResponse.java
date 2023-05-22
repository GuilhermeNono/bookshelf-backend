package br.com.projlib.bookshelf.entrypoint.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@JsonPropertyOrder({
                "user_library_id",
                "user_library_name",
                "book",
                "loan_date",
                "return_date",
                "renewal_date",
                "is_active"
})
public class BorrowingListResponse {
    @JsonProperty("loan_date")
    Date loanDate;
    @JsonProperty("return_date")
    Date returnDate;
    @JsonProperty("renewal_date")
    Date renewalDate;
    @JsonProperty("user_library_id")
    long userLibraryId;
    @JsonProperty("user_library_name")
    String userLibraryUserAccountPersonName;
    @JsonProperty("book")
    String bookName;
    @JsonProperty("is_active")
    boolean active;

}
