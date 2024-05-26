/**
 * Represents a request for a test drive, associating a customer with a specific car.
 */
public class TestDriveRequest {
    Customer customer; // The customer making the test drive request
    Car car;         // The car the customer wants to test drive

    /**
     * Constructor for creating a new TestDriveRequest.
     * 
     * @param customer The customer making the request.
     * @param car     The car involved in the request.
     */
    public TestDriveRequest(Customer customer, Car car) {
        this.customer = customer; 
        this.car = car; 
    }
}