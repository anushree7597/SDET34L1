package com.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignValidatePage
{
	@FindBy(xpath="//span[@id='dvHeaderText']")
	private WebElement campaignNameInformText;

	public CampaignValidatePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public  String campaignNameInformText()
	{
		return campaignNameInformText.getText();
	}
}
