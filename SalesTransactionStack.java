/**
 * Manages sales transactions using a stack, providing functionality for adding, removing, 
 * displaying, undoing, and redoing transactions.
 */
public class SalesTransactionStack {
    private SalesActionNode top;         // Top of the transaction stack
    private SalesActionNode undoStack;    // Stack for undo operations
    private SalesActionNode redoStack;    // Stack for redo operations

    /**
     * Pushes a new SalesActionNode onto the transaction stack and manages the undo stack. 
     * The redo stack is cleared after each new action.
     * 
     * @param action The SalesActionNode to be added to the stack.
     */
    public void pushAction(SalesActionNode action) {
        action.next = top;
        top = action;

        // If the redo stack is empty, add the action to the undo stack.
        if (redoStack == null) { 
            pushUndo(action); 
        }
        
        // Clear the redo stack.
        redoStack = null; 
    }

    /**
     * Adds a new sales transaction to the stack and updates the car's availability in the inventory.
     * 
     * @param customerName The name of the customer.
     * @param phone        The customer's phone number.
     * @param vin         The VIN of the car sold.
     * @param price       The sale price of the car.
     * @param inventory   The CarInventory object to update the car's availability.
     */
    public void addTransaction(String customerName, String phone, String vin, double price, CarInventory inventory) {
        // Input validation
        if (customerName == null || customerName.trim().isEmpty() || phone == null
                || phone.trim().isEmpty() || vin == null || vin.trim().isEmpty() || price <= 0) {
            System.out.println("Invalid input for sales transaction. Please check your data.");
            return; 
        }

        // Create car details string for the transaction.
        String carDetails = "Customer: " + customerName + ", Phone: " + phone + ", VIN: " + vin;

        // Create a new SalesActionNode representing the transaction.
        SalesActionNode newTransaction = new SalesActionNode("add_car", carDetails, price);
        
        // Add the transaction to the stack.
        pushAction(newTransaction); 

        // Mark the car as sold in the inventory
        if (inventory.markCarAsSold(vin)) {
            System.out.println("Sales transaction added to the stack. Car marked as sold.");
        } else {
            // Handle the case where the car wasn't found (maybe rollback transaction)
            System.out.println("Error: Car not found in inventory. Transaction may be inconsistent.");
        }
    }

    /**
     * Removes a sales transaction from the stack based on the customer's phone number.
     * 
     * @param phone The phone number of the customer whose transaction is to be removed.
     */
    public void removeTransaction(String phone) {
        // Input validation
        if (phone == null || phone.trim().isEmpty()) {
            System.out.println("Invalid input. Phone number cannot be empty.");
            return;
        }

        if (top == null) {
            System.out.println("No transactions in stack.");
            return;
        }

        SalesActionNode current = top;
        SalesActionNode previous = null;

        // Iterate through the stack to find the transaction to remove.
        while (current != null) {
            if (current.carDetails.contains("Phone: " + phone)) { 
                // Create a SalesActionNode for the remove action.
                SalesActionNode removeAction = new SalesActionNode("remove_car", current.carDetails,
                        current.amount);
                // Push the remove action onto the stack.
                pushAction(removeAction); 

                // Remove the transaction from the stack
                if (previous == null) { 
                    top = current.next; 
                } else { 
                    previous.next = current.next;
                }
                System.out.println("Transaction with phone number " + phone + " removed.");
                return;
            }
            previous = current;
            current = current.next;
        }
        System.out.println("Transaction with phone number " + phone + " not found in stack.");
    }

    /**
     * Displays all sales transactions currently in the stack.
     */
    public void displayAllTransactions() {
        if (top == null) {
            System.out.println("No transactions in stack.");
            return; 
        }
        SalesActionNode current = top;
        // Iterate through the stack and print details of each transaction
        while (current != null) {
            System.out.println("Action: " + current.actionType + ", Details: " + current.carDetails
                    + ", Amount: " + current.amount);
            current = current.next;
        }
    }

    /**
     * Pushes a SalesActionNode onto the undo stack.
     * 
     * @param action The SalesActionNode to add to the undo stack.
     */
    private void pushUndo(SalesActionNode action) {
        action.next = undoStack;
        undoStack = action; 
    }

    /**
     * Pops the top SalesActionNode from the undo stack.
     * 
     * @return The popped SalesActionNode, or null if the undo stack is empty.
     */
    private SalesActionNode popUndo() {
        if (undoStack == null) { 
            return null;
        }
        SalesActionNode poppedAction = undoStack;
        undoStack = undoStack.next; 
        return poppedAction;
    }

    /**
     * Pushes a SalesActionNode onto the redo stack.
     * 
     * @param action The SalesActionNode to add to the redo stack.
     */
    private void pushRedo(SalesActionNode action) {
        action.next = redoStack;
        redoStack = action; 
    }

    /**
     * Pops the top SalesActionNode from the redo stack.
     * 
     * @return The popped SalesActionNode, or null if the redo stack is empty.
     */
    private SalesActionNode popRedo() {
        if (redoStack == null) { 
            return null;
        }
        SalesActionNode poppedAction = redoStack;
        redoStack = redoStack.next; 
        return poppedAction;
    }

    /**
     * Undoes the last action on the transaction stack. This involves moving the last action from the
     * undo stack to the redo stack and reversing the action on the main stack.
     */
    public void undo() {
        if (undoStack == null) {
            System.out.println("Nothing to undo.");
            return; 
        }

        SalesActionNode lastAction = popUndo(); 
        pushRedo(lastAction); 

        // Reverse the last action based on its type
        if (lastAction.actionType.equals("add_car")) {
            removeTransactionFromStack(lastAction.carDetails); 
            System.out.println("Undid adding car: " + lastAction.carDetails);
        } else if (lastAction.actionType.equals("remove_car")) {
            lastAction.next = top; 
            top = lastAction; 
            System.out.println("Undid removing car: " + lastAction.carDetails);
        }
    }

    /**
     * Redoes the last undone action by moving the last undone action from the redo stack to the undo
     * stack and re-applying the action on the main transaction stack.
     */
    public void redo() {
        if (redoStack == null) {
            System.out.println("Nothing to redo.");
            return;
        }

        SalesActionNode lastUndoneAction = popRedo(); 
        pushUndo(lastUndoneAction); 

        // Re-apply the undone action based on its type
        if (lastUndoneAction.actionType.equals("add_car")) {
            lastUndoneAction.next = top;
            top = lastUndoneAction;
            System.out.println("Redid adding car: " + lastUndoneAction.carDetails);
        } else if (lastUndoneAction.actionType.equals("remove_car")) {
            removeTransactionFromStack(lastUndoneAction.carDetails);
            System.out.println("Redid removing car: " + lastUndoneAction.carDetails);
        }
    }

    /**
     * Removes a transaction from the main stack based on car details. This helper method is used for
     * undo and redo operations.
     * 
     * @param carDetails The details of the car to remove from the transaction stack.
     */
    private void removeTransactionFromStack(String carDetails) {
        if (top == null) { 
            return;
        }

        SalesActionNode current = top;
        SalesActionNode previous = null;

        while (current != null) {
            if (current.carDetails.equals(carDetails)) {
                if (previous == null) { 
                    top = current.next;
                } else {
                    previous.next = current.next; 
                }
                return; 
            }
            previous = current;
            current = current.next;
        }
    }
}
