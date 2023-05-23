package br.com.projlib.bookshelf.entrypoint.http.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
public class BorrowingCreateRequest {
    @NotNull
    private Date loanDate;
    @NotNull
    private Date returnDate;
    @NotNull
    private String bookCode;
    @NotNull
    private Long userId;
}
