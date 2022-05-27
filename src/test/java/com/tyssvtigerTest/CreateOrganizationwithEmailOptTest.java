
package com.tyssvtigerTest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import com.vtiger.ObjectRepository.CreateNewOrgNamePage;
import com.vtiger.ObjectRepository.HomePage;
import com.vtiger.ObjectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationwithEmailOptTest extends BaseClass
{
	String orgname;
	ClickOnOrganizationButtonpage clickonOrganizationbtn;
	CreateNewOrgNamePage createnameOrganization;
	CreateNewOrgNamePage organizationSave;
	
@Test

	public  void createOrganizationWithEmail()
	{
		orgname=ExcelsheetUtility.getDataFromExcel("Organization", 5, 1)+randomNumber;
		
		clickonOrganizationbtn=new ClickOnOrganizationButtonpage(driver);
		createnameOrganization=new CreateNewOrgNamePage(driver);
		organizationSave=new CreateNewOrgNamePage(driver);

		loginPage.loginActions(username, password);
		homePage.clickOrgTab();
		clickonOrganizationbtn.clickOnNewOrg();
		createnameOrganization.createnewOrgname(orgname);
		createnameOrganization.organizationWithEmail();
		createnameOrganization.validateOrgname();
		jutil.assertionThroughIfCondition(orgname, orgname);
		createnameOrganization.saveOrganization(driver);
	
	}
}
