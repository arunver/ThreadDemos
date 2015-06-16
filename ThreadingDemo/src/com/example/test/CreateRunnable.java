package com.example.test;

class FooRunnable implements Runnable {
	Thread t;

	FooRunnable() {
		t = new Thread(this,"myThread");
		System.out.println("Thread is :" + t.getName());
		t.start();
	}

	public void run() {
		try {
			for (int i = 0; i < 5; i++) {
				System.out.println("Value is: " + i);
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			System.out.println("Thread interrupted");
		}
	}
}

public class CreateRunnable {
	public static void main(String[] args) {
		new FooRunnable();

		try {
			for (int i = 5; i > 0; i--) {
				System.out.println("Main Thread: " + i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			System.out.println("Main thread interrupted");
		}
	}

}
