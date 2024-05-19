
public class CarNode {
    String make;
    String model;
    int year;
    double price;
    int mileage;
    String vin;
    CarNode next;
    CarNode prev;
    boolean available;

    public CarNode(String make, String model, int year, double price, int mileage, String vin) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.mileage = mileage;
        this.vin = vin;
        this.available = true; 
    }

    @Override
    public String toString() {
        return "CarNode [vin=" + vin + ", make=" + make + ", model=" + model + ", available=" + available + "]";
    }
}