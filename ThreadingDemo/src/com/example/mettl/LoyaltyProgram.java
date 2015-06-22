package com.example.mettl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Transaction class Defines attributes specific to a particular transaction
 * 
 * @author arun.verma
 *
 */
class Transaction {
	private String transactionId;
	private long amount;
	private int pointsEarned;
	private String dateTime;

	public Transaction(String transactionId, long amount, String dateTime) {
		this.transactionId = transactionId;
		this.amount = amount;
		this.dateTime = dateTime;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public int getPointsEarned() {
		return pointsEarned;
	}

	public void setPointsEarned(int pointsEarned) {
		this.pointsEarned = pointsEarned;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
}

/**
 * Customer class Defines attributes relative to a particular customer including
 * Transaction
 * 
 * Registers a new user and calculates loyalty points for new as well as old
 * customers.
 * 
 * @author arun.verma
 *
 */
class Customer {
	private String name;
	private String emailId;
	private String cardNumber;
	private List<Transaction> transaction = new ArrayList<Transaction>();
	private int loyaltyPoints;
	private String customerClass;

	public Customer() {

	}

	public Customer(String name, String emailId, String cardNumber) {
		this.name = name;
		this.emailId = emailId;
		this.cardNumber = cardNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public List<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}

	public int getLoyaltyPoints() {
		return loyaltyPoints;
	}

	public void setLoyaltyPoints(int loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}

	public String getCustomerClass() {
		return customerClass;
	}

	public void setCustomerClass(String customerClass) {
		this.customerClass = customerClass;
	}

	/**
	 * getGoldPoints Method to calculate the points earned for a customer
	 * purchase above Rs. 50000
	 * 
	 * @param points2
	 * @return
	 */
	public int getGoldPoints(long points2) {
		return (int) ((points2 / 500) * 25);
	}

	/**
	 * getSilverPoints Method to calculate the points earned for a customer
	 * purchase above Rs. 25000 and below Rs. 50000
	 * 
	 * @param points2
	 * @return
	 */
	public int getSilverPoints(long points2) {
		return (int) ((points2 / 100) * 2);
	}

	/**
	 * getNormalPoints Method to calculate the points earned for a customer
	 * purchase below Rs. 25000
	 * 
	 * @param points2
	 * @return
	 */
	public int getNormalPoints(long points2) {
		return (int) (points2 * 0.01);
	}

	/**
	 * registerCustomer Method to register a new user
	 * 
	 * If the user is an existing user, the transaction details will be added
	 * and user details will be updated
	 * 
	 * Sets the class of a particular user based on the amount of transaction
	 * 
	 * @param obj
	 * @param customer
	 * @param transaction
	 */
	public void registerCustomer(Set<Customer> obj, Customer customer, Transaction transaction) {
		/*
		 * Check if cardNumber of the customer is available or not
		 */
		if (!customer.cardNumber.equals("")) {
			boolean isRegistered = true;

			Iterator<Customer> iterator = obj.iterator();

			while (iterator.hasNext()) {
				Customer data = (Customer) iterator.next();

				/*
				 * if the customer is an existing customer
				 */
				if (data.getCardNumber().equals(customer.getCardNumber())) {
					isRegistered = false;

					/*
					 * Update details of the customer
					 */
					if (!customer.name.equals("") && !customer.emailId.equals("")) {
						data.setName(customer.name);
						data.setEmailId(customer.emailId);

					}

					int points = 0;

					/*
					 * Set class of customer based on purchase amount
					 */
					if (transaction.getAmount() > 50000) {
						if (data.getCustomerClass().equals("Silver")) {
							data.setCustomerClass("Gold");
						}
						if (data.getCustomerClass().equals("Normal")) {
							data.setCustomerClass("Gold");
						}

						points = transaction.getPointsEarned() + getGoldPoints(transaction.getAmount());
						transaction.setPointsEarned(points);

					} else if (transaction.getAmount() > 25000 && transaction.getAmount() <= 50000) {
						if (data.getCustomerClass().equals("Gold")) {
							// points = (int) (transaction.getPointsEarned() +
							// ((transaction.getAmount()/500)*25));
							points = transaction.getPointsEarned() + getGoldPoints(transaction.getAmount());
							transaction.setPointsEarned(points);
						} else if (data.getCustomerClass().equals("Normal")) {
							// points = (int)(transaction.getPointsEarned() +
							// ((transaction.getAmount()/100)*2));
							points = transaction.getPointsEarned() + getSilverPoints(transaction.getAmount());
							transaction.setPointsEarned(points);
							data.setCustomerClass("Silver");
						} else {
							points = transaction.getPointsEarned() + getSilverPoints(transaction.getAmount());
							transaction.setPointsEarned(points);
							data.setCustomerClass("Silver");
						}

					} else {
						if (data.getCustomerClass().equals("Gold")) {
							points = transaction.getPointsEarned() + getGoldPoints(transaction.getAmount());
							transaction.setPointsEarned(points);
						} else if (data.getCustomerClass().equals("Silver")) {
							points = transaction.getPointsEarned() + getSilverPoints(transaction.getAmount());
							transaction.setPointsEarned(points);

						} else {
							points = transaction.getPointsEarned() + getNormalPoints(transaction.getAmount());
							transaction.setPointsEarned(points);
						}
					}

					// total loyalty points earned by the customer
					int totalPoints = data.getLoyaltyPoints() + transaction.getPointsEarned();

					data.setLoyaltyPoints(totalPoints);
					data.getTransaction().add(transaction);
				}

			}

			// If the user is new, register the user
			if (isRegistered) {
				obj.add(addCustomer(customer, transaction));
			}
		}

	}
	
	public Customer addCustomer(Customer customer, Transaction transaction)
	{
		customer.setLoyaltyPoints(100);
		if (transaction.getAmount() > 50000) {
			customer.setCustomerClass("Gold");
			transaction.setPointsEarned(getGoldPoints(transaction.getAmount()));

		} else if (transaction.getAmount() > 25000 && transaction.getAmount() <= 50000) {
			customer.setCustomerClass("Silver");
			transaction.setPointsEarned(getSilverPoints(transaction.getAmount()));
		} else {
			customer.setCustomerClass("Normal");
			transaction.setPointsEarned(getNormalPoints(transaction.getAmount()));
		}
		int totalPoints = customer.getLoyaltyPoints() + transaction.getPointsEarned();
		customer.setLoyaltyPoints(totalPoints);
		customer.getTransaction().add(transaction);
		return customer;
	}
}

public class LoyaltyProgram {
	public static void main(String[] args) throws ParseException {

		Customer mainObj = new Customer();
		System.out.println("Enter the number of transactions");
		Scanner sc = new Scanner(System.in);
		int numTransaction = sc.nextInt();

		Set<Customer> customerData = new HashSet<Customer>();

		for (int i = 0; i < numTransaction; i++) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("");
			System.out.println("Enter the card number");
			String cardNumber = scanner.nextLine();

			System.out.println("Enter the name");
			String name = scanner.nextLine();

			System.out.println("Enter the email id");
			String email = scanner.nextLine();

			System.out.println("Enter the amount");
			long amount = scanner.nextLong();

			System.out.println("Enter the transaction id");
			String transactionId = scanner.next();

			scanner.nextLine();
			System.out.println("Enter date and time");
			String date = scanner.nextLine();

			buisnessLogicImpl(mainObj, customerData, cardNumber, name, email, amount, transactionId, date);

		}

		Iterator<Customer> it = customerData.iterator();
		while (it.hasNext()) {
			Customer newObj = (Customer) it.next();
			System.out.println("Customer Name: " + newObj.getName());
			System.out.println("Customer Email: " + newObj.getEmailId());
			System.out.println("Loyalty points: " + newObj.getLoyaltyPoints());
			System.out.println("Customer Class: " + newObj.getCustomerClass());

			System.out.println("Transactions: ");
			Iterator<Transaction> transIt = newObj.getTransaction().iterator();
			while (transIt.hasNext()) {
				Transaction data = (Transaction) transIt.next();
				System.out.println(" \t \t \t " + data.getDateTime() + " \t " + data.getTransactionId() + " \t "
						+ data.getAmount() + "\t \t " + data.getPointsEarned());
				System.out.println();
			}
			System.out.println("\t \t \t (date time) \t \t(trx Id) \t(trx Amt) \t(Points Earned)");
			System.out.println();
			System.out.println();
		}
	}

	private static void buisnessLogicImpl(Customer mainObj, Set<Customer> customerData, String cardNumber, String name,
			String email, long amount, String transactionId, String date) {
		Transaction trans = new Transaction(transactionId, amount, date);

		if (!cardNumber.equals("")) {
			Customer obj = new Customer(name, email, cardNumber);
			mainObj.registerCustomer(customerData, obj, trans);
		}
	}

}