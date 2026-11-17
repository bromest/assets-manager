package com.pluralsight;

import java.math.BigDecimal;

public class House extends Asset {
    private String address;
    private int condition; // 1-excellent, 2-good, 3-fair, 4-poor
    private int squareFoot;
    private int lotSize;

    // Constructor
    public House(String description, String dateAcquired, BigDecimal originalCost,
                 String address, int condition, int squareFoot, int lotSize) {
        super(description, dateAcquired, originalCost);
        this.address = address;
        this.condition = condition;
        this.squareFoot = squareFoot;
        this.lotSize = lotSize;
    }

    // Getters and Setters
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public int getSquareFoot() {
        return squareFoot;
    }

    public void setSquareFoot(int squareFoot) {
        this.squareFoot = squareFoot;
    }

    public int getLotSize() {
        return lotSize;
    }

    public void setLotSize(int lotSize) {
        this.lotSize = lotSize;
    }

    @Override
    public BigDecimal getValue() {
        double ratePerSqFt;

        // Determine rate per square foot based on condition
        switch (condition) {
            case 1:
                ratePerSqFt = 180.00;
                break;
            case 2:
                ratePerSqFt = 130.00;
                break;
            case 3:
                ratePerSqFt = 90.00;
                break;
            case 4:
                ratePerSqFt = 80.00;
                break;
            default:
                ratePerSqFt = 80.00; // Default fallback to poor/standard
                break;
        }

        // Calculation: (squareFoot * rate) + (lotSize * 0.25)
        double baseValue = squareFoot * ratePerSqFt;
        double lotValue = lotSize * 0.25;

        return BigDecimal.valueOf(baseValue + lotValue);
    }
}
