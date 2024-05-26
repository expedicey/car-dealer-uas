/**
 * Manages customer inquiries using a singly linked list.
 */
public class InquiryList {
    private InquiryNode head;            // Reference to the first inquiry in the list
    private int currentInquiryIndex = 0; // Index for tracking the current inquiry in swipe view

    /**
     * Adds a new inquiry to the list.
     * 
     * @param customerName  The name of the customer making the inquiry.
     * @param inquiryDetails The details of the customer's inquiry.
     */
    public void addInquiry(String customerName, String inquiryDetails) {
        // Input validation: Ensure customer name and inquiry details are not empty.
        if (customerName == null || customerName.trim().isEmpty() || 
            inquiryDetails == null || inquiryDetails.trim().isEmpty()) {
            System.out.println("Invalid input. Customer name and Inquiry details cannot be empty.");
            return;
        }

        // Create a new InquiryNode for the inquiry
        InquiryNode newInquiry = new InquiryNode(customerName, inquiryDetails);

        // Add the new inquiry to the head of the list.
        newInquiry.next = head;
        head = newInquiry;
    }

    /**
     * Updates an inquiry in the list by customer name.
     * 
     * @param customerName  The name of the customer whose inquiry is to be updated.
     * @param inquiryDetails The updated details of the inquiry.
     */
    public void updateInquiry(String customerName, String inquiryDetails) {
        if (customerName == null || customerName.trim().isEmpty()) {
            System.out.println("Invalid input. Customer name cannot be empty.");
            return;
        }
        
        InquiryNode current = head;
        while (current != null) {
            if (current.customerName.equals(customerName)) {
                // Input validation can be added here
                current.inquiryDetails = inquiryDetails;
                System.out.println("Inquiry for " + customerName + " updated.");
                return;
            }
            current = current.next;
        }
        System.out.println("Inquiry for " + customerName + " not found.");
    }

    /**
     * Removes an inquiry from the list by customer name.
     * 
     * @param customerName The name of the customer whose inquiry should be removed.
     */
    public void removeInquiry(String customerName) {
        // Input validation: Ensure customer name is not empty.
        if (customerName == null || customerName.trim().isEmpty()) {
            System.out.println("Invalid input. Customer name cannot be empty.");
            return;
        }

        InquiryNode current = head;
        InquiryNode previous = null;

        // Iterate through the list to find the matching inquiry.
        while (current != null) {
            if (current.customerName.equals(customerName)) {
                // Handle removal based on the inquiry's position in the list.
                if (previous == null) { 
                    head = current.next;
                } else {
                    previous.next = current.next;
                }
                System.out.println("Inquiry from " + customerName + " removed.");
                return;
            }
            previous = current;
            current = current.next;
        }
        System.out.println("Inquiry from " + customerName + " not found.");
    }

    /**
     * Processes and removes the first inquiry in the list (FIFO).
     */
    public void processInquiry() {
        if (head == null) {
            System.out.println("No inquiries in the list.");
            return;
        }

        // Process and remove the first inquiry.
        System.out.println("Processing inquiry from: " + head.customerName);
        System.out.println("Inquiry details: " + head.inquiryDetails);
        head = head.next; 
    }

    /**
     * Displays all inquiries in the list. The currently swiped inquiry is indicated with an arrow.
     */
    public void displayAllInquiries() {
        InquiryNode current = head;
        int index = 0;
        // Iterate through the list and print details of each inquiry.
        while (current != null) {
            System.out.print(index == currentInquiryIndex ? "--> " : "    ");
            System.out.println("Customer: " + current.customerName + ", Inquiry: " + current.inquiryDetails);
            current = current.next;
            index++;
        }
    }

    /**
     * Displays details of the currently selected inquiry in the swipe view.
     */
    public void displayCurrentInquiry() {
        InquiryNode current = getNodeAtIndex(currentInquiryIndex);
        if (current != null) {
            System.out.println("\nCustomer: " + current.customerName + ", Inquiry: " + current.inquiryDetails);
        } else {
            System.out.println("No inquiry at this index.");
        }
    }

    /**
     * Navigates through inquiries in a swipe-like manner.
     * 
     * @param direction The direction to swipe (1 for next, 2 for previous).
     */
    public void swipeInquiry(int direction) {
        if (getInquiryCount() == 0) {
            System.out.println("There are no cars to swipe.");
            return; 
        }

        // Calculate the new index based on the swipe direction.
        if (direction == 1) {
            currentInquiryIndex = (currentInquiryIndex + 1) % getInquiryCount();
        } else if (direction == 2) {
            currentInquiryIndex = (currentInquiryIndex - 1 + getInquiryCount()) % getInquiryCount();
        } else {
            System.out.println("Invalid swipe direction. Use 1 for next, 2 for previous.");
            return;
        }
        displayCurrentInquiry();
    }

    /**
     * Gets the InquiryNode at a specific index in the inquiry list.
     * 
     * @param index The index of the desired node.
     * @return The InquiryNode at the specified index, or null if the index is invalid.
     */
    private InquiryNode getNodeAtIndex(int index) {
        // Handle invalid index.
        if (index < 0) {
            return null;
        }

        InquiryNode current = head;
        // Traverse the list until the desired index is reached.
        for (int i = 0; i < index && current != null; i++) {
            current = current.next;
        }
        return current;
    }

    /**
     * Gets the total number of inquiries in the list.
     * 
     * @return The number of inquiries in the list.
     */
    private int getInquiryCount() {
        int count = 0;
        InquiryNode current = head;
        // Iterate through the list and count each inquiry.
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
    
}