package com.sdet34l1.genericUtility;

import org.openqa.selenium.WebDriver;

import com.vtiger.ObjectRepository.HomePage;
import com.vtiger.ObjectRepository.LoginPage;

public class GetterandSetterForListner {

	private static GetterandSetterForListner instance;

	public WebDriver driver;
	public LoginPage loginpage;
	public HomePage homepage;
	public  FileDataUtility fileutility;
	public ExcelsheetUtility excelutility;
	public JavaJdkUtility javautility;
	public WebDriverBrowserUtility webdriverutility;
	public int randomNumber;
	public static String username;
	public static String password;
	public String browser;
	public String url;
	public long longTimeOut;


	private GetterandSetterForListner() {}
	public static GetterandSetterForListner getInstance() {
		if(instance==null) {
			instance=new GetterandSetterForListner();	
		} 
		return instance;
	}

	public LoginPage getLoginPage() {
		return loginpage;	
	}

	public HomePage getHomePage( ) {
		return homepage;
	}

	public FileDataUtility getFileDataUtility() {
		return fileutility;
	}
	
	public JavaJdkUtility getExcelsheetUtility() {
		return javautility;
	
	}
	
	public WebDriverBrowserUtility getwebBrowserUtility() {
		return webdriverutility;
	}
	
	public int getRandomNumber() {
		return randomNumber;
	}
	public static String getPassword() {
		return password;
	}
	
	public String getBrowser() {
		return browser;
	}
	
	public String getUrl() {
		return url;
	}
	
	public long getlongTimeOut() {
		return longTimeOut;
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public void setDriver(WebDriver driver) {
		this.driver=driver;
	}
	public void setLoginPage(LoginPage loginpage) {
		this.loginpage=loginpage;
	}
	public void setHomePage(HomePage homepage) {
		this.homepage=homepage;
	}
	
	public void setFileDataUtility(FileDataUtility  fileutility) {
		this.fileutility=fileutility;
	}
	
	public void setExcelsheetUtility(ExcelsheetUtility excelutility) {
		this.excelutility=excelutility;
	}
	
	public void setJavaJdkUtility(JavaJdkUtility javautility) {
		this.javautility=javautility;
	}
	
	public void setWebDriverBrowserUtility(WebDriverBrowserUtility webdriverutility) {
		this.webdriverutility=webdriverutility;
	}
	
	public void setRandomNumber(int randomNumber) {
		this.randomNumber=randomNumber;
	}
	public static void setUsername(String username) {
		GetterandSetterForListner.username=username;
	}
	
	public static void setPassword(String password) {
		GetterandSetterForListner.password=password;
	}
	
	public void setBrowser(String browser) {
		this.browser=browser;
	}
	
	public void setUrl(String url) {
		this.url=url;
	}
	
	public void setLongTimeOut(long longTimeOut) {
		this.longTimeOut=longTimeOut;
	}
}
