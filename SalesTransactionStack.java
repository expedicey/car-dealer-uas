public class SalesTransactionStack {
    SalesActionNode top;
    SalesActionNode undoStack; 
    SalesActionNode redoStack; 

    public void pushAction(SalesActionNode action) {
        action.next = top;
        top = action;

        if (redoStack == null) { 
            pushUndo(action); 
        }
        redoStack = null; 
    }

    public void addTransaction(String customerName, String phone, String vin, double price) {
        if (customerName == null || customerName.trim().isEmpty() ||
            phone == null || phone.trim().isEmpty() ||
            vin == null || vin.trim().isEmpty() || price <= 0) {
            System.out.println("Invalid input for sales transaction. Please check your data.");
            return;
        }

        String carDetails = "Customer: " + customerName + ", Phone: " + phone + ", VIN: " + vin;
        SalesActionNode newTransaction = new SalesActionNode("add_car", carDetails, price);
        pushAction(newTransaction); 
        System.out.println("Sales transaction added to the stack.");
    }

    public void removeTransaction(String phone) {
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

        while (current != null) {
            if (current.carDetails.contains("Phone: " + phone)) { 
                SalesActionNode removeAction = new SalesActionNode("remove_car", current.carDetails, current.amount);
                pushAction(removeAction);

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

    public void displayAllTransactions() {
        if (top == null) {
            System.out.println("No transactions in stack.");
            return;
        }
        SalesActionNode current = top;
        while (current != null) {
            System.out.println("Action: " + current.actionType + 
                               ", Details: " + current.carDetails + 
                               ", Amount: " + current.amount);
            current = current.next;
        }
    }

    private void pushUndo(SalesActionNode action) {
        action.next = undoStack;
        undoStack = action;
    }

    private SalesActionNode popUndo() {
        if (undoStack == null) {
            return null; 
        }
        SalesActionNode poppedAction = undoStack;
        undoStack = undoStack.next;
        return poppedAction;
    }

    private void pushRedo(SalesActionNode action) {
        action.next = redoStack;
        redoStack = action;
    }

    private SalesActionNode popRedo() {
        if (redoStack == null) {
            return null; 
        }
        SalesActionNode poppedAction = redoStack;
        redoStack = redoStack.next;
        return poppedAction;
    }

    public void undo() {
        if (undoStack == null) {
            System.out.println("Nothing to undo.");
            return;
        }
    
        SalesActionNode lastAction = popUndo();
        pushRedo(lastAction);
    
        if (lastAction.actionType.equals("add_car")) {
            removeTransactionFromStack(lastAction.carDetails); 
            System.out.println("Undid adding car: " + lastAction.carDetails);
    
        } else if (lastAction.actionType.equals("remove_car")) {
            lastAction.next = top;
            top = lastAction;
            System.out.println("Undid removing car: " + lastAction.carDetails);
        }
    }

    public void redo() {
        if (redoStack == null) {
            System.out.println("Nothing to redo.");
            return;
        }

        SalesActionNode lastUndoneAction = popRedo();
        pushUndo(lastUndoneAction);

        if (lastUndoneAction.actionType.equals("add_car")) {
            lastUndoneAction.next = top;
            top = lastUndoneAction;
            System.out.println("Redid adding car: " + lastUndoneAction.carDetails);

        } else if (lastUndoneAction.actionType.equals("remove_car")) {
            removeTransactionFromStack(lastUndoneAction.carDetails);
            System.out.println("Redid removing car: " + lastUndoneAction.carDetails);
        }
    }

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