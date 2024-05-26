import java.util.Scanner;

/**
 * Main class for running the Car Dealership System. Provides the user interface for interacting
 * with various dealership functionalities.
 */
public class Main {

    /**
     * Main method, entry point of the Car Dealership System.
     * 
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Scanner for user input
        CarInventory inventory = new CarInventory(); // Creates the car inventory
        InquiryList inquiries = new InquiryList(); // Creates the inquiry list
        TestDrive testDrives = new TestDrive(); // Manages scheduled test drives
        TestDriveQueue testDriveQueue = new TestDriveQueue(); // Queue for customers waiting for test drives
        SalesTransactionStack sales = new SalesTransactionStack(); // Stack to manage sales transactions
        
        // Main loop for the dealership system
        while (true) {
            System.out.println("\nCar Dealership System");
            System.out.println("1. Manage Inventory");
            System.out.println("2. Manage Inquiries");
            System.out.println("3. Manage Test Drives");
            System.out.println("4. Process Sales Transactions");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
    
            // Input validation: Ensure user input is an integer.
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); // Consume the invalid input
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline character left-over from nextInt()

            // Handle user choice
            switch (choice) {
                case 1:
                    manageInventory(inventory, sc);
                    break;
                case 2:
                    manageInquiries(inquiries, sc);
                    break;
                case 3:
                    manageTestDrives(inventory, testDrives, testDriveQueue, sc);
                    break;
                case 4:
                    processSalesTransactions(sales, sc, inventory);
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    sc.close(); // Close the scanner
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    } 

    /**
     * Helper method to obtain a valid integer input from the user.
     * 
     * @param sc      The Scanner object to read input from.
     * @param message The message to display to the user prompting for input.
     * @return The valid integer entered by the user.
     */
    private static int getValidIntInput(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            if (sc.hasNextInt()) {
                int input = sc.nextInt();
                sc.nextLine(); // Consume newline character
                return input;
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                sc.next(); // Consume the invalid input
            }
        }
    }

    /**
     * Helper method to obtain a valid double input from the user.
     * 
     * @param sc      The Scanner object to read input from.
     * @param message The message to display to the user prompting for input.
     * @return The valid double entered by the user.
     */
    private static double getValidDoubleInput(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            if (sc.hasNextDouble()) {
                double input = sc.nextDouble();
                sc.nextLine(); // Consume newline character
                return input;
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); // Consume the invalid input
            }
        }
    }

        /**
     * Manages the car inventory, allowing the user to add, remove, and browse cars.
     * 
     * @param inventory The CarInventory object to manage.
     * @param sc      The Scanner object for user input.
     */
    private static void manageInventory(CarInventory inventory, Scanner sc) {
        while (true) {
            System.out.println("\nManage Inventory");
            System.out.println("1. Add a car");
            System.out.println("2. Remove a car");
            System.out.println("3. Swipe Cars");
            System.out.println("4. update car details");
            System.out.println("5. Back");
            System.out.print("Enter your choice: ");
    
            // Input validation: Ensure the user input is an integer.
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); // Consume invalid input
                continue;
            }
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline character
    
            switch (choice) {
                case 1:
                    // Get details for the new car from the user.
                    System.out.print("Enter car make: ");
                    String make = sc.nextLine();
                    System.out.print("Enter car model: ");
                    String model = sc.nextLine();
                    int year = getValidIntInput(sc, "Enter car year: ");
                    double price = getValidDoubleInput(sc, "Enter car price: ");
                    int mileage = getValidIntInput(sc, "Enter car mileage: ");
                    System.out.print("Enter car VIN: ");
                    String vin = sc.nextLine();

                    // Add the car to the inventory.
                    inventory.addCar(make, model, year, price, mileage, vin);
                    break;
                case 2:
                    inventory.displayAllCars(); // Display cars for selection.
                    System.out.print("Enter VIN of car to remove: ");
                    String vinToRemove = sc.nextLine();

                    // Remove the car with the specified VIN.
                    inventory.removeCar(vinToRemove);
                    break;
                case 3:
                    // Allows user to browse cars using a swipe-like interface.
                    while (true) {
                        inventory.displayAllCars();
                        System.out.println("\nSwipe Cars");
                        System.out.println("1. Next");
                        System.out.println("2. Previous");
                        System.out.println("3. Back");
                        System.out.print("Enter your choice: ");

                        if (!sc.hasNextInt()) {
                            System.out.println("Invalid input. Please enter a number.");
                            sc.next(); 
                            continue;
                        }

                        int swipeChoice = sc.nextInt();
                        sc.nextLine(); 

                        switch (swipeChoice) {
                            case 1:
                                inventory.swipeCar(1); // Swipe to the next car.
                                break;
                            case 2:
                                inventory.swipeCar(2); // Swipe to the previous car.
                                break;
                            case 3:
                                System.out.println("Going back to Inventory Management.");
                                break;
                            default:
                                System.out.println("Invalid choice. Try again.");
                        }

                        if (swipeChoice == 3) {
                            break;
                        }
                    }
                    break;
                case 4:
                    // Get details for the car to update from the user.
                    System.out.print("Enter car VIN: ");
                    vin = sc.nextLine();
                    System.out.print("Enter updated car make: ");
                    make = sc.nextLine();
                    System.out.print("Enter updated car model: ");
                    model = sc.nextLine();
                    year = getValidIntInput(sc, "Enter updated car year: ");
                    price = getValidDoubleInput(sc, "Enter updated car price: ");
                    mileage = getValidIntInput(sc, "Enter updated car mileage: ");

                    // Update the car details in the inventory.
                    inventory.updateCarDetails(vin, make, model, year, price, mileage);
                    break;
                case 5:
                    return; // Go back to the main menu
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    /**
     * Manages customer inquiries, allowing the user to add, remove, and browse inquiries.
     * 
     * @param inquiries The InquiryList object to manage.
     * @param sc        The Scanner object for user input.
     */
    private static void manageInquiries(InquiryList inquiries, Scanner sc) {
        while (true) {
            System.out.println("\nManage Inquiries");
            System.out.println("1. Add an inquiry");
            System.out.println("2. Remove an inquiry");
            System.out.println("3. Swipe inquiries");
            System.out.println("4. Update an inquiry");
            System.out.println("5. Back");
            System.out.print("Enter your choice: ");
    
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); 
                continue;
            }
            int choice = sc.nextInt();
            sc.nextLine(); 
    
            switch (choice) {
                case 1:
                    // Get details for the new inquiry from the user.
                    System.out.print("Enter customer name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter inquiry Details: ");
                    String inquiryDetails = sc.nextLine();

                    // Add the inquiry to the list.
                    inquiries.addInquiry(name, inquiryDetails);
                    break;
                case 2:
                    inquiries.displayAllInquiries();
                    System.out.print("Enter name of inquiry to remove: ");
                    name = sc.nextLine();

                    // Remove the inquiry with the specified name.
                    inquiries.removeInquiry(name);
                    break;
                case 3:
                    while (true) {
                        // Allows the userr to browse inquiries using a swipe-like interface.
                        inquiries.displayAllInquiries();
                        System.out.println("\nSwipe Inquiries");
                        System.out.println("1. Next");
                        System.out.println("2. Previous");
                        System.out.println("3. Back");
                        System.out.print("Enter your choice: ");
    
                        if (!sc.hasNextInt()) {
                            System.out.println("Invalid input. Please enter a number.");
                            sc.next(); 
                            continue;
                        }
    
                        int swipeChoice = sc.nextInt();
                        sc.nextLine();
    
                        switch (swipeChoice) {
                            case 1:
                                inquiries.swipeInquiry(1);
                                break;
                            case 2:
                                inquiries.swipeInquiry(2);
                                break;
                            case 3:
                                System.out.println("Going back to Inquiry Management.");
                                break;
                            default:
                                System.out.println("Invalid choice. Try again.");
                        }
    
                        if (swipeChoice == 3) {
                            break;
                        }
                    }
                    break;
                case 4:
                    // Get details for the inquiry to update from the user.
                    System.out.print("Enter customer name: ");
                    name = sc.nextLine();
                    System.out.print("Enter updated inquiry details: ");
                    inquiryDetails = sc.nextLine();

                    // Update the inquiry details in the list.
                    inquiries.updateInquiry(name, inquiryDetails);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    /**
     * Manages test drives, allowing the user to schedule, cancel, and manage the queue of
     * customers waiting for a test drive.
     * 
     * @param inventory      The CarInventory object to check for available cars.
     * @param testDrives   The TestDrive object to manage scheduled test drives.
     * @param testDriveQueue The TestDriveQueue to manage customers waiting for test drives.
     * @param sc            The Scanner object for user input.
     */
    private static void manageTestDrives(CarInventory inventory ,TestDrive testDrives, TestDriveQueue testDriveQueue, Scanner sc) {
        int initialTestDriveCounter = 0; // Counter to limit initial test drives.
        while (true) {
            System.out.println("\nManage Test Drives");
            System.out.println("1. Add a test drive");
            System.out.println("2. Cancel a test drive");
            System.out.println("3. Swipe test drives");
            System.out.println("4. Remove a customer from test drive queue");
            System.out.println("5. Swap test drive queue positions"); 
            System.out.println("6. Update a test drive");
            System.out.println("7. Back");
            System.out.print("Enter your choice: ");
    
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); 
                continue;
            }
            int choice = sc.nextInt();
            sc.nextLine(); 
    
            switch (choice) {
                case 1:
                    // Get customer details and car VIN for the test drive.
                    System.out.print("Enter customer name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter customer phone number: ");
                    String phone = sc.nextLine();
                    System.out.print("Enter car VIN: ");
                    String vin = sc.nextLine();
                    
                    // Check if the car is available.
                    Car car = inventory.findCarByVIN(vin);
                    if (car != null) {
                        Customer customer = new Customer(name, phone);
    
                        // Logic to handle initial test drives and queue management.
                        if (initialTestDriveCounter < 3) {
                            testDrives.scheduleTestDrive(name, phone, vin);
                            initialTestDriveCounter++;
                            System.out.println(customer.name + " has been scheduled for a test drive.");
                        } else if (testDriveQueue.getSize() >= 3) {
                            testDriveQueue.displayQueue(testDriveQueue);
                            // if the queue if full, allow swapping positions
                            int position1 = getValidIntInput(sc, 
                                    "Enter the position of the first customer to swap: ");
                            int position2 = getValidIntInput(sc, 
                                    "Enter the position of the second customer to swap: ");
                            testDriveQueue.swapPositions(position1, position2); 
                        } else {
                            testDriveQueue.enqueue(customer, car); 
                            System.out.println(
                                    customer.name + " has been added to the test drive queue.");
                        }
                    } else {
                        System.out.println("Car with VIN " + vin + " not found.");
                    }
                    break;
                case 2:
                    // Display test drives for selection
                    testDrives.displayAllTestDrives();
                    System.out.print("Enter phone number of test drive to cancel: ");
                    phone = sc.nextLine();

                    // Cancel the test drive by phone number.
                    testDrives.cancelTestDrive(phone);
                    break;
                case 3:
                    // Allow browsing through test drives using a swipe-like interface.
                    while (true) {        
                        testDrives.displayAllTestDrives();
                        System.out.println("\nSwipe Test Drives");
                        System.out.println("1. Next");
                        System.out.println("2. Previous");
                        System.out.println("3. Back");
                        System.out.print("Enter your choice: ");
    
                        if (!sc.hasNextInt()) {
                            System.out.println("Invalid input. Please enter a number.");
                            sc.next(); 
                            continue;
                        }
    
                        int swipeChoice = sc.nextInt();
                        sc.nextLine();
    
                        switch (swipeChoice) {
                            case 1:
                                testDrives.swipeTestDrive(1);
                                break;
                            case 2:
                                testDrives.swipeTestDrive(2);
                                break;
                            case 3:
                                System.out.println("Going back to Test Drive Management.");
                                break;
                            default:
                                System.out.println("Invalid choice. Try again.");
                        }
    
                        if (swipeChoice == 3) {
                            break;
                        }
                    }
                    break;
                case 4:
                    // Remove a customer from the test drive queue.
                    System.out.print("Enter customer name to remove from test drive queue: ");
                    name = sc.nextLine();
                    testDriveQueue.removeCustomer(name);
                    break;
                case 5:
                    // Swap positions of customers in the test drive queue.
                    testDriveQueue.displayQueue(testDriveQueue);
                    System.out.println("Current Test Drive Queue:");
                    testDriveQueue.displayQueue(testDriveQueue); 
                    int position1 = getValidIntInput(sc, 
                            "Enter the position of the first customer to swap: ");
                    int position2 = getValidIntInput(sc, 
                            "Enter the position of the second customer to swap: ");
        
                    testDriveQueue.swapPositions(position1, position2);
                    System.out.println("\nTest Drive Queue after Swap:");
                    testDriveQueue.displayQueue(testDriveQueue); 
                    break;
                case 6:
                    // Update details of a scheduled test drive.
                    System.out.print("Enter phone number of test drive to update: ");
                    phone = sc.nextLine();
                    System.out.print("Enter updated car VIN: ");
                    vin = sc.nextLine();
                    testDrives.rescheduleTestDrive(phone, vin);
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    /**
     * Manages sales transactions, allowing the user to add, remove, display, undo, and redo transactions.
     * 
     * @param sales The SalesTransactionStack object to manage.
     * @param sc   The Scanner object for user input.
     */
    private static void processSalesTransactions(SalesTransactionStack sales, Scanner sc, CarInventory inventory) {
        while (true) {
            System.out.println("\nProcess Sales Transactions");
            System.out.println("1. Add a sales transaction");
            System.out.println("2. Remove a sales transaction");
            System.out.println("3. Display all sales transactions");
            System.out.println("4. Undo last transaction");
            System.out.println("5. Redo last transaction");
            System.out.println("6. Back");
            System.out.print("Enter your choice: ");
    
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); 
                continue;
            }
            int choice = sc.nextInt();
            sc.nextLine();
    
            switch (choice) {
                case 1:
                    // Get details fro the sales transaction from the user.
                    System.out.print("Enter customer name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter customer phone number: ");
                    String phone = sc.nextLine();
                    System.out.print("Enter car VIN: ");
                    String vin = sc.nextLine();
                    double price = getValidDoubleInput(sc, "Enter sale price: ");


                    // Add the sales transaction to the stack.
                    sales.addTransaction(name, phone, vin, price, inventory);
                    break;
                case 2:
                    System.out.print("Enter phone number of transaction to remove: ");
                    phone = sc.nextLine();

                    // Remove the sales transaction by phone number.
                    sales.removeTransaction(phone);
                    break;
                case 3:
                    // Display all sales transactions.
                    sales.displayAllTransactions();
                    break;
                case 4:
                    // Undo the last sales transaction.
                    sales.undo();
                    break;
                case 5:
                    // Redo the last undone sales transaction.
                    sales.redo();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
