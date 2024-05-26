/**
 * Represents a customer interacting with the dealership.
 */
public class Customer {
    String name;  // The name of the customer
    String phone; // The phone number of the customer

    /**
     * Constructor to create a new Customer object.
     * 
     * @param name  The name of the customer.
     * @param phone The phone number of the customer.
     * @throws IllegalArgumentException if the name or phone number are empty or null.
     */
    public Customer(String name, String phone) {
        // Input validation: Ensure both name and phone are not empty.
        if (name == null || name.trim().isEmpty() || phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name and phone number cannot be empty.");
        }

        this.name = name;
        this.phone = phone;
    }
}