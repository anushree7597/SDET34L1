package com.testngPractice;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNgBasicConfigAnnotationPracticeTest {

	@BeforeSuite
	public void beforesuite1Test() {
		Reporter.log("beforesuite1", true);
	}
	
	@AfterSuite
	public void aftersuite1Test() {
		Reporter.log("aftersuite1", true);
	}
	
	@BeforeClass
	public void beforeclassTest() {
		Reporter.log("beforeClass1", true);
	}
	
	@AfterClass
	public void afterClass1Test() {
		Reporter.log("afterClass1", true);
	}
	
	@BeforeTest
	public void beforeTest1Test() {
		Reporter.log("beforeTest1", true);
	}
	
	@AfterTest
	public void afterTest1Test() {
		Reporter.log("afterTest1", true);
	}
	
	@BeforeMethod
	public void beforeMethod2Test() {
		Reporter.log("beforeMethod2", true);
	}
	
	@AfterMethod
	public void afterMethod1Test() {
		Reporter.log("afterMethod1", true);
	}
	

}
