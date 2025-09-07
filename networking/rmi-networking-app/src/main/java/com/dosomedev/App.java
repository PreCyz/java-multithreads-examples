package com.dosomedev;

import java.io.PrintStream;

public class App {

    public static final PrintStream ERR = System.err;

    public static void main(String[] args) {
        // Differentiate between server and client.
        if (args.length > 0) {
            String firstParam = args[0].toLowerCase();

            switch (firstParam) {
                case "server":
                    CalculatorServer server = new CalculatorServer();
                    server.run();
                    break;

                case "client":
                    if (args.length > 1) {
                        String secondParam = args[1].toLowerCase();

                        // Start the client.
                        CalculatorClient client = new CalculatorClient();
                        client.run(secondParam);
                    } else {
                        ERR.println("Error: incomplete param 'client': [server|client <servername>]");
                    }
                    break;

                default:
                    ERR.println("Error: unknown param '" + firstParam + "': [server|client <servername>]");
                    break;
            }
        } else {
            ERR.println("Missing param: [server|client <servername>]");
        }
    }
}
