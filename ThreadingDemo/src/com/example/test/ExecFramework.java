package com.example.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task implements Runnable
{
	private int taskId;
	
	Task(int id)
	{
		taskId = id;
	}
	public void run()
	{
		System.out.println("Task ID : " + this.taskId +" performed by " 
                + Thread.currentThread().getName());
	}
}
public class ExecFramework {
	
	
	public static void main(String[] args) {
		
		ExecutorService service = Executors.newFixedThreadPool(10);
		for(int i=0; i<10; i++)
		{
			service.submit(new Task(i));
		}
	}
	
	

}
