package structured;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Game implements Runnable {
    private final String title;
    private final long wait;
    private final TimeUnit timeUnit;

    public Game(String title, long wait) {
        this(title, wait, TimeUnit.SECONDS);
    }

    @Override
    public void run() {
        LocalDateTime start = LocalDateTime.now();
        Thread thread = Thread.currentThread();
        try {
            timeUnit.sleep(wait);

            System.out.printf("Game '%s' finished in %d seconds by thread id %d, isVirtual[%b], isDeamon[%b].%n",
                    title, Duration.between(start, LocalDateTime.now()).toSeconds(), thread.threadId(), thread.isVirtual(), thread.isDaemon());
        } catch (InterruptedException ex) {
            thread.interrupt();
            System.out.printf("Game %s interrupted after %d seconds.%n",
                    title, Duration.between(start, LocalDateTime.now()).toSeconds());
        }
    }
}
