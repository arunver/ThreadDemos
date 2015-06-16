package com.example.test;

class MyRunnable implements Runnable
{
	String name;
	Thread t;
	 MyRunnable(String threadName)
	 {
		 name= threadName;
		 t= new Thread(this,name);
		 System.out.println("New Thread : "+t);
		 t.start();
	 }
	public void run()
	{
		try
		{
			for(int i=0;i<4; i++)
			{
				System.out.println("Value is: "+i);
				Thread.sleep(1000);
			}
		}
		catch(InterruptedException e)
		{
			System.out.println("Child thread "+Thread.currentThread().getName()+" interrupted");
		}
		System.out.println("Child thread "+Thread.currentThread().getName()+" exited");
	}
}
public class CreateMultiple {
	public static void main(String[] args) {
		
		new MyRunnable("One");	
		new MyRunnable("Two");
		new MyRunnable("Three");
	}
}
