public class TestDriveQueue {
    private TestDriveQueueNode front;
    private TestDriveQueueNode rear;
    private int capacity;
    private int size; 

    public TestDriveQueue() {
        this.capacity = 3; 
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    public void enqueue(Customer customer, Car car) {
        if (size == capacity) {
            System.out.println("Test drive queue is full. " + customer.name +
                               " will be added to the waiting list.");
            return; 
        }

        TestDriveRequest newRequest = new TestDriveRequest(customer, car);
        TestDriveQueueNode newNode = new TestDriveQueueNode(newRequest);

        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        System.out.println(customer.name + " added to the test drive queue for a " +
                         car.make + " " + car.model + ".");
    }

    public TestDriveRequest dequeue() {
        if (front == null) {
            System.out.println("Test drive queue is empty.");
            return null;
        }
        TestDriveRequest nextRequest = front.request;
        front = front.next;
        if (front == null) {
            rear = null; 
        }
        size--;
        System.out.println(nextRequest.customer.name + " is starting a test drive of a " +
                         nextRequest.car.make + " " + nextRequest.car.model + ".");
        return nextRequest;
    }

    public int getSize() {
        return size;
    }

    public void displayQueue(TestDriveQueue queue) {
        if (queue.getSize() == 0) {
            System.out.println("The test drive queue is empty.");
            return;
        }

        TestDriveQueueNode current = queue.front;
        int position = 1; 

        while (current != null) {
            System.out.println(position + ". " + current.request.customer.name + " (Phone: " +
                               current.request.customer.phone + ")");
            position++;
            current = current.next;
        }
    }



    public void swapPositions(int position1, int position2) {
        if (position1 < 1 || position2 < 1 || position1 > size || position2 > size) {
            System.out.println("Invalid positions. Positions must be within the queue range (1 to " + size + ").");
            return;
        }
    
        if (position1 == position2) {
            System.out.println("The positions are the same. No swap needed.");
            return; 
        }
    
        TestDriveQueueNode node1 = getNodeAtPosition(position1);
        TestDriveQueueNode node2 = getNodeAtPosition(position2);
    
        if (node1 == null || node2 == null) {
            System.out.println("Error finding nodes at the specified positions.");
            return;
        }
    
        TestDriveRequest tempRequest = node1.request;
        node1.request = node2.request;
        node2.request = tempRequest;
    
        System.out.println("Customers at positions " + position1 + " and " + position2 +
                            " have been swapped in the test drive queue.");
    }

    private TestDriveQueueNode getNodeAtPosition(int position) {
        if (front == null || position < 1) {
            return null;
        }

        TestDriveQueueNode current = front;
        for (int i = 1; i < position; i++) {
            current = current.next;
        }
        return current;
    }

    public void removeCustomer(String customerName) {
        if (customerName == null || customerName.trim().isEmpty()) {
            System.out.println("Invalid input. Customer name cannot be empty.");
            return;
        }
        
        if (front == null) {
            System.out.println("Test drive queue is empty.");
            return;
        }

        TestDriveQueueNode current = front;
        TestDriveQueueNode previous = null;

        while (current != null) {
            if (current.request.customer.name.equals(customerName)) {
                if (previous == null) { 
                    front = current.next;
                    if (front == null) {
                        rear = null;
                    }
                } else {
                    previous.next = current.next;
                    if (current == rear) { 
                        rear = previous;
                    }
                }
                size--;
                System.out.println(customerName + " removed from the test drive queue.");
                return;
            }

            previous = current;
            current = current.next;
        }

        System.out.println(customerName + " not found in the test drive queue.");
    }
}
