package sv;

import java.time.Duration;
import java.time.LocalDateTime;

public class Game implements Runnable {

    private final int wait;

    public Game(int wait) {
        this.wait = wait;
    }

    @Override
    public void run() {
        LocalDateTime start = LocalDateTime.now();
        String title = App.VALUE.isBound() ? App.VALUE.get() : "NO-VALUE";
        Thread thread = Thread.currentThread();
        try {
            Thread.sleep(wait);

            System.out.printf("Game '%s' finished in %d seconds by thread: id=[%d] name=[%s], isVirtual=[%b], isDeamon=[%b].%n",
                    title, Duration.between(start, LocalDateTime.now()).toSeconds(), thread.threadId(), thread.getName(),
                    thread.isVirtual(), thread.isDaemon()
            );
        } catch (InterruptedException ex) {
            thread.interrupt();
            System.out.printf("Game %s interrupted after %d seconds.%n",
                    title, Duration.between(start, LocalDateTime.now()).toSeconds());
        }
    }
}
