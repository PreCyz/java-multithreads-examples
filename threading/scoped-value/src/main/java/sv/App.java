package sv;

import java.util.concurrent.StructuredTaskScope;

public class App {

    public static final ScopedValue<String> VALUE = ScopedValue.newInstance();

    static void main() {
        Game godOfWar = new Game(1000);
        Game witcher = new Game(2000);
        Game jediSurvivor = new Game(3000);

        IO.println("==================PLATFORM==================");
        platform(godOfWar, witcher, jediSurvivor);

        IO.println("==================DEAMON==================");
        deamons(godOfWar, witcher, jediSurvivor);

        IO.println("==================VIRTUAL==================");
        virtual(godOfWar, witcher, jediSurvivor);
    }

    private static void platform(Game godOfWar, Game witcher, Game jediSurvivor) {
        ScopedValue.where(VALUE, "God of War").run(godOfWar);
        ScopedValue.where(VALUE, "Witcher").run(witcher);
        ScopedValue.where(VALUE, "Jedi Survivor").run(jediSurvivor);
    }

    private static void deamons(Game godOfWar, Game witcher, Game jediSurvivor) {
        try {
            Thread gow = Thread.ofPlatform().daemon().name("demon-gow")
                    .start(() -> ScopedValue.where(VALUE, "God of War").run(godOfWar));
            Thread geralt = Thread.ofPlatform().daemon().name("demon-geralt")
                    .start(() -> ScopedValue.where(VALUE, "Witcher").run(witcher));
            Thread cal = Thread.ofPlatform().daemon().name("demon-cal")
                    .start(() -> ScopedValue.where(VALUE, "Jedi Survivor").run(jediSurvivor));

            gow.join();
            geralt.join();
            cal.join();
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void virtual(Game godOfWar, Game witcher, Game jediSurvivor) {
        try (var scope = StructuredTaskScope.open(StructuredTaskScope.Joiner.allSuccessfulOrThrow())) {
            scope.fork(() -> ScopedValue.where(VALUE, "God of War").run(godOfWar));
            scope.fork(() -> ScopedValue.where(VALUE, "Witcher").run(witcher));
            scope.fork(() -> ScopedValue.where(VALUE, "Jedi Survivor").run(jediSurvivor));

            scope.join();
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}
