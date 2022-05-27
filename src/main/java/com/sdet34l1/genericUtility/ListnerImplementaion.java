package com.sdet34l1.genericUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.vtiger.ObjectRepository.HomePage;
import com.vtiger.ObjectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author DELL
 *
 */
public class ListnerImplementaion implements ITestListener{

	ExtentSparkReporter spark;
	ExtentReports report;
	ExtentTest test;
		
	@Override
	public void onStart(ITestContext context) {
		System.out.println("onStart"+Thread.currentThread().getId());
		
		spark=new ExtentSparkReporter("./extentReport/extentReport.html");
		spark.config().setDocumentTitle("DocumentTitle");
		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("ReporterName");
		
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("Environment", "TestingEnvironment");
		report.setSystemInfo("ReporterName", "Anushree");
		report.setSystemInfo("Platform", "Windows10");
		report.setSystemInfo("unit testing Tool", "Maven");
		report.setSystemInfo("BuildManagement tool", "Maven");
		report.setSystemInfo("Automation Tool", "Selenium");
	}
	
	@Override
	public void onTestStart(ITestResult result) 
	{
		System.out.println("onTestStart"+Thread.currentThread().getId());
		test=report.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("onTestSuccess"+Thread.currentThread().getId());
		test.log(Status.PASS, result.getMethod().getMethodName()+"is passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("onTestFailure");
		test.log(Status.PASS, result.getMethod().getMethodName()+"is failed");
		test.log(Status.FAIL, result.getThrowable());
		
		test.addScreenCaptureFromPath(WebDriverBrowserUtility.takeScreenShot(result.getMethod().getMethodName(),BaseClass.staticdriver));
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("onTestSkipped");
		test.log(Status.SKIP, result.getMethod().getMethodName()+"is skipped");
		test.log(Status.SKIP, result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("onTestFailedButWithinSuccessPercentage");

	}

	@Override
		public void onFinish(ITestContext context) {
			System.out.println("onFinish"+Thread.currentThread().getId());
			report.flush();

	}
}
