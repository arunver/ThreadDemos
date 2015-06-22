package com.example.mettl;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
	public static void main(String[] args) {
		
		Result result = JUnitCore.runClasses(LoyaltyTest.class);
		for(Failure failure: result.getFailures())
		{
			System.out.println("Failure is: "+failure.toString());
		}
		
		System.out.println(result.wasSuccessful());
	}

}
