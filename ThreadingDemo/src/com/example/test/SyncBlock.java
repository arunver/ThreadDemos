package com.example.test;

class CallMe1
{
	public void msgCall(String msg)
	{
		System.out.print("["+msg);
		try
		{
			Thread.sleep(1000);
		}
		catch(InterruptedException e)
		{
			System.out.println("Thread interrupted");
		}
		System.out.println("]");
	}
}

class CallMethod1 implements Runnable
{
	Thread t;
	CallMe1 call;
	String msg;
	
	CallMethod1(CallMe1 target, String msg)
	{
		call= target;
		this.msg= msg;
		t= new Thread(this);
		t.start();
	}
	
	public void run()
	{
		/*
		 * a synchronized block
		 * for cases where we dont have access to the method we are calling
		 * put the object of the class inside synchronized block and call the method
		 */
		synchronized (call) {
			call.msgCall(msg);
		}
		
	}
}

public class SyncBlock {
	public static void main(String[] args) {
		CallMe1 target= new CallMe1();
		CallMethod1 callMethod = new CallMethod1(target, "Hello");
		CallMethod1 callMethod1 = new CallMethod1(target, "Synchronized");
		CallMethod1 callMethod2 = new CallMethod1(target, "method");
		
		try
		{
			callMethod.t.join();
			callMethod1.t.join();
			callMethod2.t.join();
			
			System.out.println("Waiting for child thread to complete execution");
		}
		catch(InterruptedException e)
		{
			System.out.println("Main thread interrupted");
		}
	}

}
