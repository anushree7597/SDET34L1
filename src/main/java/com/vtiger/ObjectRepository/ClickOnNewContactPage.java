package com.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClickOnNewContactPage 
{
	@FindBy(xpath="//img[@title='Create Contact...']")
	private WebElement clickNewContactBtn;

	public ClickOnNewContactPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public void clickNewcontactButton()
	{
		clickNewContactBtn.click();
	}
}
