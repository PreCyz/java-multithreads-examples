package pawg.dosomedev.structured;

import java.util.concurrent.StructuredTaskScope;

public class App {

    static void main() {
        IO.println("=========All=========");
        execute(); // == default;
        IO.println("=========Any=========");
        execute(StructuredTaskScope.Joiner.anySuccessfulResultOrThrow()); // == default;
    }

    private static void execute() {
        execute(StructuredTaskScope.Joiner.allSuccessfulOrThrow());
    }

    private static void execute(StructuredTaskScope.Joiner joiner) {
        try (var scope = StructuredTaskScope.open(joiner)) {
            scope.fork(new Game(1000, "God of War"));
            scope.fork(new Game(2000, "Witcher"));
            scope.fork(new Game(3000, "Jedi Survivor"));

            scope.join();
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

}
