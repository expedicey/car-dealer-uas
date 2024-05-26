
import java.time.Year;

/**
 * Represents a car, extending the Vehicle class. Includes methods to calculate total price and 
 * depreciation and to check if a car is within a given budget.
 */
public class Car extends Vehicle {

    /**
     * Constructor for creating a new Car object.
     * 
     * @param make     The make of the car (e.g., "Toyota").
     * @param model    The model of the car (e.g., "Corolla").
     * @param year     The year the car was manufactured.
     * @param price    The initial price of the car.
     * @param mileage  The mileage of the car.
     * @param vin      The Vehicle Identification Number (VIN). 
     */
    public Car(String make, String model, int year, double price, int mileage, String vin) {
        super(make, model, year, price, mileage, vin); 
    }

    /**
     * Calculates the total price of the car, considering depreciation.
     * 
     * @return The total price of the car.
     */
    public double calculateTotalPrice() {
        double depreciation = calculateDepreciation();
        return price - depreciation; 
    }

    /**
     * Checks if the car's total price (including depreciation) is within a given budget.
     * 
     * @param budget The maximum budget for the car purchase.
     * @return True if the car is within the budget, false otherwise.
     */
    public boolean isWithinBudget(double budget) {
        return calculateTotalPrice() <= budget; 
    }

    /**
     * Calculates the car's depreciation based on its age and a depreciation rate of 45% per year.
     * 
     * @return The calculated depreciation amount.
     */
    @Override
    public double calculateDepreciation() {
        int currentYear = Year.now().getValue();
        int age = currentYear - this.getYear();
        double depreciationRate = 0.45;
        double depreciation = this.getPrice() * Math.pow(1 - depreciationRate, age);
        return depreciation; 
    }

    /**
     * Displays the car's make, model, and year.
     */
    @Override
    public void displayVehicleDetails() {
        System.out.println("Make: " + make + ", Model: " + model + ", Year: " + year);
    }  

}