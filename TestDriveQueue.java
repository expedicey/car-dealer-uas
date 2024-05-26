/**
 * Manages a queue of customers waiting for test drives. Implemented as a linked list,
 * it handles enqueueing, dequeueing, displaying, swapping positions, and removing customers.
 */
public class TestDriveQueue {
    private TestDriveQueueNode front;    // Front of the queue (first customer)
    private TestDriveQueueNode rear;     // Rear of the queue (last customer)
    private int capacity;               // Maximum capacity of the queue
    private int size;                   // Current number of customers in the queue

    /**
     * Constructor for creating a new TestDriveQueue with a default capacity of 3.
     */
    public TestDriveQueue() {
        this.capacity = 3; 
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    /**
     * Adds a customer to the rear of the test drive queue, provided the queue is not full.
     * 
     * @param customer The Customer object to be added.
     * @param car     The Car object the customer wants to test drive.
     */
    public void enqueue(Customer customer, Car car) {
        // Check if the queue is full.
        if (size == capacity) {
            System.out.println("Test drive queue is full. " + customer.name
                    + " will be added to the waiting list.");
            return; 
        }

        // Create a new test drive request.
        TestDriveRequest newRequest = new TestDriveRequest(customer, car); 

        // Create a new queue node for the request.
        TestDriveQueueNode newNode = new TestDriveQueueNode(newRequest); 

        if (rear == null) { 
            // If the queue is empty, the new node becomes both front and rear.
            front = newNode; 
            rear = newNode; 
        } else {
            // Otherwise, append the new node to the rear of the queue.
            rear.next = newNode; 
            rear = newNode;
        }

        size++;
        System.out.println(customer.name + " added to the test drive queue for a " + car.make + " "
                + car.model + ".");
    }

    /**
     * Removes and returns the customer at the front of the queue (FIFO).
     * 
     * @return The TestDriveRequest representing the customer and car at the front of the queue, 
     *         or null if the queue is empty.
     */
    public TestDriveRequest dequeue() {
        // Handle case of an empty queue
        if (front == null) {
            System.out.println("Test drive queue is empty.");
            return null;
        }

        // Remove the customer at the front of the queue.
        TestDriveRequest nextRequest = front.request; 
        front = front.next; 

        // If the queue becomes empty, update the rear as well
        if (front == null) {
            rear = null;
        }

        size--;
        System.out.println(nextRequest.customer.name + " is starting a test drive of a "
                + nextRequest.car.make + " " + nextRequest.car.model + ".");
        return nextRequest;
    }

    /**
     * Returns the current number of customers in the queue.
     * 
     * @return The size of the queue.
     */
    public int getSize() {
        return size; 
    }

    /**
     * Displays the customers in the test drive queue, along with their positions.
     * 
     * @param queue The TestDriveQueue to display.
     */
    public void displayQueue(TestDriveQueue queue) {
        if (queue.getSize() == 0) {
            System.out.println("The test drive queue is empty.");
            return;
        }

        TestDriveQueueNode current = queue.front;
        int position = 1;

        while (current != null) {
            System.out.println(position + ". " + current.request.customer.name + " (Phone: "
                    + current.request.customer.phone + ")");
            position++;
            current = current.next;
        }
    }

    /**
     * Swaps the positions of two customers in the test drive queue.
     * 
     * @param position1 The position of the first customer to swap.
     * @param position2 The position of the second customer to swap.
     */
    public void swapPositions(int position1, int position2) {
        // Input validation
        if (position1 < 1 || position2 < 1 || position1 > size || position2 > size) {
            System.out.println(
                    "Invalid positions. Positions must be within the queue range (1 to " + size + ").");
            return; 
        }

        if (position1 == position2) { 
            System.out.println("The positions are the same. No swap needed.");
            return;
        }

        TestDriveQueueNode node1 = getNodeAtPosition(position1); 
        TestDriveQueueNode node2 = getNodeAtPosition(position2); 

        // Ensure nodes are found.
        if (node1 == null || node2 == null) {
            System.out.println("Error finding nodes at the specified positions.");
            return; 
        }

        // Swap the requests associated with the nodes
        TestDriveRequest tempRequest = node1.request; 
        node1.request = node2.request; 
        node2.request = tempRequest; 

        System.out.println("Customers at positions " + position1 + " and " + position2
                + " have been swapped in the test drive queue.");
    }

    /**
     * Returns the TestDriveQueueNode at a given position in the queue.
     * 
     * @param position The desired position in the queue (starting from 1).
     * @return The TestDriveQueueNode at the given position, or null if not found.
     */
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

    /**
     * Removes a customer from the test drive queue by their name.
     * 
     * @param customerName The name of the customer to remove.
     */
    public void removeCustomer(String customerName) {
        // Input validation
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

        // Iterate through the queue to find the customer to remove.
        while (current != null) {
            if (current.request.customer.name.equals(customerName)) { 
                // Handle removing from different positions in the queue
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