
public class InquiryList {
    InquiryNode head;
    private int currentInquiryIndex = 0;

    public void addInquiry(String customerName, String inquiryDetails) {
        if (customerName == null || customerName.trim().isEmpty() || 
            inquiryDetails == null || inquiryDetails.trim().isEmpty()) {
            System.out.println("Invalid input. Customer name and Inquiry details cannot be empty.");
            return;
        }

        InquiryNode newInquiry = new InquiryNode(customerName, inquiryDetails);
        newInquiry.next = head;
        head = newInquiry;
    }

    public void removeInquiry(String customerName) {
        if (customerName == null || customerName.trim().isEmpty()) {
            System.out.println("Invalid input. Customer name cannot be empty.");
            return;
        }
        
        InquiryNode current = head;
        InquiryNode previous = null;

        while (current != null) {
            if (current.customerName.equals(customerName)) {
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

    public void processInquiry() {
        if (head == null) {
            System.out.println("No inquiries in the list.");
            return;
        }
        System.out.println("Processing inquiry from: " + head.customerName);
        System.out.println("Inquiry details: " + head.inquiryDetails);
        head = head.next; 
    }

    public void displayAllInquiries() {
        InquiryNode current = head;
        int index = 0;
        while (current != null) {
            System.out.print(index == currentInquiryIndex ? "--> " : "    ");
            System.out.println("Customer: " + current.customerName + ", Inquiry: " + current.inquiryDetails);
            current = current.next;
            index++;
        }
    }

    public void displayCurrentInquiry() {
        InquiryNode current = getNodeAtIndex(currentInquiryIndex);
        if (current != null) {
            System.out.println("\nCustomer: " + current.customerName + ", Inquiry: " + current.inquiryDetails);
        } else {
            System.out.println("No inquiry at this index.");
        }
    }

    public void swipeInquiry(int direction) {
        if (getInquiryCount() == 0) {
            System.out.println("There are no cars to swipe.");
            return; 
        }
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

    private InquiryNode getNodeAtIndex(int index) {
        if (index < 0) {
            return null;
        }

        InquiryNode current = head;
        for (int i = 0; i < index && current != null; i++) {
            current = current.next;
        }
        return current;
    }

    private int getInquiryCount() {
        int count = 0;
        InquiryNode current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
    
}