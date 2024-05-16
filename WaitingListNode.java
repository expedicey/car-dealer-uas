
public class WaitingListNode {
    String customerName;
    String desiredCarModel;
    WaitingListNode next;
    WaitingListNode prev;

    public WaitingListNode(String customerName, String desiredCarModel) {
        this.customerName = customerName;
        this.desiredCarModel = desiredCarModel;
    }
}

