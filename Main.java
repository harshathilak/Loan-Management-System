import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

 class Customer {
    private static int customercount = 0;
    private String id;
    private String name;
    private int age;
    private String address;
    private String contact;

    public static List<Customer> customers = new ArrayList<>();

    public Customer(String name, int age, String address, String contact) {
        this.id = Customer.generatecustomerId();
        this.name = name;
        this.age = age;
        this.address = address;
        this.contact = contact;

        customers.add(this);
        System.out.println("Customer added to list: " + name + " (ID: " + id + ")");
    }

    private static String generatecustomerId() {
        customercount++;
        return "C" + String.format("%03d", customercount);
    }

    public String getid() {
        return id;
    }

    public String getname() {
        return name;
    }

    public int getage() {
        return age;
    }

    public String getaddress() {
        return address;
    }

    public String getcontact() {
        return contact;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getCustomerId() {
        return id; // Fixed the variable name
    }

    public void setage(int age) {
        if (age > 0) {
            this.age = age;
        } else {
            System.out.print("age must be positive");
        }
    }

    public void setaddress(String address) {
        this.address = address;
    }

    public void setcontact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Customer ID: " + id + "\nName: " + name + "\nAge: " + age + "\nAddress: " + address + "\nContact: " + contact;
    }
}

class Loan {
    private static int loancount = 0;
    private String loanId;
    private String customerid;
    private double amount;
    private double interestrate;
    private String loantype;
    private int terms;
    private List<Double> repayments;

    public Loan(String customerid, double amount, String loantype, int terminmonth) {
        this.loanId = generateLoanId();
        this.customerid = customerid;
        this.amount = amount;
        this.loantype = loantype;
        this.interestrate = assignInterestRate(loantype);
        this.terms = terminmonth;
        this.repayments = new ArrayList<>();
    }

    private static String generateLoanId() {
        loancount++;
        return "Lbsg" + String.format("%03d", loancount);
    }

    private double assignInterestRate(String loanType) {
        switch (loanType.toLowerCase()) {
            case "home":
                return 4.5;
            case "personal":
                return 6.5;
            case "car":
                return 5.5;
            case "business":
                return 7.0;
            default:
                return 8.0;
        }
    }

    public double calculatetotalinterest() {
        double years = terms / 12.0;
        return (amount * interestrate * years) / 100;
    }

    public double calculatetotalrepayment() {
        return amount + calculatetotalinterest();
    }

    public double calculatemonthlyrepayment() {
        return calculatetotalrepayment() / terms; // Fixed calculation
    }

    public void addrepayment(double repaymentamount) {
        repayments.add(repaymentamount);
    }

    public double totalrepayment() {
        double totalrepayment = 0;
        for (double repayment : repayments) {
            totalrepayment += repayment;
        }
        return totalrepayment;
    }

    public double remainingbalance() {
        return calculatetotalrepayment() - totalrepayment();
    }

    public String getloanId() {
        return loanId;
    }

    public String getcustomerid() {
        return customerid;
    }

    public String getloantype() {
        return loantype;
    }

    public double getinterestrate() {
        return interestrate;
    }

    public double getamount() {
        return amount;
    }

    public int getterminmonth() {
        return terms;
    }

    public List<Double> getRepayments() {
        return repayments;
    }

