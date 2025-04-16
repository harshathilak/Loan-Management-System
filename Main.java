import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
class Customer 
{
	private static int  customercount=0;
    private String id;
	private String name;
	private int age;
	private String address;
	private String contact;
   
    public Customer(String name, int age, String address, String contact) 
  {
    this.id = Customer.generatecustomerId();
    this.name = name;
    this.age = age;
    this.address = address;
    this.contact = contact;
  }
  
  private static String generatecustomerId()
  {
	  customercount++;
	   return "C" + String.format("%03d", customercount);
  }
  
  public String getid()
  {
	  return id;
  }
  public String getname()
  {
	  return name;
  }
  public int getage()
  {
	  return age;
  }
  public String getaddress()
  {
	  return address;
  }
  public String getcontact()
  {
	  return contact;
  }
  public void setname(String name)
  {
	  this.name=name;
  }
  public void setage(int age)
  {
	  if(age>0)
	  {
		  this.age=age;
	  }
	  else
	  {
		  System.out.print("age must be positive");
	  }
  }
  public void setaddress(String address)
  {
	  this.address=address;
  } 
  public void setcontact(String contact)
  {
	  this.contact=contact;
  }  
   @Override
  public String toString()
  {
    return "Customer ID: " + id + "\nName: " + name + "\nAge: " + age + "\nAddress: " + address +"\nContact: " + contact;
  }
}

class Loan
{
	private static int loancount=0;
	private String loanId;
	private String customerid;
	private double amount;
	private double interestrate;
	private String loantype;
	private int terms;
	private List<Double>repayments;
	
	public Loan(String customerid, double amount, String loantype, int terminmonth) {
        this.loanId = generateLoanId();  
        this.customerid = customerid;
        this.amount = amount;
		this.loantype=loantype;
        this.interestrate = assignInterestRate(loantype);
        this.terms = terminmonth;
        this.repayments = new ArrayList<>();  
    }
	
	private  static String generateLoanId() {
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

	//calculate the interest for a principal amount
	public double calculatetotalinterest()
	{
		double years=terms/12.0;
		return(amount*interestrate*years)/100;
	}
	//calculate total amount to be paid
	public double calculatetotalrepayment()
	{
		return amount+calculatetotalinterest();
	}
	//calculte amount paid in months
	public double calculatemonthlyrepayment()
	{
		return  calculatetotalinterest()/terms;
	}
	//amount paying
	public void addrepayment(double repaymentamount)
	{
		repayments.add(repaymentamount);
    }
	//total repayed amount
	public double totalrepayment()
	{
		double totalrepayment=0;
		for(int i=0;i<repayments.size();i++)
		{
			totalrepayment+=repayments.get(i);
		}
		return totalrepayment;
	}
	//calculating remaining balance
	public double remainingbalance()
	{
		return  calculatetotalrepayment()-totalrepayment();
	}
	
	//getter
	public String getloanId()
	{
		return loanId;
	}
	public String getcustomerid()
	{
		return customerid;
	}
	public String getloantype()
	{
		return loantype;
	}
    public double getinterestrate()
	{
		return interestrate;
	}
     public double getamount()
	{
		return amount;
	}
	public int getterminmonth()
	{
		return terms;
	}
	public List<Double> getRepayments()
	{
    return repayments;
   }

	
	@Override
    public String toString() {
        return "Loan ID: " + loanId + "\nCustomer ID: " + customerid +"\nPrincipal Amount: ₹" + amount + "\nInterest Rate: " + interestrate + "%" + "\nTerm: " + terms + " months" + "\nTotal Interest: " + calculatetotalinterest() +
         "\nTotal Repayment: " + calculatetotalrepayment() + "\nMonthly Repayment: " + String.format("%.2f", calculatemonthlyrepayment()) +  "\nAmount Repaid: " + totalrepayment() +  "\nOutstanding Balance: " +remainingbalance();
    }
}	
public class Main
{
	  public static void main(String [] args)
	  {
		Scanner sc =new Scanner(System.in);
		
		System.out.println("Enter Customer Name:");
        String name = sc.nextLine();

        System.out.println("Enter Customer Age:");
        int age = sc.nextInt();
        sc.nextLine(); 

        System.out.println("Enter Customer Address:");
        String address = sc.nextLine();

        System.out.println("Enter Customer Contact:");
        String contact = sc.nextLine();

        Customer customer = new Customer(name, age, address, contact);
		
		 System.out.println("\nEnter Loan Amount:");
        double amount = sc.nextDouble();

        System.out.println("Enter Term (in months):");
        int termInMonths = sc.nextInt();
        sc.nextLine(); 

        System.out.println("Enter Loan Type (home/personal/car/business):");
        String loanType = sc.nextLine();

        Loan loan = new Loan(customer.getid(), amount, loanType, termInMonths);

        // Optionally add repayment
        System.out.println("Do you want to add a repayment now? (yes/no)");
        String choice = sc.nextLine();

        if (choice.equalsIgnoreCase("yes")) {
            System.out.print("Enter repayment amount: ");
            double repay = sc.nextDouble();
            loan.addrepayment(repay);
        }

        // Print full details
        System.out.println("\n===== Customer Details =====");
        System.out.println(customer);

        System.out.println("\n===== Loan Details =====");
        System.out.println(loan);
		
		sc.close();
	  }
}
