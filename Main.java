
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CarInventory inventory = new CarInventory();
        InquiryList inquiries = new InquiryList();
        TestDriveSchedule testDrives = new TestDriveSchedule();
        WaitingList waitingList = new WaitingList();
        SalesTransactionStack sales = new SalesTransactionStack();
    
        while (true) {
            System.out.println("\nCar Dealership System");
            System.out.println("1. Manage Inventory");
            System.out.println("2. Manage Inquiries");
            System.out.println("3. Manage Test Drives");
            System.out.println("4. Manage Waiting list");
            System.out.println("5. Process Sales Transactions");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
    
            int choice = sc.nextInt();
            sc.nextLine();
    
            switch (choice) {
                case 1:
                    manageInventory(inventory);
                    break;
                case 2:
                    manageInquiries(inquiries);
                    break;
                case 3:
                    manageTestDrives(testDrives);
                    break;
                case 4:
                    manageWaitingList(waitingList);
                    break;
                case 5:
                    processSalesTransactions(sales, sc);
                    break;
                case 6:
                    System.out.println("Exiting program...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    } 

    private static void manageInventory(CarInventory inventory) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nManage Inventory");
            System.out.println("1. Add a car");
            System.out.println("2. Remove a car");
            System.out.println("3. Display all cars");
            System.out.println("4. Back");
            System.out.print("Enter your choice: ");
    
            int choice = sc.nextInt();
            sc.nextLine();
    
            switch (choice) {
                case 1:
                    System.out.print("Enter car make: ");
                    String make = sc.nextLine();
                    System.out.print("Enter car model: ");
                    String model = sc.nextLine();
                    System.out.print("Enter car year: ");
                    int year = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter car price: ");
                    double price = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Enter car mileage: ");
                    int mileage = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter car VIN: ");
                    String vin = sc.nextLine();
                    inventory.addCar(make, model, year, price, mileage, vin);
                    break;
                case 2:
                    System.out.print("Enter VIN of car to remove: ");
                    vin = sc.nextLine();
                    inventory.removeCar(vin);
                    break;
                case 3:
                    inventory.displayAllCars();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void manageInquiries(InquiryList inquiries) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nManage Inquiries");
            System.out.println("1. Add an inquiry");
            System.out.println("2. Remove an inquiry");
            System.out.println("3. Display all inquiries");
            System.out.println("4. Back");
            System.out.print("Enter your choice: ");
    
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
                    System.out.print("Enter name of inquiry to remove: ");
                    name = sc.nextLine();
                    inquiries.removeInquiry(name);
                    break;
                case 3:
                    inquiries.displayAllInquiries();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void manageTestDrives(TestDriveSchedule testDrives) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nManage Test Drives");
            System.out.println("1. Schedule a test drive");
            System.out.println("2. Cancel a test drive");
            System.out.println("3. Display all test drives");
            System.out.println("4. Back");
            System.out.print("Enter your choice: ");
    
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
                    System.out.print("Enter test drive date: ");
                    String date = sc.nextLine();
                    testDrives.scheduleTestDrive(name, phone, vin, date);
                    break;
                case 2:
                    System.out.print("Enter phone number of test drive to cancel: ");
                    phone = sc.nextLine();
                    testDrives.cancelTestDrive(phone);
                    break;
                case 3:
                    testDrives.displayAllTestDrives();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void manageWaitingList(WaitingList waitingList) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nManage Waiting List");
            System.out.println("1. Add a customer to waiting list");
            System.out.println("2. Remove a customer from waiting list");
            System.out.println("3. Display all customers on waiting list");
            System.out.println("4. Back");
            System.out.print("Enter your choice: ");
    
            int choice = sc.nextInt();
            sc.nextLine();
    
            switch (choice) {
                case 1:
                    System.out.print("Enter customer name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter desired Car Model: ");
                    String desiredCarModel = sc.nextLine();
                    waitingList.addToWaitingList(name, desiredCarModel);
                    break;
                case 2:
                    System.out.print("Enter name of customer to remove: ");
                    name = sc.nextLine();
                    waitingList.removeCustomer(name);
                    break;
                case 3:
                    waitingList.displayAllCustomers();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void processSalesTransactions(SalesTransactionStack sales, Scanner sc) {
        while (true) {
            System.out.println("\nProcess Sales Transactions and Undo/Redo Actions");
            System.out.println("1. Add a sales transaction");
            System.out.println("2. Remove a sales transaction");
            System.out.println("3. Display all sales transactions");
            System.out.println("4. Undo last action");
            System.out.println("5. Redo Last undone action");
            System.out.println("6. Back");
            System.out.print("Enter your choice: ");
    
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
                    System.out.print("Enter sale price: ");
                    double price = sc.nextDouble();
                    sc.nextLine();
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