package com.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateCampaignPage 
{
	
	@FindBy(name="campaignname")
	private WebElement createCampaignNameTxt;
	
	@FindBy(xpath="//input[@class='crmButton small save']")
	private WebElement saveBtn;
	
	public CreateCampaignPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void enterDatatoTextfeild(String campaignname)
	{
		createCampaignNameTxt.sendKeys(campaignname);
	}
	
	public void saveCampaignName()
	{
		saveBtn.click();
	}
}