    @Override
    public String toString() {
        return "Loan ID: " + loanId + "\nCustomer ID: " + customerid + "\nPrincipal Amount: ₹" + amount +
               "\nInterest Rate: " + interestrate + "%" + "\nTerm: " + terms + " months" +
               "\nTotal Interest: " + calculatetotalinterest() +
               "\nTotal Repayment: " + calculatetotalrepayment() +
               "\nMonthly Repayment: " + String.format("%.2f", calculatemonthlyrepayment()) +
               "\nAmount Repaid: " + totalrepayment() +
               "\nOutstanding Balance: " + remainingbalance();
    }
}

 class LoanManager {
    private List<Customer> customers;
    private List<Loan> loans;

    public LoanManager() {
        customers = new ArrayList<>();
        loans = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer); // Added this method
    }

    public void viewallcustomers() {
        if (customers.isEmpty()) {
            System.out.print("No customers available");
            return;
        }
        for (Customer c : customers) {
            System.out.println("\n" + c);
        }
    }

    public Customer searchcustomerbyid(String id) {
        for (Customer c : customers) {
            if (c.getid().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }

    public void applyLoan(Loan loan) {
        loans.add(loan);
        System.out.println("Loan applied for Customer ID: " + loan.getcustomerid());
    }

    public void viewAllLoans() {
        if (loans.isEmpty()) {
            System.out.println("No loans found.");
            return;
        }
        System.out.println("\nAll Loans:");
        for (Loan l : loans) {
            System.out.println("\n" + l);
        }
    }

    public Loan searchLoanById(String loanId) {
        for (Loan l : loans) {
            if (l.getloanId().equalsIgnoreCase(loanId)) {
                return l;
            }
        }
        return null;
    }

    public boolean deleteLoan(String loanId) {
        for (Loan l : loans) {
            if (l.getloanId().equalsIgnoreCase(loanId)) {
                loans.remove(l);
                System.out.println("Loan with ID " + loanId + " has been deleted.");
                return true;
            }
        }
        System.out.println("Loan with ID " + loanId + " not found.");
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LoanManager manager = new LoanManager();

        while (true) {
            System.out.println("\n===== Loan Management System Menu =====");
            System.out.println("1. Add New Customer");
            System.out.println("2. Apply for Loan");
            System.out.println("3. View All Customers");
            System.out.println("4. View All Loans");
            System.out.println("5. Search Customer by ID");
            System.out.println("6. Search Loan by ID");
            System.out.println("7. Delete Loan");
            System.out.println("8. Exit");
            System.out.print("Choose an option (1-8): ");

            int choice = sc.nextInt();
            sc.nextLine(); // clear newline

            switch (choice) {
                case 1:
                    // Add New Customer
                    System.out.print("Enter Customer Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Customer Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Customer Address: ");
                    String address = sc.nextLine();
                    System.out.print("Enter Customer Contact: ");
                    String contact = sc.nextLine();

                    Customer newCustomer = new Customer(name, age, address, contact);
                    manager.addCustomer(newCustomer);
                    break;

                case 2:
                    // Apply for Loan
                    System.out.print("Enter Customer ID to apply loan: ");
                    String customerId = sc.nextLine();
                    Customer found = manager.searchcustomerbyid(customerId);
                    if (found == null) {
                        System.out.println("Customer ID not found.");
                        break;
                    }

                    System.out.print("Enter Loan Amount: ");
                    double amount = sc.nextDouble();
                    System.out.print("Enter Term (months): ");
                    int term = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Loan Type (home/personal/car/business): ");
                    String loanType = sc.nextLine();

                    Loan loan = new Loan(customerId, amount, loanType, term);
                    manager.applyLoan(loan);
                    break;

                case 3:
                    manager.viewallcustomers();
                    break;

                case 4:
                    manager.viewAllLoans();
                    break;

                case 5:
                    System.out.print("Enter Customer ID to search: ");
                    String cid = sc.nextLine();
                    Customer c = manager.searchcustomerbyid(cid);
                    if (c != null) {
                        System.out.println("\n" + c);
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;

                case 6:
                    System.out.print("Enter Loan ID to search: ");
                    String lid = sc.nextLine();
                    Loan l = manager.searchLoanById(lid);
                    if (l != null) {
                        System.out.println("\n" + l);
                    } else {
                        System.out.println("Loan not found.");
                    }
                    break;

                case 7:
                    System.out.print("Enter Loan ID to delete: ");
                    String deleteId = sc.nextLine();
                    manager.deleteLoan(deleteId);
                    break;

                case 8:
                    System.out.println("Exiting... Thank you!");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
