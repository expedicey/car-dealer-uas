
public class CarInventory {
    CarNode head;
    CarNode tail;

    // Add a new car to the end of the list
    public void addCar(String make, String model, int year, double price, int mileage, String vin) {
        CarNode newCar = new CarNode(make, model, year, price, mileage, vin);
        if (head == null) {
            head = newCar;
            tail = newCar;
        } else {
            tail.next = newCar;
            newCar.prev = tail;
            tail = newCar;
        }
    }

    public void removeCar(String vin) {
        CarNode current = head;
        while (current != null) {
            if (current.vin.equals(vin)) {
                // If node to be deleted is head node
                if (current == head) {
                    head = head.next;
                    if (head != null) {
                        head.prev = null;
                    }
                }
                // If node to be deleted is tail node
                else if (current == tail) {
                    tail = tail.prev;
                    if (tail != null) {
                        tail.next = null;
                    }
                }
                // If node to be deleted is in between
                else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                System.out.println("Car with VIN " + vin + " removed from inventory.");
                return;
            }
            current = current.next;
        }
        System.out.println("Car with VIN " + vin + " not found in inventory.");
    }

    // public void displayAllCars() {
    //     CarNode current = head;
    //     while (current != null) {
    //         System.out.println(current);
    //         current = current.next;
    //     }
    // }

    

    // Mark a car as sold (not available)
    public void sellCar(String make, String model) {
        CarNode current = head;
        while (current != null) {
            if (current.make.equals(make) && current.model.equals(model)) {
                current.available = false;
                System.out.println(make + " " + model + " marked as sold.");
                return;
            }
            current = current.next;
        }
        System.out.println("Car not found in inventory.");
    }

    // Print the car inventory
    public void displayAllCars() {
        CarNode current = head;
        while (current != null) {
            System.out.println("Make: " + current.make + ", Model: " + current.model +
                               ", Year: " + current.year + ", Mileage: " + current.mileage +
                               ", Price: $" + current.price + ", Available: " + current.available);
            current = current.next;
        }
    }
}