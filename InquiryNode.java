
public class InquiryNode {
    String customerName;
    String inquiryDetails; 
    InquiryNode next;

    public InquiryNode(String customerName, String inquiryDetails) {
        this.customerName = customerName;
        this.inquiryDetails = inquiryDetails;
        this.next = null;
    }
}