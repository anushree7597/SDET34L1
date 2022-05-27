package com.tyssvtigerTest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.sdet34l1.genericUtility.BaseClass;
import com.sdet34l1.genericUtility.ExcelsheetUtility;
import com.sdet34l1.genericUtility.FileDataUtility;
import com.sdet34l1.genericUtility.IconstantPathOfPropertyUtility;
import com.sdet34l1.genericUtility.JavaJdkUtility;
import com.sdet34l1.genericUtility.WebDriverBrowserUtility;
import com.vtiger.ObjectRepository.ClickOnOrganizationButtonpage;
import com.vtiger.ObjectRepository.CreateNewOrgNamePage;
import com.vtiger.ObjectRepository.HomePage;
import com.vtiger.ObjectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationwithGroupTest extends BaseClass
{
	String orgname;
	ClickOnOrganizationButtonpage clickonOrgbtn;
	CreateNewOrgNamePage createnameorg;
	CreateNewOrgNamePage saveorg;

	@Test

	public  void createOrgWithGroup()
	{
		orgname=ExcelsheetUtility.getDataFromExcel("Organization", 4, 1)+randomNumber;

		clickonOrgbtn=new ClickOnOrganizationButtonpage(driver);
		createnameorg=new CreateNewOrgNamePage(driver);
		saveorg=new CreateNewOrgNamePage(driver);
		
		homePage.clickOrgTab();
		clickonOrgbtn.clickOnNewOrg();
		createnameorg.createnewOrgname(orgname);
		createnameorg.selectRadioGroup();
		jutil.assertionThroughIfCondition(orgname, orgname);
		homePage.signOut(driver);
	}
}
