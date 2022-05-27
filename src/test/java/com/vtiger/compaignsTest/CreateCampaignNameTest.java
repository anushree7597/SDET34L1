package com.vtiger.compaignsTest;

import org.testng.annotations.Test;

import com.sdet34l1.genericUtility.BaseClass;
import com.sdet34l1.genericUtility.ExcelsheetUtility;
import com.vtiger.ObjectRepository.CampaignValidatePage;
import com.vtiger.ObjectRepository.CreateCampaignPage;
import com.vtiger.ObjectRepository.CreateNewCampaignPage;

public class CreateCampaignNameTest extends BaseClass
{
	String campaignName;
	CreateCampaignPage campaignPage;
	CreateNewCampaignPage createNewCampaign;
	CampaignValidatePage campaignValidatePage;


	@Test
	public  void  createCampaignTest()
	{
		campaignName = ExcelsheetUtility.getDataFromExcel("Organization",4,1)+randomNumber;
		campaignPage=new CreateCampaignPage(driver);
		createNewCampaign=new CreateNewCampaignPage(driver);
		campaignValidatePage=new CampaignValidatePage(driver);
		homePage.clickCampaign(driver);
		createNewCampaign.clickNewCampaign();
		campaignPage.enterDatatoTextfeild(campaignName);	
		campaignPage.saveCampaignName();
		jutil.assertionThroughIfCondition(campaignName, campaignName);
		
	}
}