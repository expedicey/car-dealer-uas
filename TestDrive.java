/**
 * Manages scheduled test drives using a circular linked list.
 */
public class TestDrive {
    private TestDriveNode head;                // Reference to the first test drive in the list
    private TestDriveNode tail;                // Reference to the last test drive in the list
    private int currentTestDriveIndex = 0;    // Index to track the currently displayed test drive in swipe view

    /**
     * Schedules a new test drive.
     * 
     * @param name  The name of the customer.
     * @param phone The phone number of the customer.
     * @param vin   The VIN of the car to be test driven.
     */
    public void scheduleTestDrive(String name, String phone, String vin) {
        // Input validation
        if (name == null || name.trim().isEmpty() || phone == null || phone.trim().isEmpty()
                || vin == null || vin.trim().isEmpty()) {
            System.out.println("Invalid input. Name, phone, and VIN cannot be empty.");
            return;
        }

        // Create a new TestDriveNode for the test drive.
        TestDriveNode newTestDrive = new TestDriveNode(name, phone, vin);

        // Handle adding to an empty list and appending to an existing list.
        if (head == null) {
            // If the list is empty, the new node becomes both head and tail, creating a circular list.
            head = newTestDrive; 
            tail = newTestDrive;
            head.next = head;
        } else {
            // Append the new node to the end and update the tail to maintain the circular structure.
            tail.next = newTestDrive; 
            tail = newTestDrive;
            tail.next = head;
        }
    }

    /**
     * Reschedules a test drive by updating the car VIN. 
     * 
     * @param phone The phone number of the customer whose test drive is to be rescheduled.
     * @param vin   The updated VIN of the car for the test drive.
     */
    public void rescheduleTestDrive(String phone, String vin) {
        if (phone == null || phone.trim().isEmpty()) {
            System.out.println("Invalid input. Phone number cannot be empty.");
            return;
        }
        
        if (head == null) {
            System.out.println("No test drives scheduled.");
            return;
        }
    
        TestDriveNode current = head;
        do {
            if (current.phone.equals(phone)) {
                // Input validation (for vin) can be added here 
                current.vin = vin;
                System.out.println("Test drive for " + phone + " rescheduled with new VIN " + vin + ".");
                return;
            }
            current = current.next;
        } while (current != head);
    
        System.out.println("Test drive with phone number " + phone + " not found.");
    }

    /**
     * Cancels a scheduled test drive by customer's phone number.
     * 
     * @param phone The phone number of the customer whose test drive is to be canceled.
     */
    public void cancelTestDrive(String phone) {
        // Input validation
        if (phone == null || phone.trim().isEmpty()) {
            System.out.println("Invalid input. Phone number cannot be empty.");
            return;
        }

        if (head == null) {
            System.out.println("No test drives scheduled.");
            return;
        }

        TestDriveNode current = head;
        TestDriveNode prev = null;
        // Iterate through the list (circular) to find the matching test drive.
        do {
            if (current.phone.equals(phone)) {
                // Handle removing from different positions in the circular list.
                if (prev == null) {
                    // Removing the head, adjust head and tail to maintain circular structure.
                    head = current.next; 
                    tail.next = head; 
                } else if (current == tail) {
                    // Removing the tail, update tail and point previous node to head.
                    prev.next = head; 
                    tail = prev; 
                } else {
                    // Removing a node in the middle, connect previous node to next node.
                    prev.next = current.next;
                }
                System.out.println("Test drive with phone number " + phone + " canceled.");
                return; 
            }
            prev = current;
            current = current.next;
        } while (current != head); 

        System.out.println("Test drive with phone number " + phone + " not found.");
    }

    /**
     * Displays all scheduled test drives. The currently swiped test drive is indicated with an arrow.
     */
    public void displayAllTestDrives() {
        if (head == null) {
            System.out.println("No test drives scheduled.");
            return;
        }

        TestDriveNode current = head;
        
        // Iterate through the circular linked list.
        do {
            // Indicate the currently selected test drive.
            System.out.print((current == getNodeAtIndex(currentTestDriveIndex) ? "--> " : "    ")); 
            System.out.println("Customer: " + current.name + ", Phone: " + current.phone + ", VIN: "
                    + current.vin);
            current = current.next;
        } while (current != head);
    }

    /**
     * Displays the currently selected test drive in the swipe view.
     */
    public void displayCurrentTestDrive() {
        TestDriveNode current = getNodeAtIndex(currentTestDriveIndex); 
        if (current != null) {
            System.out.println("\nCustomer: " + current.name + ", Phone: " + current.phone
                    + ", VIN: " + current.vin);
        } else {
            System.out.println("No test drive at this index.");
        }
    }

    /**
     * Navigates through test drives using a swipe-like interface.
     * 
     * @param direction The swipe direction (1 for next, 2 for previous).
     */
    public void swipeTestDrive(int direction) {
        // If there are no test drives, there's nothing to swipe.
        if (getTestDriveCount() == 0) { 
            System.out.println("There are no test drives to swipe.");
            return;
        }

        // Calculate the new index based on swipe direction.
        if (direction == 1) {
            currentTestDriveIndex = (currentTestDriveIndex + 1) % getTestDriveCount(); 
        } else if (direction == 2) {
            currentTestDriveIndex = (currentTestDriveIndex - 1 + getTestDriveCount())
                    % getTestDriveCount(); 
        } else {
            System.out.println("Invalid swipe direction. Use 1 for next, 2 for previous.");
            return;
        }
        displayCurrentTestDrive(); 
    }

    /**
     * Retrieves the TestDriveNode at the given index in the circular linked list.
     * 
     * @param index The index of the desired node.
     * @return The TestDriveNode at the given index, or null if not found.
     */
    private TestDriveNode getNodeAtIndex(int index) {
        if (head == null || index < 0) { 
            return null; 
        }

        TestDriveNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
            if (current == head) { 
                // Wrapped around the list without finding the index, return null
                return null; 
            }
        }
        return current;
    }

    /**
     * Calculates the number of scheduled test drives.
     * 
     * @return The total number of test drives in the list.
     */
    private int getTestDriveCount() {
        if (head == null) { 
            return 0; 
        }

        int count = 1;
        TestDriveNode current = head.next;
        // Iterate through the circular list to count the test drives.
        while (current != head) { 
            count++;
            current = current.next;
        }
        return count;
    }
}