package com.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClickSearchProductPage 
{

	@FindBy(xpath="//img[@alt='Select']")
	private WebElement clickproductBtn;

	public ClickSearchProductPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public void clickOnSearchProduct()
	{
		clickproductBtn.click();
	}
}
