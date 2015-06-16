package com.example.test;

class CallMe
{
	public synchronized void msgCall(String msg)
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

class CallMethod implements Runnable
{
	Thread t;
	CallMe call;
	String msg;
	
	CallMethod(CallMe target, String msg)
	{
		call= target;
		this.msg= msg;
		t= new Thread(this);
		t.start();
	}
	
	public void run()
	{
		call.msgCall(msg);
	}
}

public class SyncMethod {
	public static void main(String[] args) {
		
		CallMe target= new CallMe();
		CallMethod callMethod = new CallMethod(target, "Hello");
		CallMethod callMethod1 = new CallMethod(target, "Synchronized");
		CallMethod callMethod2 = new CallMethod(target, "method");
		
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
