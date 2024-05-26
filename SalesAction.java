/**
 * Represents a single action within a sales transaction, such as adding or removing a car.
 */
public class SalesAction {
    String actionType;  // The type of action ("add_car" or "remove_car")
    String carDetails; // Details of the car involved in the action
    double amount;      // The price associated with the action

    /**
     * Constructor to create a new SalesAction object.
     * 
     * @param actionType  The type of action.
     * @param carDetails The details of the car.
     * @param amount      The price involved.
     */
    public SalesAction(String actionType, String carDetails, double amount) {
        this.actionType = actionType;
        this.carDetails = carDetails;
        this.amount = amount;
    }
}