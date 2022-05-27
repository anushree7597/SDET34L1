package com.tyssvtigerTest;

import org.testng.annotations.Test;

import com.sdet34l1.genericUtility.BaseClass;
import com.sdet34l1.genericUtility.ExcelsheetUtility;
import com.vtiger.ObjectRepository.ClickOnOrganizationButtonpage;
import com.vtiger.ObjectRepository.CreateNewOrgNamePage;

public class CreateOrganizationwithAdministratorTest extends BaseClass
{
	String orgname;
	ClickOnOrganizationButtonpage clickonOrganizationbtn;
	CreateNewOrgNamePage createNameorganization;
	
	@Test
	public  void createOrganizationWithAministrator()
	{
		
		String orgname=ExcelsheetUtility.getDataFromExcel("Organization", 5, 1)+randomNumber;

		clickonOrganizationbtn=new ClickOnOrganizationButtonpage(driver);
		createNameorganization=new CreateNewOrgNamePage(driver);
		
		homePage.clickOrgTab();
		clickonOrganizationbtn.clickOnNewOrg();
		createNameorganization.createnewOrgname(orgname);
		createNameorganization.saveOrganization(driver);
		createNameorganization.validateOrgname();
		
		jutil.assertionThroughIfCondition(orgname, orgname);
		
	}
}
