package br.com.projlib.bookshelf.entrypoint.http.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
public class BorrowingCreateRequest {
    @NotNull
    private LocalDate loanDate;
    @NotNull
    private LocalDate returnDate;
    @NotNull
    private String bookCode;
    @NotNull
    private Long userId;
}
