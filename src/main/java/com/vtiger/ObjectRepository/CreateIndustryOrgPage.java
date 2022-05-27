package com.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateIndustryOrgPage 
{
	@FindBy(xpath="//select[@name='industry']")
	private WebElement selectDropdown;
	
	@FindBy(xpath="//select[@name='accounttype']")
	private WebElement selectDropdownType;

	public void CreateDropdownIndustry(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void ClickOnIndustry(String text)
	{
		selectDropdown.click();
	}
	
	public void ClickonType(String text)
	{
		selectDropdownType.click();
	}
}
