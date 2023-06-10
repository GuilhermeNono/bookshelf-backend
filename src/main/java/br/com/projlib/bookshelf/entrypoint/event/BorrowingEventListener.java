package br.com.projlib.bookshelf.entrypoint.event;

import br.com.projlib.bookshelf.core.usecase.SaveBorrowing;
import br.com.projlib.bookshelf.infra.gateway.borrowingjpa.BorrowingJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BorrowingEventListener {

    private final SaveBorrowing saveBorrowing;

    @EventListener
    public void eventListener(BorrowingJpa overdue) {
            overdue.setOverdue(true);
            saveBorrowing.process(overdue);
    }
}
