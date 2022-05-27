package com.vtiger.organizationTest;

import org.testng.annotations.Test;

import com.sdet34l1.genericUtility.BaseClass;
import com.sdet34l1.genericUtility.ExcelsheetUtility;
import com.vtiger.ObjectRepository.ClickOnOrganizationButtonpage;
import com.vtiger.ObjectRepository.CreateNewOrgNamePage;

public class CreateOrganizationTest extends BaseClass
{
	String organizationName;
	ClickOnOrganizationButtonpage clickonOrgbtn;
	CreateNewOrgNamePage createnameorganization;
	CreateNewOrgNamePage saveorg;

	@Test(groups="sanity")

	public  void createOrganization() throws InterruptedException
	{
		String organizationName = ExcelsheetUtility.getDataFromExcel("Organization", 4, 1)+randomNumber;

		clickonOrgbtn=new ClickOnOrganizationButtonpage(driver);
		createnameorganization=new CreateNewOrgNamePage(driver);
		saveorg=new CreateNewOrgNamePage(driver);
		homePage.clickOrgTab();
		clickonOrgbtn.clickOnNewOrg();
		createnameorganization.createnewOrgname(organizationName);
		createnameorganization.saveOrganization(driver);
	}
}

