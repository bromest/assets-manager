package com.pluralsight;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Create an ArrayList of Asset objects
        ArrayList<Asset> myAssets = new ArrayList<>();

        // Populate with at least two houses and two vehicles
        myAssets.add(new House("my primary home", "2018-05-12", new BigDecimal("350000.00"),
                "123 Maple Street", 1, 2200, 6000));
        myAssets.add(new House("my vacation home", "2021-08-20", new BigDecimal("210000.00"),
                "456 Beachside Drive", 2, 1400, 4500));

        myAssets.add(new Vehicle("Tom's truck", "2019-03-10", new BigDecimal("42000.00"),
                "Ford F-150", 2018, 115000));
        myAssets.add(new Vehicle("daily commuter", "2022-11-05", new BigDecimal("26000.00"),
                "Toyota Corolla", 2021, 45000));

        // Loop through the Asset collection and display information using instanceof and downcasting
        for (int i = 0; i < myAssets.size(); i++) {
            Asset asset = myAssets.get(i);
            String specificDetails = "";

            if (asset instanceof House) {
                House house = (House) asset;
                specificDetails = "House at " + house.getAddress();
            } else if (asset instanceof Vehicle) {
                Vehicle vehicle = (Vehicle) asset;
                specificDetails = "Vehicle: " + vehicle.getYear() + " " + vehicle.getMakeModel();
            }

            System.out.println("--------------------------------------------------");
            System.out.println("Description : " + asset.getDescription());
            System.out.println("Details     : " + specificDetails);
            System.out.println("Acquired On : " + asset.getDateAcquired());
            System.out.println("Paid Cost   : $" + asset.getOriginalCost());
            System.out.println("Calculated  : $" + asset.getValue());
        }
        System.out.println("--------------------------------------------------");
    }

}
