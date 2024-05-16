public class SalesAction {
    String actionType; // (e.g., "add_car", "apply_discount")
    String carDetails; // (e.g., make, model, price)
    double amount; // (e.g., discount amount)

    public SalesAction(String actionType, String carDetails, double amount) {
        this.actionType = actionType;
        this.carDetails = carDetails;
        this.amount = amount;
    }
}

