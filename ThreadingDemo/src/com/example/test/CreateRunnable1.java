package com.example.test;

class FooRunnable1 implements Runnable
{
	public void run()
	{
		try
		{
			for(int i=0; i<5; i++)
			{
				System.out.println("Value is: "+i);
				Thread.sleep(500);
			}
		}
		catch(InterruptedException e)
		{
			System.out.println("Child thread interrupted");
		}
		System.out.println("Child thread exited");
	}
}
public class CreateRunnable1 {
	public static void main(String[] args) {
		
		FooRunnable1 runnable= new FooRunnable1();
		Thread t= new Thread(runnable, "MyThread");
		t.start();
		
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		try
		{
			for(int i=5; i>0; i--)
			{
				System.out.println("Main Value is: "+i);
				Thread.sleep(1000);
			}
		}
		catch(InterruptedException e)
		{
			System.out.println("Main thread interrupted");
		}
		
		System.out.println("Priority of main thread is: "+Thread.currentThread().getPriority());
		System.out.println("Main thread exited");
	}
}
