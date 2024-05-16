
public abstract class Vehicle {
    protected String make;
    protected String model;
    protected int year;
    protected double price;
    protected int mileage;
    protected String vin; 

    public Vehicle(String make, String model, int year, double price, int mileage, String vin) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.mileage = mileage;
        this.vin = vin;
    }

    public abstract void displayVehicleDetails();

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