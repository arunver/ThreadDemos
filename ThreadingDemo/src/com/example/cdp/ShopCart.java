package com.example.cdp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

enum ProductType{men,women,kid};

class Product implements Comparable<Product>
{
	int id;
	String description;
	
	//HAS A relation
	Vendor vendor;
	String type;
	double price;
	
	public Product(int id, String desc, Vendor vendor, String type, double price)
	{
		this.id=id;
		description=desc;
		this.vendor= vendor;
		this.type= type;
		this.price = price;
	}
	
	public void show()
	{
		System.out.println("This is the product");
	}

	public int compareTo(Product o) {
		// TODO Auto-generated method stub
		return (int) ((o.price)- (this.price));
	}
	
	public String toString()
	{
		return "Price is: "+this.price;
	}
}

//IS A relation
class Men extends Product
{
	
	public Men(int id, String desc, Vendor vendor, String type, double price) {
		super(id, desc, vendor, type, price);
		// TODO Auto-generated constructor stub
	}
	
	public void show()
	{
		System.out.println("This is men's class");
	}

}

//IS A relation
class Women extends Product
{
	public Women(int id, String desc, Vendor vendor, String type, double price) {
		super(id, desc, vendor, type, price);
		// TODO Auto-generated constructor stub
	}

	public void show()
	{
		System.out.println("This is women's class");
	}
}

class Kid extends Product
{
	public Kid(int id, String desc, Vendor vendor, String type, double price) {
		super(id, desc, vendor, type, price);
		// TODO Auto-generated constructor stub
	}

	public void show()
	{
		System.out.println("This is Kid's class");
	}
}

public class ShopCart {
	public static void main(String[] args) {
		int menCount=0,womenCount=0, kidCount=0;
		
		double menProductPrice = 0, womenProductPrice = 0.0, kidProductPrice = 0.0;
		int id=0;
		
		String choice="";
		ArrayList<Product> menProduct = new ArrayList<Product>();
		ArrayList<Product> womenProduct = new ArrayList<Product>();
		ArrayList<Product> kidProduct = new ArrayList<Product>();
		do{
			System.out.println("Enter type of product");
			Scanner scanner = new Scanner(System.in);
			String type = scanner.nextLine();
			ProductType productType = ProductType.valueOf(type.toLowerCase().trim());
			
			switch(productType)
			{
			case men:
				System.out.println("Enter description");
				String menDesc = scanner.nextLine();
				
				System.out.println("Enter vendor name");
				String menName = scanner.nextLine();
				
				System.out.println("Enter price of the product");
				double menPrice = scanner.nextDouble();
				
				id++;
				
				Men men= new Men(id, menDesc, new Vendor(menName),type, menPrice);
				menProduct.add(men);
				menCount++;
				break;				
			case women:
				System.out.println("Enter description");
				String womenDesc = scanner.nextLine();
				
				
				System.out.println("Enter vendor name");
				String womenName = scanner.nextLine();
				
				System.out.println("Enter price of the product");
				double womenPrice = scanner.nextDouble();
				id++;
				
				Women women= new Women(id, womenDesc, new Vendor(womenName), type, womenPrice);
				womenProduct.add(women);
				womenCount++;
				break;
			case kid:
				System.out.println("Enter description");
				String kidDesc = scanner.nextLine();
				
				
				System.out.println("Enter vendor name");
				String kidName = scanner.nextLine();
				
				System.out.println("Enter price of the product");
				double kidPrice = scanner.nextDouble();
				id++;
				
				Kid kid= new Kid(id, kidDesc, new Vendor(kidName), type, kidPrice);
				kidProduct.add(kid);
				kidCount++;
				break;
				
			default:
					System.out.println("Wrong input: Please try again");
				
			}
			System.out.println("Do you want to continue");
			choice = scanner.next();
		}		
		while(choice.equalsIgnoreCase("y"));
		
		Collections.sort(menProduct);
		Collections.sort(womenProduct);
		Collections.sort(kidProduct);
		
		int menProductCount = menProduct.size();
		int womenProductCount = womenProduct.size();
		int kidProductCount = kidProduct.size();
		
		if(menProductCount <3)
		{
			for(int i=0; i<menProductCount; i++)
			{
				menProductPrice= menProductPrice + menProduct.get(i).price;
			}
			
		}
		else
		{
			int rem= (menProductCount/3);
			
			double discount =0.0;
			for(int i=0; i<rem; i++)
			{
				discount = discount+(0.25 * (menProduct.get(i).price));
			}
			
			for(Product product: menProduct)
			{
				menProductPrice = menProductPrice + product.price;
			}
			menProductPrice = menProductPrice - discount;
		}
		
		if(womenProductCount <2)
		{
			for(int i=0; i<1; i++)
			{
				womenProductPrice= womenProductPrice + womenProduct.get(i).price;
			}
			
		}
		else
		{
			int rem= (womenProductCount/2);
			
			double discount =0.0;
			for(int i=rem-1; i>=0; i--)
			{
				discount = discount+(0.30 * (womenProduct.get(i).price));
			}
			
			for(Product product: womenProduct)
			{
				womenProductPrice = womenProductPrice + product.price;
			}
			womenProductPrice = womenProductPrice - discount;
		}
		
		if(kidProductCount <2)
		{
			for(int i=0; i<1; i++)
			{
				kidProductPrice= kidProductPrice + kidProduct.get(i).price;
			}
			
		}
		else
		{
			int rem= (kidProductCount/2);
			
			double discount =0.0;
			for(int i=0; i<rem; i++)
			{
				discount = discount+(0.15 * (kidProduct.get(i).price));
			}
			
			for(Product product: kidProduct)
			{
				kidProductPrice = kidProductPrice + product.price;
			}
			kidProductPrice = kidProductPrice - discount;
		}
	
		System.out.println("Total price is: "+(menProductPrice + womenProductPrice+kidProductPrice));
		
		
	}

}
