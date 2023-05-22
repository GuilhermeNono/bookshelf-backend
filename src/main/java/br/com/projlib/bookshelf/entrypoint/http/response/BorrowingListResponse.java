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
                "id",
                "user_id",
                "user_name",
                "book_identifier",
                "book",
                "loan_date",
                "return_date",
                "renewal_date",
                "is_active"
})
public class BorrowingListResponse {

    long id;
    @JsonProperty("loan_date")
    Date loanDate;
    @JsonProperty("return_date")
    Date returnDate;
    @JsonProperty("renewal_date")
    Date renewalDate;
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

}
