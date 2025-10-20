package com.dosomedev;

import java.util.ArrayDeque;

public class Basket {

    private ArrayDeque<String> plates = new ArrayDeque<>();

    public void addTopPlate(String plate) {
        this.plates.addFirst(plate);
        IO.println("Added top plate: " + plate
                           + ". Total number of plates: " + this.plates.size());
    }

    public void addBottomPlate(String plate) {
        this.plates.addLast(plate);
        IO.println("Added bottom plate: " + plate
                           + ". Total number of plates: " + this.plates.size());
    }

    public String takeTopPlate() {
        String plate = this.plates.pollFirst();
        if (plate != null) {
            IO.println("Took top plate: " + plate
                               + ". Total number of plates: " + this.plates.size());
        } else {
            IO.println("Tried to take top plate, but no plates left.");
        }
        return plate;
    }

    public String takeBottomPlate() {
        String plate = this.plates.pollLast();
        if (plate != null) {
            IO.println("Took bottom plate: " + plate
                               + ". Total number of plates: " + this.plates.size());
        } else {
            IO.println("Tried to take bottom plate, but no plates left.");
        }
        return plate;
    }

    public void listPlates() {
        IO.println("List of plates:");
        if (this.plates.size() > 0) {
            int index = this.plates.size() - 1;
            for (String plate : this.plates) {
                IO.println("[" + index-- + "] " + plate);
            }
        } else {
            IO.println("[] No plates left.");
        }
    }

    public String lookAtTopPlate() {
        if (!this.plates.isEmpty()) {
            String plate = this.plates.peekFirst();
            IO.println("Look at top plate: " + plate);
            return plate;
        } else {
            IO.println("Tried to look at the top plate, but no plates left.");
            return null;
        }
    }

    public String lookAtBottomPlate() {
        if (!this.plates.isEmpty()) {
            String plate = this.plates.peekLast();
            IO.println("Look at bottom plate: " + plate);
            return plate;
        } else {
            IO.println("Tried to look at the bottom plate, but no plates left.");
            return null;
        }
    }

    public void emptyBasket() {
        this.plates.clear();
        IO.println("Emptied the basket.");
    }
}
