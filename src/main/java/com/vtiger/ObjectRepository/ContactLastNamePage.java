package com.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactLastNamePage {

	@FindBy(name="lastname")
	private WebElement lastNameTxt;
	
	@FindBy(xpath="//img[@title='Select']")
	private WebElement organizationNewBtn;

	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveLastnameBtn;

	public ContactLastNamePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public void enterLastName(String lastname)
	{
		lastNameTxt.sendKeys(lastname);
	}
	
	public void searchNewOrg()
	{
		organizationNewBtn.click();
	}

	public void saveLastName()
	{
		saveLastnameBtn.click();
	}
}