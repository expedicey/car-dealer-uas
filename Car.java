
import java.time.Year;

public class Car extends Vehicle {
    public Car(String make, String model, int year, double price, int mileage, String vin) {
        super(make, model, year, price, mileage, vin);
    }

    public double calculateDepreciation() {
        int currentYear = Year.now().getValue();
        int age = currentYear - this.getYear();
        double depreciationRate = 0.45;
        double depreciation = this.getPrice() * Math.pow(1 - depreciationRate, age);
        return depreciation; 
    }

    public double calculateTotalPrice() {
        double depreciation = calculateDepreciation();
        return price - depreciation;
    }

    public boolean isWithinBudget(double budget) {
        return calculateTotalPrice() <= budget;
    }

    @Override
    public void displayVehicleDetails() {
        System.out.println("Make: " + make + ", Model: " + model + ", Year: " + year);
    }
    

}