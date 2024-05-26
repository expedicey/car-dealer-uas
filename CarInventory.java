/**
 * Represents the inventory of cars in the dealership. Uses a doubly linked list for efficient 
 * car addition and removal.
 */
public class CarInventory {
    private CarNode head; // Reference to the first car in the inventory
    private CarNode tail; // Reference to the last car in the inventory
    private int currentCarIndex = 0; // Index for tracking currently displayed car in swipe view

    /**
     * Adds a new car to the inventory.
     * 
     * @param make     The make of the car (e.g., "Toyota").
     * @param model    The model of the car (e.g., "Camry").
     * @param year     The year the car was manufactured.
     * @param price    The price of the car.
     * @param mileage  The mileage of the car.
     * @param vin      The Vehicle Identification Number (VIN) of the car.
     */
    public void addCar(String make, String model, int year, double price, int mileage, String vin) {
        // Input validation: Ensure year, price, and mileage are positive, and make, model, and VIN are not empty.
        if (year <= 0 || price <= 0 || mileage < 0) {
            System.out.println("Invalid input. Year, price, and mileage must be positive values.");
            return;
        }

        if (make == null || make.trim().isEmpty() || model == null || model.trim().isEmpty() ||
            vin == null || vin.trim().isEmpty()) {
            System.out.println("Invalid input. Make, model, and VIN cannot be empty.");
            return; 
        }

        // Create a new CarNode to represent the car
        CarNode newCar = new CarNode(make, model, year, price, mileage, vin);

        // If the inventory is empty, the new car becomes both head and tail.
        if (head == null) {
            head = newCar;
            tail = newCar;
        } else {
            // Append the new car to the end of the inventory
            tail.next = newCar;
            newCar.prev = tail;
            tail = newCar;
        }
    }

    /**
     * Updates details of a car in the inventory using its VIN.
     * 
     * @param vin      The VIN of the car to update.
     * @param make     The updated make of the car.
     * @param model    The updated model of the car.
     * @param year     The updated year of the car.
     * @param price    The updated price of the car.
     * @param mileage  The updated mileage of the car.
     */
    public void updateCarDetails(String vin, String make, String model, int year, double price, int mileage) {
        CarNode current = head;
        while (current != null) {
            if (current.vin.equals(vin)) {
                // Input validation (similar to addCar method) can be added here
                current.make = make;
                current.model = model;
                current.year = year;
                current.price = price;
                current.mileage = mileage;
                System.out.println("Car with VIN " + vin + " updated successfully.");
                return;
            }
            current = current.next;
        }
        System.out.println("Car with VIN " + vin + " not found in inventory.");
    }

    /**
     * Removes a car from the inventory using its VIN.
     * 
     * @param vin The VIN of the car to remove.
     */
    public void removeCar(String vin) {
        CarNode current = head;

        // Iterate through the inventory to find the car with the matching VIN.
        while (current != null) {
            if (current.vin.equals(vin)) {
                // Handle removal based on the car's position in the linked list.
                if (current == head) {
                    head = head.next;
                    if (head != null) {
                        head.prev = null;
                    }
                }
                else if (current == tail) {
                    tail = tail.prev;
                    if (tail != null) {
                        tail.next = null;
                    }
                }
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

    /**
     * Finds a car in the inventory by its VIN.
     * 
     * @param vin The VIN of the car to find.
     * @return A new Car object containing the data of the found car, or null if not found.
     */
    public Car findCarByVIN(String vin) {
        CarNode current = head;
        // Iterate through the inventory to find the car with the matching VIN.
        while (current != null) {
            if (current.vin.equals(vin)) {
                return new Car(current.make, current.model, current.year, 
                               current.price, current.mileage, current.vin);
            }
            current = current.next;
        }
        // Return null if the car is not found.
        return null; 
    }

    /**
     * Displays all cars in the inventory. The currently swiped car is indicated with an arrow.
     */
    public void displayAllCars() {
        CarNode current = head;
        int index = 0;

        // Iterate though the inventory and display each car's details.
        while (current != null) {
            System.out.print((index == currentCarIndex ? "--> " : "    ")); 
            System.out.println("Make: " + current.make + ", Model: " + current.model +
                               ", Year: " + current.year + ", Mileage: " + current.mileage +
                               ", Price: $" + current.price + ", Available: " + current.available);
            current = current.next;
            index++;
        }
    }

    /**
     * Displays details of the currently selected car in the swipe view.
     */
    public void displayCurrentCar() {
        CarNode current = getNodeAtIndex(currentCarIndex);
        if (current != null) {
            System.out.println("\nMake: " + current.make + ", Model: " + current.model +
                    ", Year: " + current.year + ", Mileage: " + current.mileage +
                    ", Price: $" + current.price + ", Available: " + current.available);
        } else {
            System.out.println("No car at this index.");
        }
    }

    /**
     * Navigates through the car inventory in a swipe-like manner.
     * 
     * @param direction The direction to swipe (1 for next, 2 for previous).
     */
    public void swipeCar(int direction) {
        // If inventory is empty, there's nothing to swipe.
        if (getCarCount() == 0) {
            System.out.println("There are no cars to swipe.");
            return; 
        }

        // Calculate the new index based on the swipe direction.
        if (direction == 1) { 
            currentCarIndex = (currentCarIndex + 1) % getCarCount(); 
        } else if (direction == 2) {
            currentCarIndex = (currentCarIndex - 1 + getCarCount()) % getCarCount(); 
        } else {
            System.out.println("Invalid swipe direction. Use 1 for next, 2 for previous.");
            return;
        }

        // Display the details of the currently selected car.
        displayCurrentCar(); 
    }

    /**
     * Gets the CarNode at a specific index in the inventory list.
     * 
     * @param index The index of the desired node.
     * @return The CarNode at the specified index, or null if the index is invalid.
     */
    private CarNode getNodeAtIndex(int index) {
        // Handle invalid index.
        if (index < 0) {
            return null; 
        }

        CarNode current = head;
        // Traverse the linked list until the desired index is reached.
        for (int i = 0; i < index && current != null; i++) {
            current = current.next;
        }
        return current;
    }

    /**
     * Gets the total number of cars in the inventory.
     * 
     * @return The number of cars in the inventory.
     */
    private int getCarCount() {
        int count = 0;
        CarNode current = head;
        // Iterate through the list and count each car.
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    /**
     * Finds a car in the inventory by its VIN and marks it as sold (unavailable).
     *
     * @param vin The VIN of the car to mark as sold.
     * @return True if the car is found and marked as sold, false otherwise.
     */
    public boolean markCarAsSold(String vin) {
        CarNode current = head;
        while (current != null) {
            if (current.vin.equals(vin)) {
                current.available = false;
                System.out.println("Car with VIN " + vin + " marked as sold.");
                return true;
            }
            current = current.next;
        }
        System.out.println("Car with VIN " + vin + " not found in inventory.");
        return false;
    }
}