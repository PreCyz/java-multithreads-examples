package com.dosomedev;

public class App {
    public static void main(String[] args) {
        Basket basket = new Basket();

        basket.addPlate("Red Plate");
        basket.addPlate("Green Plate");
        basket.addPlate("Blue Plate");
        basket.addPlate("Pink Plate");
        basket.addPlate("Yellow Plate");

        basket.takePlate();

        basket.addPlate("White Plate");
        basket.addPlate("Violet Plate");

        basket.takePlate();
        basket.takePlate();
        basket.takePlate();

        basket.listPlates();

        basket.addPlate("White Plate");
        basket.addPlate("White Plate");
        basket.addPlate("White Plate");

        basket.listPlates();

        basket.takePlate();
        basket.takePlate();

        basket.listPlates();

        basket.lookAtTopPlate();

        basket.addPlate("Red Plate");

        basket.lookAtTopPlate();

        basket.takePlate();

        basket.lookAtTopPlate();

        basket.listPlates();

        basket.emptyBasket();

        basket.listPlates();
    }
}
