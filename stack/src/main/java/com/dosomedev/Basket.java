package com.dosomedev;

import java.util.Stack;

public class Basket {


    private final Stack<String> plates = new Stack<>();

    public void addPlate(String plate) {
        this.plates.push(plate);
        IO.println("Added plate: " + plate + ". Total number of plates: " + this.plates.size());
    }

    public String takePlate() {
        if (!this.plates.empty()) {
            String plate = this.plates.pop();
            IO.println("Took plate: " + plate + ". Total number of plates: " + this.plates.size());
            return plate;
        } else {
            IO.println("Tried to take plates, but no plates left.");
            return null;
        }
    }

    public void listPlates() {
        IO.println("List of plates:");
        if (this.plates.size() > 0) {
            for (int i = this.plates.size() - 1; i >= 0; i--) {
                IO.println("[" + i + "] " + this.plates.get(i));
            }
        } else {
            IO.println("[] No plates left.");
        }
    }

    public String lookAtTopPlate() {
        if (!this.plates.empty()) {
            String plate = this.plates.peek();
            IO.println("Look at top plate: " + plate);
            return plate;
        } else {
            IO.println("Tried to look at the top plate, but no plates left.");
            return null;
        }
    }

    public void emptyBasket() {
        this.plates.clear();
        IO.println("Emptied the basket.");
    }
}
