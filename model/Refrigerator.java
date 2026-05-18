package com.App.model;

/**
 * Represents the Refrigerator entity with its specific properties.
 */
public class Refrigerator {
    private String brand; 
    private String coolingSystem;
    private String energyClass;
    private int doorsCount;
    private int netVolume;
    private double price;

    // Constructor
    public Refrigerator(String brand, String coolingSystem, String energyClass, int doorsCount, int netVolume, double price) { 
        this.brand = brand;
        this.coolingSystem = coolingSystem;
        this.energyClass = energyClass;
        this.doorsCount = doorsCount;
        this.netVolume = netVolume;
        this.price = price;
    }

    // Getters and Setters (Encapsulation)
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getCoolingSystem() { return coolingSystem; }
    public void setCoolingSystem(String coolingSystem) { this.coolingSystem = coolingSystem; }

    public String getEnergyClass() { return energyClass; }
    public void setEnergyClass(String energyClass) { this.energyClass = energyClass; }

    public int getDoorsCount() { return doorsCount; }
    public void setDoorsCount(int doorsCount) { this.doorsCount = doorsCount; }

    public int getNetVolume() { return netVolume; }
    public void setNetVolume(int netVolume) { this.netVolume = netVolume; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return "Refrigerator{" +
                "Brand='" + brand + '\'' +
                ", Cooling System='" + coolingSystem + '\'' +
                ", Energy Class='" + energyClass + '\'' +
                ", Doors Count=" + doorsCount +
                ", Net Volume=" + netVolume + "L" +
                ", Price=" + price + " USD" +
                '}';
    }
}