package com.pluralsight;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Vehicle extends Asset {
    private String makeModel;
    private int year;
    private int odometer;

    // Constructor
    public Vehicle(String description, String dateAcquired, BigDecimal originalCost,
                   String makeModel, int year, int odometer) {
        super(description, dateAcquired, originalCost);
        this.makeModel = makeModel;
        this.year = year;
        this.odometer = odometer;
    }

    // Getters and Setters
    public String getMakeModel() {
        return makeModel;
    }

    public void setMakeModel(String makeModel) {
        this.makeModel = makeModel;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    @Override
    public BigDecimal getValue() {
        int currentYear = LocalDate.now().getYear();
        int age = currentYear - year;
        BigDecimal finalValue;

        // 1. Determine base depreciated value based on age
        if (age <= 3) {
            // 3% reduced value of cost per year
            // Value remaining = Cost * (1 - 0.03 * age)
            double multiplier = 1.0 - (0.03 * age);
            finalValue = getOriginalCost().multiply(BigDecimal.valueOf(multiplier));
        } else if (age >= 4 && age <= 6) {
            // 6% reduced value of cost per year
            double multiplier = 1.0 - (0.06 * age);
            finalValue = getOriginalCost().multiply(BigDecimal.valueOf(multiplier));
        } else if (age >= 7 && age <= 10) {
            // 8% reduced value of cost per year
            double multiplier = 1.0 - (0.08 * age);
            finalValue = getOriginalCost().multiply(BigDecimal.valueOf(multiplier));
        } else {
            // over 10 years old - flat $1000.00
            finalValue = BigDecimal.valueOf(1000.00);
        }

        // Ensure value doesn't drop below zero due to aggressive depreciation percentages
        if (finalValue.compareTo(BigDecimal.ZERO) < 0) {
            finalValue = BigDecimal.ZERO;
        }

        // 2. Check odometer mileage penalty
        if (odometer > 100000) {
            String lowerCaseModel = makeModel.toLowerCase();
            // Check if makeModel contains "honda" or "toyota" (case-insensitive check)
            boolean isHondaOrToyota = lowerCaseModel.contains("honda") || lowerCaseModel.contains("toyota");

            if (!isHondaOrToyota) {
                // Reduce final value by 25%
                finalValue = finalValue.multiply(BigDecimal.valueOf(0.75));
            }
        }

        return finalValue;
    }
}
