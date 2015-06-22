package com.example.mettl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LoyaltyTest {
	
	@Test
	public void getGoldPoints()
	{
		//LoyaltyProgram obj= new LoyaltyProgram();
		Customer customer = new Customer();
		
		
		assertEquals(500,customer.getGoldPoints(10000));
	}
	
	@Test
	public void getSilverPoints()
	{
		Customer customer = new Customer();
		assertEquals(200,customer.getSilverPoints(10000));
	}
	
	@Test
	public void getNormalPoints()
	{
		Customer customer = new Customer();
		assertEquals(100, customer.getNormalPoints(10000));
	}

	@Test
	public void addCustomer()
	{
		String transactionId="23432434";
		long amount=13083;
		String dateTime="22-06-2012 11:23";
		
		String name="Abhay";
		String cardNumber="11001";
		String emailId="abhay@example.com";
		
		Customer customer = new Customer(name, emailId, cardNumber);
		Transaction transaction = new Transaction(transactionId, amount, dateTime);
		
		customer.addCustomer(customer, transaction);
		
		assertEquals(230, customer.getLoyaltyPoints());
	}
	
	@Test
	public void testCustomerClass()
	{
		String transactionId="23432434";
		long amount=13083;
		String dateTime="22-06-2012 11:23";
		
		String name="Abhay";
		String cardNumber="11001";
		String emailId="abhay@example.com";
		
		Customer customer = new Customer(name, emailId, cardNumber);
		Transaction transaction = new Transaction(transactionId, amount, dateTime);
		
		customer.addCustomer(customer, transaction);
		
		assertEquals("Normal", customer.getCustomerClass());
	}
}
