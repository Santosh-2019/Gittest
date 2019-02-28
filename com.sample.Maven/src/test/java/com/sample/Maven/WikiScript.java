package com.sample.Maven;

import org.testng.annotations.Test;

import com.selenium.Assignment.Assignment;
import com.selenium.TestCase.WikiTestCase;

public class WikiScript extends Assignment{
	
	@Test()
	public void execute () throws Throwable {
		Assignment.launchUrl();
		System.out.println("santhosh");
		//Assignment.maximize();
		
		WikiTestCase.oxygen();
		System.out.println("yaragani");
	}

}
