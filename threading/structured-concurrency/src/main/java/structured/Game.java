package structured;

import java.time.Duration;
import java.time.LocalDateTime;

public class Game implements Runnable {

    private final int wait;
    private final String title;

    public Game(int wait, String title) {
        this.wait = wait;
        this.title = title;
    }

    @Override
    public void run() {
        LocalDateTime start = LocalDateTime.now();
        Thread thread = Thread.currentThread();
        try {
            Thread.sleep(wait);

            System.out.printf("Game '%s' finished in %d seconds by thread id %d, isVirtual[%b], isDeamon[%b].%n",
                    title, Duration.between(start, LocalDateTime.now()).toSeconds(), thread.threadId(), thread.isVirtual(), thread.isDaemon());
        } catch (InterruptedException ex) {
            thread.interrupt();
            System.out.printf("Game %s interrupted after %d seconds.%n",
                    title, Duration.between(start, LocalDateTime.now()).toSeconds());
        }
    }
}
