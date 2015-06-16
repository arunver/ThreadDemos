package com.example.test;


class TestThread extends Thread
{
	
	public TestThread()
	{
		super("myThread");
		System.out.println("Child thread is: "+this.currentThread().getName());
		start();
	}
	
	public void run()
	{
		for(int i=0; i<3; i++)
		{
			System.out.println("Value is: "+i);
			System.out.println("Thread is: "+Thread.currentThread().getName());
		}
	}
}
public class CreateThread {
	public static void main(String[] args) {
		TestThread myThread= new TestThread();
		
	}

}
