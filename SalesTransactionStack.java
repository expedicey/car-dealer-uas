
public class SalesTransactionStack {
    SalesActionNode top;
    SalesActionNode undoTop;
    SalesActionNode redoTop;

    // Push a new action onto the stack
    public void pushAction(SalesActionNode action) {
        action.next = top;
        top = action;
        pushUndo(action);
        redoTop = null; // Clear the redo stack
    }

    public void addTransaction(String customerName, String phone, String vin, double price) {
        String carDetails = "Customer: " + customerName + ", Phone: " + phone + ", VIN: " + vin;
        SalesActionNode newTransaction = new SalesActionNode("add_car", carDetails, price);
        pushAction(newTransaction); // Push the new transaction onto the stack
        System.out.println("Sales transaction added to the stack.");
    }

    public void removeTransaction(String phone) {
        if (top == null) {
            System.out.println("No transactions in stack.");
            return;
        }

        SalesActionNode current = top;
        SalesActionNode previous = null;

        while (current != null) {
            if (current.carDetails.contains("Phone :" + phone)) {
                if (previous == null) {
                    top = current.next;
                } else {
                    previous.next = current.next;
                }
                System.out.println("Transaction with phone number" + phone + "remove");
                return;
            }
            previous = current;
            current = current.next;
        }
        System.out.println("Transaction with phone number" + phone + "not found in stack.");

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
        action.next = undoTop;
        undoTop = action;
    }

    // Pop an action from the undo stack
    private SalesActionNode popUndo() {
        if (undoTop == null) {
            return null; // Undo stack is empty
        }
        SalesActionNode poppedAction = undoTop;
        undoTop = undoTop.next;
        return poppedAction;
    }

    private void pushRedo(SalesActionNode action) {
        action.next = redoTop;
        redoTop = action;
    }

    // Pop an action from the redo stack
    private SalesActionNode popRedo() {
        if (redoTop == null) {
            return null; // Redo stack is empty
        }
        SalesActionNode poppedAction = redoTop;
        redoTop = redoTop.next;
        return poppedAction;
    }


    // Undo the last action
    public void undo() {
        if (undoTop == null) {
            System.out.println("Nothing to undo.");
            return;
        }

        SalesActionNode lastAction = popUndo();
        pushRedo(lastAction); // Add to redo stack

        // Reverse the action 
        if (lastAction.actionType.equals("add_car")) {
            removeTransactionFromStack(lastAction.carDetails);
            System.out.println("Undid adding car: " + lastAction.carDetails);
        } else if (lastAction.actionType.equals("remove_car")) {
            pushAction(new SalesActionNode("add_car", lastAction.carDetails, lastAction.amount)); 
            System.out.println("Undid removing car: " + lastAction.carDetails);
        }
    }

    public void redo() {
        if (redoTop == null) {
            System.out.println("Nothing to redo.");
            return;
        }

        SalesActionNode lastUndoneAction = popRedo();
        pushUndo(lastUndoneAction);  // Add to undo stack

        if (lastUndoneAction.actionType.equals("add_car")) {
            pushAction(lastUndoneAction); 
            System.out.println("Redid adding car: " + lastUndoneAction.carDetails);
        } else if (lastUndoneAction.actionType.equals("remove_car")) {
            removeTransactionFromStack(lastUndoneAction.carDetails);
            System.out.println("Redid removing car: " + lastUndoneAction.carDetails);
        }
    }

    private void removeTransactionFromStack(String carDetails) {
        if (top == null) {
            return; // Stack is empty
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