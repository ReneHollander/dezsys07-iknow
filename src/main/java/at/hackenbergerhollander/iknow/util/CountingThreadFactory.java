package at.hackenbergerhollander.iknow.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class CountingThreadFactory implements ThreadFactory {

    private String name;
    private AtomicInteger count;

    public CountingThreadFactory(String name) {
        this.name = name;
        count = new AtomicInteger(0);
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, String.format(name, count.incrementAndGet()));
    }
}
