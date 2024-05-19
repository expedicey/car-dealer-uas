import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CarInventory inventory = new CarInventory();
        InquiryList inquiries = new InquiryList();
        TestDrive testDrives = new TestDrive();
        TestDriveQueue testDriveQueue = new TestDriveQueue();
        SalesTransactionStack sales = new SalesTransactionStack();
        
    
        while (true) {
            System.out.println("\nCar Dealership System");
            System.out.println("1. Manage Inventory");
            System.out.println("2. Manage Inquiries");
            System.out.println("3. Manage Test Drives");
            System.out.println("4. Process Sales Transactions");
            System.out.println("5. Exit");
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
                    manageInventory(inventory, sc); 
                    break;
                case 2:
                    manageInquiries(inquiries, sc); 
                    break;
                case 3:
                    manageTestDrives(inventory, testDrives, testDriveQueue, sc); 
                    break;
                case 4:
                    processSalesTransactions(sales, sc);
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    } 

    private static int getValidIntInput(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            if (sc.hasNextInt()) {
                int input = sc.nextInt();
                sc.nextLine();
                return input; 
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                sc.next(); 
            }
        }
    }

    private static double getValidDoubleInput(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            if (sc.hasNextDouble()) {
                double input = sc.nextDouble();
                sc.nextLine(); 
               return input;
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); 
            }
        }
    }

    private static void manageInventory(CarInventory inventory, Scanner sc) {
        while (true) {
            System.out.println("\nManage Inventory");
            System.out.println("1. Add a car");
            System.out.println("2. Remove a car");
            System.out.println("3. Swipe Cars");
            System.out.println("4. Back");
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
                    System.out.print("Enter car make: ");
                    String make = sc.nextLine();
                    System.out.print("Enter car model: ");
                    String model = sc.nextLine();
                    int year = getValidIntInput(sc, "Enter car year: ");
                    double price = getValidDoubleInput(sc, "Enter car price: ");
                    int mileage = getValidIntInput(sc, "Enter car mileage: ");
                    System.out.print("Enter car VIN: ");
                    String vin = sc.nextLine();
                    inventory.addCar(make, model, year, price, mileage, vin);
                    break;
                case 2:
                    inventory.displayAllCars();
                    System.out.print("Enter VIN of car to remove: ");
                    String vinToRemove = sc.nextLine();
                    inventory.removeCar(vinToRemove);
                    break;
                case 3:
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
                                inventory.swipeCar(1);
                                break;
                            case 2:
                                inventory.swipeCar(2);
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
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void manageInquiries(InquiryList inquiries, Scanner sc) {
        while (true) {
            System.out.println("\nManage Inquiries");
            System.out.println("1. Add an inquiry");
            System.out.println("2. Remove an inquiry");
            System.out.println("3. Swipe inquiries");
            System.out.println("4. Back");
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
                    System.out.print("Enter customer name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter inquiry Details: ");
                    String inquiryDetails = sc.nextLine();
                    inquiries.addInquiry(name, inquiryDetails);
                    break;
                case 2:
                    inquiries.displayAllInquiries();
                    System.out.print("Enter name of inquiry to remove: ");
                    name = sc.nextLine();
                    inquiries.removeInquiry(name);
                    break;
                case 3:
                    while (true) {
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
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void manageTestDrives(CarInventory inventory ,TestDrive testDrives, TestDriveQueue testDriveQueue, Scanner sc) {
        int initialTestDriveCounter = 0;
        while (true) {
            System.out.println("\nManage Test Drives");
            System.out.println("1. Add a test drive");
            System.out.println("2. Cancel a test drive");
            System.out.println("3. Swipe test drives");
            System.out.println("4. Remove a customer from test drive queue");
            System.out.println("5. Swap test drive queue positions"); 
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
                    System.out.print("Enter customer name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter customer phone number: ");
                    String phone = sc.nextLine();
                    System.out.print("Enter car VIN: ");
                    String vin = sc.nextLine();
                    Car car = inventory.findCarByVIN(vin);
                    if (car != null) {
                        Customer customer = new Customer(name, phone);
    
                        if (initialTestDriveCounter < 3) {
                            testDrives.scheduleTestDrive(name, phone, vin);
                            initialTestDriveCounter++;
                            System.out.println(customer.name + " has been scheduled for a test drive.");
                        } else if (testDriveQueue.getSize() >= 3) {
                            int position1 = getValidIntInput(sc, "Enter the position of the first customer to swap: ");
                            int position2 = getValidIntInput(sc, "Enter the position of the second customer to swap: ");
                            testDriveQueue.swapPositions(position1, position2); 
                        } else {
                            testDriveQueue.enqueue(customer, car); 
                            System.out.println(customer.name + " has been added to the test drive queue.");
                        }
                    } else {
                        System.out.println("Car with VIN " + vin + " not found.");
                    }
                    break;
                case 2:
                    testDrives.displayAllTestDrives();
                    System.out.print("Enter phone number of test drive to cancel: ");
                    phone = sc.nextLine();
                    testDrives.cancelTestDrive(phone);
                    break;
                case 3:
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
                    System.out.print("Enter customer name to remove from test drive queue: ");
                    name = sc.nextLine();
                    testDriveQueue.removeCustomer(name);
                    break;
                case 5:
                    testDriveQueue.displayQueue(testDriveQueue);
                    System.out.println("Current Test Drive Queue:");
                    testDriveQueue.displayQueue(testDriveQueue); 
                    int position1 = getValidIntInput(sc, "Enter the position of the first customer to swap: ");
                    int position2 = getValidIntInput(sc, "Enter the position of the second customer to swap: ");
        
                    testDriveQueue.swapPositions(position1, position2);
                    System.out.println("\nTest Drive Queue after Swap:");
                    testDriveQueue.displayQueue(testDriveQueue); 
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void processSalesTransactions(SalesTransactionStack sales, Scanner sc) {
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
                    System.out.print("Enter customer name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter customer phone number: ");
                    String phone = sc.nextLine();
                    System.out.print("Enter car VIN: ");
                    String vin = sc.nextLine();
                    double price = getValidDoubleInput(sc, "Enter sale price: ");
                    sales.addTransaction(name, phone, vin, price);
                    break;
                case 2:
                    System.out.print("Enter phone number of transaction to remove: ");
                    phone = sc.nextLine();
                    sales.removeTransaction(phone);
                    break;
                case 3:
                    sales.displayAllTransactions();
                    break;
                case 4:
                    sales.undo();
                    break;
                case 5:
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
