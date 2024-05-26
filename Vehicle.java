/**
 * Abstract base class representing a generic vehicle. Provides basic attributes and methods common 
 * to all vehicle types (e.g., cars).
 */
public abstract class Vehicle {
    protected String make;        // The make of the vehicle (e.g., "Ford")
    protected String model;       // The model of the vehicle (e.g., "Focus")
    protected int year;          // The year the vehicle was manufactured
    protected double price;       // The price of the vehicle
    protected int mileage;       // The mileage of the vehicle
    protected String vin;         // The Vehicle Identification Number (VIN) 

    /**
     * Constructor for creating a new Vehicle object.
     * 
     * @param make     The make of the vehicle.
     * @param model    The model of the vehicle.
     * @param year     The year of manufacture.
     * @param price    The price of the vehicle.
     * @param mileage  The mileage of the vehicle.
     * @param vin      The Vehicle Identification Number (VIN).
     */
    public Vehicle(String make, String model, int year, double price, int mileage, String vin) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.mileage = mileage;
        this.vin = vin;
    }

    // Abstract methods that must be implemented by subclasses 
    public abstract void displayVehicleDetails();  // Displays the vehicle's details.
    public abstract double calculateDepreciation(); // Calculates the vehicle's depreciation.

    // Getters and Setters 
    public String getMake() {
        return this.make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}