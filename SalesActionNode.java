/**
 * Represents a node in the stack used for managing sales transactions and their undo/redo history.
 */
public class SalesActionNode {
    String actionType;        // Type of action ("add_car" or "remove_car")
    String carDetails;       // Details of the car involved in the action
    double amount;           // Price associated with the action
    SalesActionNode next;    // Reference to the next action node in the stack

    /**
     * Constructor to create a new SalesActionNode.
     * 
     * @param actionType  The type of action.
     * @param carDetails The details of the car.
     * @param amount      The price involved.
     */
    public SalesActionNode(String actionType, String carDetails, double amount) {
        this.actionType = actionType;
        this.carDetails = carDetails;
        this.amount = amount;
    }
}
