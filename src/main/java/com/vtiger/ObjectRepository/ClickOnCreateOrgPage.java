package com.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClickOnCreateOrgPage {

	@FindBy(xpath="//img[@tabindex='']")
	private WebElement createOrgBtn;
	
	public  ClickOnCreateOrgPage (WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnNewOrganization(WebDriver driver)
	{
		createOrgBtn.click();
	}
	
	
}
