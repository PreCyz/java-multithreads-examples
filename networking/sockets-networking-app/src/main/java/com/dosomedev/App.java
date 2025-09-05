package com.dosomedev;

public class App {
    public static void main(String[] args) {
        if (args.length > 0) {
            String firstParam = args[0].toLowerCase();

            switch (firstParam) {
                case "server":
                    // Start the server.
                    SimpleServer server = new SimpleServer();
                    server.run();
                    break;

                case "client":
                    if (args.length > 1) {
                        String secondParam = args[1].toLowerCase();

                        SimpleClient client = new SimpleClient();
                        client.run(secondParam);
                    } else {
                        System.err.println("Error: incomplete param 'client': [server|client <servername>]");
                    }
                    break;

                default:
                    System.err.println("Error: unknown param '" + firstParam + "': [server|client <servername>]");
                    break;
            }
        } else {
            System.err.println("Missing param: [server|client <servername>]");
        }
    }
}
