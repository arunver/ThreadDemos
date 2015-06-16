package com.example.test;

class NewThread implements Runnable {
	String name;
	Thread t;

	NewThread(String threadName) {
		name = threadName;
		t = new Thread(this, name);
		System.out.println("New Thread " + t);
		t.start();
	}

	public void run() {
		try {
			for (int i = 0; i < 4; i++) {
				System.out.println("Value is: " + i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName() + " interrupted");
		}
		System.out.println(Thread.currentThread().getName() + " exited");
	}
}

public class JoinAlive {
	public static void main(String[] args) {
		NewThread one = new NewThread("One");
		NewThread two = new NewThread("Two");
		NewThread three = new NewThread("Three");
		
		System.out.println("Thread one is alive: "+one.t.isAlive());
		System.out.println("Thread two is alive: "+two.t.isAlive());
		System.out.println("Thread three is alive: "+three.t.isAlive());
		
		try
		{
			System.out.println("Waiting for child threads to finish");
			one.t.join();
			two.t.join();
			three.t.join();
			
			for(int i=0; i<4; i++)
			{
				System.out.println("Main value is: "+i);
				Thread.sleep(1500);
			}
		}
		catch(InterruptedException e)
		{
			System.out.println("Main thread interrupted");
		}
		System.out.println("Main thread exited");
		
		System.out.println("Thread one is alive: "+one.t.isAlive());
		System.out.println("Thread two is alive: "+two.t.isAlive());
		System.out.println("Thread three is alive: "+three.t.isAlive());
	}
}
