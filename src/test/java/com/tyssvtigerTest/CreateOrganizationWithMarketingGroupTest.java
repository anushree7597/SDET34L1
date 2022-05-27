package com.tyssvtigerTest;

import org.testng.annotations.Test;

import com.sdet34l1.genericUtility.BaseClass;
import com.sdet34l1.genericUtility.ExcelsheetUtility;
import com.vtiger.ObjectRepository.ClickOnOrganizationButtonpage;
import com.vtiger.ObjectRepository.CreateNewOrgNamePage;

public class CreateOrganizationWithMarketingGroupTest extends BaseClass
{
	String orgname;
	ClickOnOrganizationButtonpage clickonOrgbtn;
	CreateNewOrgNamePage createnameorg;
	CreateNewOrgNamePage saveorg;
@Test

	public void createOrganizationMarketingGroup()
	{
		orgname=ExcelsheetUtility.getDataFromExcel("Organization", 4, 1)+randomNumber;

		clickonOrgbtn=new ClickOnOrganizationButtonpage(driver);
		createnameorg=new CreateNewOrgNamePage(driver);
		saveorg=new CreateNewOrgNamePage(driver);
		
		homePage.clickOrgTab();
		clickonOrgbtn.clickOnNewOrg();
		createnameorg.createnewOrgname(orgname);
		createnameorg.createGroup();
		saveorg.saveOrganization(driver);
	}

}
