package local.qqq.infrastructure.util;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

import local.qqq.domain.model.IdGenerator;

@Component
public class LocalIdGenerator implements IdGenerator {
    private static final AtomicInteger atomInt = new AtomicInteger(0);

    @Override
    public int newId() {
        return atomInt.incrementAndGet();
    }
}