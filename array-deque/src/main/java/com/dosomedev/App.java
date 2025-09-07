package com.dosomedev;

public class App {
    public static void main(String[] args) {
        Basket basket = new Basket();

        basket.addTopPlate("Red Plate");
        basket.addTopPlate("Orange Plate");
        basket.addTopPlate("Pink Plate");
        basket.addBottomPlate("Blue Plate");
        basket.addBottomPlate("Navy Plate");
        basket.addBottomPlate("Azure Plate");

        basket.takeTopPlate();
        basket.takeTopPlate();
        basket.takeBottomPlate();
        basket.takeBottomPlate();

        basket.addTopPlate("White Plate");
        basket.addTopPlate("White Plate");
        basket.addTopPlate("White Plate");

        basket.takeTopPlate();
        basket.takeTopPlate();
        basket.takeTopPlate();


        basket.listPlates();

        basket.addTopPlate("Gray Plate");
        basket.addTopPlate("Gray Plate");
        basket.addTopPlate("Gray Plate");

        basket.listPlates();

        basket.addBottomPlate("Green Plate");
        basket.addBottomPlate("Green Plate");
        basket.addBottomPlate("Green Plate");

        basket.listPlates();

        basket.takeTopPlate();
        basket.takeBottomPlate();
        basket.takeBottomPlate();

        basket.listPlates();


        basket.lookAtTopPlate();
        basket.lookAtBottomPlate();

        basket.addTopPlate("Black Plate");
        basket.addBottomPlate("Black Plate");

        basket.lookAtTopPlate();
        basket.lookAtTopPlate();
        basket.lookAtBottomPlate();
        basket.lookAtBottomPlate();

        basket.takeTopPlate();
        basket.takeBottomPlate();

        basket.lookAtTopPlate();
        basket.lookAtBottomPlate();


        basket.listPlates();

        basket.emptyBasket();

        basket.listPlates();
    }
}
