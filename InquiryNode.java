/**
 * Represents a single customer inquiry. Serves as a node in the singly linked list used for
 * managing inquiries.
 */
public class InquiryNode {
    String customerName;   // The name of the customer making the inquiry
    String inquiryDetails; // The detailed information about the inquiry
    InquiryNode next;      // Reference to the next inquiry in the list

    /**
     * Constructor for creating a new InquiryNode.
     * 
     * @param customerName  The name of the customer.
     * @param inquiryDetails The details of the inquiry.
     */
    public InquiryNode(String customerName, String inquiryDetails) {
        this.customerName = customerName;
        this.inquiryDetails = inquiryDetails;
        this.next = null;
    }
}