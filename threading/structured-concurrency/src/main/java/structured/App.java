package structured;

import java.util.concurrent.StructuredTaskScope;

public class App {

    static void main() throws InterruptedException {
        IO.println("=========All=========");
        execute(); // == default;
        IO.println("=========Any=========");
        execute(StructuredTaskScope.Joiner.anySuccessfulResultOrThrow());
    }

    private static void execute() {
        execute(StructuredTaskScope.Joiner.allSuccessfulOrThrow());
    }

    private static void execute(StructuredTaskScope.Joiner joiner) {
        try (var scope = StructuredTaskScope.open(joiner)) {
            scope.fork(new Game("God of War", 1));
            scope.fork(new Game("Witcher", 2));
            scope.fork(new Game("Jedi Survivor", 3));

            scope.join();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
