
public class CarInventory {
    CarNode head;
    CarNode tail;
    private int currentCarIndex = 0;

    public void addCar(String make, String model, int year, double price, int mileage, String vin) {
        if (year <= 0 || price <= 0 || mileage < 0) {
            System.out.println("Invalid input. Year, price, and mileage must be positive values.");
            return;
        }

        if (make == null || make.trim().isEmpty() || model == null || model.trim().isEmpty() ||
            vin == null || vin.trim().isEmpty()) {
            System.out.println("Invalid input. Make, model, and VIN cannot be empty.");
            return; 
        }

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

    public Car findCarByVIN(String vin) {
        CarNode current = head;
        while (current != null) {
            if (current.vin.equals(vin)) {
                return new Car(current.make, current.model, current.year, 
                               current.price, current.mileage, current.vin);
            }
            current = current.next;
        }
        return null; 
    }

    public void displayAllCars() {
        CarNode current = head;
        int index = 0;
        while (current != null) {
            System.out.print((index == currentCarIndex ? "--> " : "    ")); 
            System.out.println("Make: " + current.make + ", Model: " + current.model +
                               ", Year: " + current.year + ", Mileage: " + current.mileage +
                               ", Price: $" + current.price + ", Available: " + current.available);
            current = current.next;
            index++;
        }
    }

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

    public void swipeCar(int direction) {
        if (getCarCount() == 0) {
            System.out.println("There are no cars to swipe.");
            return; 
        }
        if (direction == 1) { 
            currentCarIndex = (currentCarIndex + 1) % getCarCount(); 
        } else if (direction == 2) {
            currentCarIndex = (currentCarIndex - 1 + getCarCount()) % getCarCount(); 
        } else {
            System.out.println("Invalid swipe direction. Use 1 for next, 2 for previous.");
            return;
        }
        displayCurrentCar(); 
    }

    private CarNode getNodeAtIndex(int index) {
        if (index < 0) {
            return null; 
        }

        CarNode current = head;
        for (int i = 0; i < index && current != null; i++) {
            current = current.next;
        }
        return current;
    }

    private int getCarCount() {
        int count = 0;
        CarNode current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}