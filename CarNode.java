/**
 * Represents a single car in the inventory. Acts as a node in the doubly linked list
 * that forms the CarInventory.
 */
public class CarNode {
    String make;       // The make of the car (e.g., "Honda")
    String model;      // The model of the car (e.g., "Civic")
    int year;         // The year the car was manufactured
    double price;      // The price of the car
    int mileage;      // The mileage of the car
    String vin;        // The Vehicle Identification Number (VIN) of the car
    CarNode next;     // Reference to the next car in the inventory list
    CarNode prev;     // Reference to the previous car in the inventory list
    boolean available; // Indicates whether the car is currently available for sale

    /**
     * Constructor for creating a new CarNode.
     * 
     * @param make     The make of the car.
     * @param model    The model of the car.
     * @param year     The year of manufacture of the car.
     * @param price    The price of the car.
     * @param mileage  The mileage of the car.
     * @param vin      The Vehicle Identification Number (VIN) of the car.
     */
    public CarNode(String make, String model, int year, double price, int mileage, String vin) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.mileage = mileage;
        this.vin = vin;
        this.available = true; // By default, a newly added car is available.
    }

    /**
     * Returns a string representation of the CarNode, primarily for debugging.
     * 
     * @return A string containing the VIN, make, model, and availability of the car.
     */
    @Override
    public String toString() {
        return "CarNode [vin=" + vin + ", make=" + make + ", model=" + model + ", available="
                + available + "]";
    }
}