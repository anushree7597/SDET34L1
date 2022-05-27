package com.testngPractice;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestNgAssertTest extends TestNgBasicConfigAnnotationPracticeTest{

	@Test

	public void practice1Test() {
		Reporter.log("a-practice1", true);
		Reporter.log("b-practice1", true);
		Reporter.log("c-practice1", true);
		Assert.fail();
		Reporter.log("d-practice1", true);
		Reporter.log("e-practice1", true);

	}

	@Test

	public void practice2Test() {
		Reporter.log("f-practice2", true);	
		Reporter.log("g-practice2", true);	
		Reporter.log("h-practice2", true);
		Assert.fail();
		Reporter.log("i-practice2", true);	
		Reporter.log("h-practice2", true);	
	}

	@Test

	public void practice3Test() {
		Reporter.log("a-practice2", true);	
		Reporter.log("b-practice3", true);	
		Reporter.log("c-practice3", true);	
		Assert.fail();
		Reporter.log("d-practice3", true);	
		Reporter.log("d-practice3", true);
	}

	@Test

	public void practice4Test() {
		Reporter.log("e-practice4", true);	
		Reporter.log("f-practice4", true);	
		Reporter.log("g-practice4", true);	
		Assert.fail();
		Reporter.log("h-practice4", true);	
		Reporter.log("i-practice4", true);
	}
}
