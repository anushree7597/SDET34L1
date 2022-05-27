package com.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClickOnOrganizationButtonpage {

	@FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement clickOnOrgBtn;
	
	public ClickOnOrganizationButtonpage (WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public void clickOnNewOrg()
	{
		clickOnOrgBtn.click();
	}
}
