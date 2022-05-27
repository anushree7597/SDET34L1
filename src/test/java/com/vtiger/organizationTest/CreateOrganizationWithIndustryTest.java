package com.vtiger.organizationTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.sdet34l1.genericUtility.BaseClass;
import com.sdet34l1.genericUtility.ExcelsheetUtility;
import com.sdet34l1.genericUtility.FileDataUtility;
import com.sdet34l1.genericUtility.IconstantPathOfPropertyUtility;
import com.sdet34l1.genericUtility.JavaJdkUtility;
import com.sdet34l1.genericUtility.WebDriverBrowserUtility;
import com.vtiger.ObjectRepository.ClickOnOrganizationButtonpage;
import com.vtiger.ObjectRepository.CreateIndustryOrgPage;
import com.vtiger.ObjectRepository.CreateNewOrgNamePage;
import com.vtiger.ObjectRepository.HomePage;
import com.vtiger.ObjectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationWithIndustryTest extends BaseClass
{
	String organizationName;
	ClickOnOrganizationButtonpage clickonOrgbtn;
	CreateNewOrgNamePage createnameorg;
	CreateNewOrgNamePage inddrop;
	CreateNewOrgNamePage typedrop;
	CreateNewOrgNamePage saveorg;

	@Test(groups="sanity")
	public  void createOrgWithIdustry() throws InterruptedException
	{

		organizationName = ExcelsheetUtility.getDataFromExcel("Organization", 4, 1)+randomNumber;

		clickonOrgbtn=new ClickOnOrganizationButtonpage(driver);
		createnameorg=new CreateNewOrgNamePage(driver);
		inddrop= new CreateNewOrgNamePage(driver);
		typedrop=new CreateNewOrgNamePage(driver);
		saveorg=new CreateNewOrgNamePage(driver);

		homePage.clickOrgTab();
		clickonOrgbtn.clickOnNewOrg();
		createnameorg.createnewOrgname(organizationName);
		inddrop.createIndusdrop("Education");
		typedrop.createTypedrop1("Press");	
		createnameorg.saveOrganization(driver);

	}
}
