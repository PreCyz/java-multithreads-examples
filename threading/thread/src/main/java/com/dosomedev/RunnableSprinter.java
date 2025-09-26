package com.dosomedev;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RunnableSprinter implements Runnable {
    private final String name;

    private final int stepTime;

    private final int fallsDown;

    @Override
    public void run() {
        System.out.printf("===> %s starts running!%n", this.name);

        for (int i = 1; i <= 10; i++) {
            System.out.printf("%s runs %sm.%n", this.name, i);

            if (i == this.fallsDown) {
                throw new RuntimeException("%s fell down!%n".formatted(this.name));
            }

            if (i == 10) {
                System.out.printf("===> %s finished!%n", this.name);
            }

            try {
                Thread.sleep(this.stepTime);
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
        }
    }
}
