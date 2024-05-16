
public class TestDriveSchedule {
    TestDriveNode head;
    TestDriveNode tail;

    // Schedule a new test drive
    public void scheduleTestDrive(String name, String phone, String vin, String date) {
        TestDriveNode newTestDrive = new TestDriveNode(name, phone, vin, date);
        if (head == null) {
            head = newTestDrive;
            tail = newTestDrive;
            newTestDrive.next = head; // Point back to head
        } else {
            tail.next = newTestDrive;
            tail = newTestDrive;
            tail.next = head; // Maintain circular connection
        }
    }

    public void cancelTestDrive(String phone) {
        if (head == null) {
            System.out.println("No test drives scheduled.");
            return;
        }
    
        TestDriveNode current = head;
        TestDriveNode prev = null;
        do {
            if (current.phone.equals(phone)) {
                if (prev == null) { // Removing the head
                    head = current.next;
                    tail.next = head;
                } else if (current == tail) { // Removing the tail
                    prev.next = head;
                    tail = prev;
                } else {
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


    // Complete a test drive (remove from schedule)
    public void completeTestDrive(String customerName) {
        if (head == null) {
            System.out.println("No test drives scheduled.");
            return;
        }

        TestDriveNode current = head;
        TestDriveNode prev = null;
        do {
            if (current.name.equals(customerName)) {
                if (prev == null) { // Removing the head
                    head = current.next;
                    tail.next = head;
                } else if (current == tail) { // Removing the tail
                    prev.next = head;
                    tail = prev;
                } else {
                    prev.next = current.next;
                }
                System.out.println("Test drive for " + customerName + " completed.");
                return;
            }
            prev = current;
            current = current.next;
        } while (current != head);

        System.out.println("Test drive for " + customerName + " not found.");
    }

    // Print the test drive schedule
    public void displayAllTestDrives() {
        if (head == null) {
            System.out.println("No test drives scheduled.");
            return;
        }
    
        TestDriveNode current = head;
        do {
            System.out.println("Customer: " + current.name + 
                               ", Phone: " + current.phone + 
                               ", VIN: " + current.vin +
                               ", Date: " + current.date);
            current = current.next;
        } while (current != head);
    }
}