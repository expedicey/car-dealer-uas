/**
 * Represents a single test drive in the TestDrive circular linked list.
 */
public class TestDriveNode {
    String name;              // The name of the customer scheduled for the test drive
    String phone;             // The phone number of the customer
    String vin;               // The VIN of the car to be test driven
    TestDriveNode next;      // Reference to the next TestDriveNode in the circular list

    /**
     * Constructor to create a new TestDriveNode.
     * 
     * @param name  The customer's name.
     * @param phone The customer's phone number.
     * @param vin   The car's VIN.
     */
    public TestDriveNode(String name, String phone, String vin) {
        this.name = name;
        this.phone = phone;
        this.vin = vin;
    }
}