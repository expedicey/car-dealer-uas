
public class WaitingList {
    WaitingListNode head;
    WaitingListNode tail;

    // Add a customer to the waiting list
    public void addToWaitingList(String customerName, String desiredCarModel) {
        WaitingListNode newNode = new WaitingListNode(customerName, desiredCarModel);
        if (head == null) {
            head = newNode;
            tail = newNode;
            newNode.next = head;
            newNode.prev = head;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            head.prev = newNode;
            newNode.next = head;
            tail = newNode;
        }
    }

    public void removeCustomer(String name) {
        if (head == null) {
            System.out.println("Waiting list is empty.");
            return;
        }
    
        WaitingListNode current = head;
        do {
            if (current.customerName.equals(name)) {
                if (current == head && current == tail) { // Only one node in the list
                    head = null;
                    tail = null;
                } else if (current == head) { // Removing the head
                    head = current.next;
                    head.prev = tail;
                    tail.next = head;
                } else if (current == tail) { // Removing the tail
                    tail = current.prev;
                    tail.next = head;
                    head.prev = tail;
                } else { // Removing a node in between
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                System.out.println("Customer with the name: " + name + " removed from waiting list.");
                return;
            }
            current = current.next;
        } while (current != head);
    
        System.out.println("Customer with the name: " + name + " not found in waiting list.");
    }



    // Assign a car to the first customer in the waiting list and remove them
    public void assignCar(String carModel) {
        if (head == null) {
            System.out.println("Waiting list is empty.");
            return;
        }

        WaitingListNode current = head;
        do {
            if (current.desiredCarModel.equals(carModel)) {
                if (current == head) {
                    head = current.next;
                    head.prev = tail;
                    tail.next = head;
                } else if (current == tail) {
                    tail = current.prev;
                    tail.next = head;
                    head.prev = tail;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                System.out.println(carModel + " assigned to " + current.customerName);
                return;
            }
            current = current.next;
        } while (current != head);

        System.out.println("No customer found waiting for " + carModel);
    }

    // Print the waiting list
    public void displayAllCustomers() {
        if (head == null) {
            System.out.println("Waiting list is empty.");
            return;
        }

        WaitingListNode current = head;
        do {
            System.out.println("Customer: " + current.customerName + 
                               ", Desired Car: " + current.desiredCarModel);
            current = current.next;
        } while (current != head);
    }
}