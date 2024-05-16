
public class InquiryNode {
    String customerName;
    String inquiryDetails; // (e.g., interested car model, questions)
    InquiryNode next;

    public InquiryNode(String customerName, String inquiryDetails) {
        this.customerName = customerName;
        this.inquiryDetails = inquiryDetails;
        this.next = null;
    }
}