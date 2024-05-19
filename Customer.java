public class Customer {
    String name;
    String phone; 

    public Customer(String name, String phone) {
        if (name == null || name.trim().isEmpty() || phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name and phone number cannot be empty.");
        }

        this.name = name;
        this.phone = phone;
    }
}