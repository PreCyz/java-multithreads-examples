package com.dosomedev;

import java.io.PrintStream;
import java.util.Stack;

public class Basket {

    public static final PrintStream OUT = System.out;
    private final Stack<String> plates = new Stack<>();

    public void addPlate(String plate) {
        this.plates.push(plate);
        OUT.println("Added plate: " + plate + ". Total number of plates: " + this.plates.size());
    }

    public String takePlate() {
        if (!this.plates.empty()) {
            String plate = this.plates.pop();
            OUT.println("Took plate: " + plate + ". Total number of plates: " + this.plates.size());
            return plate;
        } else {
            OUT.println("Tried to take plates, but no plates left.");
            return null;
        }
    }

    public void listPlates() {
        OUT.println("List of plates:");
        if (this.plates.size() > 0) {
            for (int i = this.plates.size() - 1; i >= 0; i--) {
                OUT.println("[" + i + "] " + this.plates.get(i));
            }
        } else {
            OUT.println("[] No plates left.");
        }
    }

    public String lookAtTopPlate() {
        if (!this.plates.empty()) {
            String plate = this.plates.peek();
            OUT.println("Look at top plate: " + plate);
            return plate;
        } else {
            OUT.println("Tried to look at the top plate, but no plates left.");
            return null;
        }
    }

    public void emptyBasket() {
        this.plates.clear();
        OUT.println("Emptied the basket.");
    }
}
