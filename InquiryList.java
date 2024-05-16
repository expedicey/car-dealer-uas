
public class InquiryList {
    InquiryNode head;

    // Add a new inquiry to the beginning of the list
    public void addInquiry(String customerName, String inquiryDetails) {
        InquiryNode newInquiry = new InquiryNode(customerName, inquiryDetails);
        newInquiry.next = head;
        head = newInquiry;
    }

    public void removeInquiry(String customerName) {
        InquiryNode current = head;
        InquiryNode previous = null;

        // Iterate through the list to find the inquiry
        while (current != null) {
            if (current.customerName.equals(customerName)) {
                // If the inquiry is found, remove it
                if (previous == null) {
                    // Removing the head node
                    head = current.next;
                } else {
                    // Removing a node in the middle or at the end
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



    // Process the first inquiry and remove it from the list
    public void processInquiry() {
        if (head == null) {
            System.out.println("No inquiries in the list.");
            return;
        }
        System.out.println("Processing inquiry from: " + head.customerName);
        System.out.println("Inquiry details: " + head.inquiryDetails);
        head = head.next; // Move to the next inquiry
    }

    // Print the list of inquiries
    public void displayAllInquiries() {
        InquiryNode current = head;
        while (current != null) {
            System.out.println("Customer: " + current.customerName + 
                               ", Inquiry: " + current.inquiryDetails);
            current = current.next;
        }
    }
}