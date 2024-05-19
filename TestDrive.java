public class TestDrive {
    TestDriveNode head;
    TestDriveNode tail;
    private int currentTestDriveIndex = 0;

    public void scheduleTestDrive(String name, String phone, String vin) {
        if (name == null || name.trim().isEmpty() ||
            phone == null || phone.trim().isEmpty() ||
            vin == null || vin.trim().isEmpty()) {
            System.out.println("Invalid input. Name, phone, and VIN cannot be empty.");
            return;
        }

        TestDriveNode newTestDrive = new TestDriveNode(name, phone, vin);

        if (head == null) {
            head = newTestDrive;
            tail = newTestDrive;
            head.next = head; 
        } else {
            tail.next = newTestDrive;
            tail = newTestDrive;
            tail.next = head; 
        }
    }


    public void cancelTestDrive(String phone) {
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
        do {
            if (current.phone.equals(phone)) {
                if (prev == null) { 
                    head = current.next;
                    tail.next = head;
                } else if (current == tail) { 
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

    public void displayAllTestDrives() {
        if (head == null) {
            System.out.println("No test drives scheduled.");
            return;
        }
    
        TestDriveNode current = head;
        
        do { 
            System.out.print((current == getNodeAtIndex(currentTestDriveIndex) ? "--> " : "    "));
            System.out.println("Customer: " + current.name + 
                               ", Phone: " + current.phone + 
                               ", VIN: " + current.vin);
            current = current.next;
        } while (current != head); 
    }

    public void displayCurrentTestDrive() {
        TestDriveNode current = getNodeAtIndex(currentTestDriveIndex);
        if (current != null) {
            System.out.println("\nCustomer: " + current.name + ", Phone: " + current.phone + ", VIN: " + current.vin);
        } else {
            System.out.println("No test drive at this index.");
        }
    }

    public void swipeTestDrive(int direction) {
        if (getTestDriveCount() == 0) {
            System.out.println("There are no cars to swipe.");
            return; 
        }
        if (direction == 1) {
            currentTestDriveIndex = (currentTestDriveIndex + 1) % getTestDriveCount();
        } else if (direction == 2) {
            currentTestDriveIndex = (currentTestDriveIndex - 1 + getTestDriveCount()) % getTestDriveCount();
        } else {
            System.out.println("Invalid swipe direction. Use 1 for next, 2 for previous.");
            return;
        }
        displayCurrentTestDrive(); 
    }

    private TestDriveNode getNodeAtIndex(int index) {
        if (head == null || index < 0) {
            return null; 
        }

        TestDriveNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
            if (current == head) { 
                return null; 
            }
        }
        return current;
    }

    private int getTestDriveCount() {
        if (head == null) {
            return 0; 
        }

        int count = 1; 
        TestDriveNode current = head.next;
        while (current != head) {
            count++;
            current = current.next;
        }
        return count;
    }
}