package br.com.projlib.bookshelf.entrypoint.jobs;

import br.com.projlib.bookshelf.core.usecase.FindAllBorrowings;
import br.com.projlib.bookshelf.infra.gateway.borrowingjpa.BorrowingJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class BorrowingJob {

    private static final long SECONDS_SCHEDULE = 1000;
    private static final long MINUTE_SCHEDULE = SECONDS_SCHEDULE * 60;
    private static final long HOUR_SCHEDULE = MINUTE_SCHEDULE * 60;
    private static final long DAY_SCHEDULE = HOUR_SCHEDULE * 24;

    private final FindAllBorrowings findAllBorrowings;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Scheduled(fixedDelay = DAY_SCHEDULE)
    public void process() {
        List<BorrowingJpa> borrowingList = findAllBorrowings.process();
        for (BorrowingJpa borrowing: borrowingList) {
            final LocalDate date = borrowing.getReturnDate();
            final LocalDate currentDate = LocalDate.now();

            boolean isTimeToRenewal = date.isBefore(currentDate);

            if(isTimeToRenewal) {
                if(!borrowing.isOverdue()){
                    applicationEventPublisher.publishEvent(borrowing);
                }
            }
        }

    }

}
