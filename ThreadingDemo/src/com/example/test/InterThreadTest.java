package com.example.test;

class Q
{
	int n;
	boolean valueSet = false;
	
	
	synchronized int get()
	{
		while(!valueSet)
		{
			try{
				wait();
			}
			catch(InterruptedException e)
			{
				System.out.println("Interrupted Exception caught");
			}
		}
		System.out.println("Got "+n);
		valueSet= false;
		notify();
		return n;
	}
	
	synchronized void put(int n)
	{
		int i=0;
		while(valueSet)
		{	
			try
			{
				wait();
			}
			catch(InterruptedException e)
			{
				System.out.println("Ínterrupted Exception caught");
			}
		}
		this.n=n;
		valueSet = true;
		System.out.println("Put : "+n);
		notify();
		
		
	}
}

class Producer implements Runnable
{
	Q q;
	Thread t;
	
	Producer( Q q)
	{
		this.q=q;
		System.out.println("Producer Thread");
		t= new Thread(this,"Producer Thread");
		t.start();
	}
	public void run()
	{
		int i=0;
		
		while(true)
		{
			q.put(i++);
		}
	}
}

class Consumer implements Runnable
{
	Q q;
	Thread t;
	
	Consumer(Q q)
	{
		this.q=q;

		System.out.println("Consumer Thread");
		t= new Thread(this, "Consumer Thread");
		t.start();
	}
	public void run()
	{
		while(true)
		{
			q.get();
		}
	}
}
public class InterThreadTest {
	public static void main(String[] args) {
		Q q= new Q();
		new Producer(q);
		new Consumer(q);
		
		
	}

}
