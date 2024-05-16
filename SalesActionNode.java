
public class SalesActionNode {
    String actionType; 
    String carDetails;
    double amount; 
    SalesActionNode next;

    public SalesActionNode(String actionType, String carDetails, double amount) {
        this.actionType = actionType;
        this.carDetails = carDetails;
        this.amount = amount;
    }
}

