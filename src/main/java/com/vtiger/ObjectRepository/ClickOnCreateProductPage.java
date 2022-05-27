package com.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClickOnCreateProductPage 
{
	@FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement clickoncreateProduct;

	public ClickOnCreateProductPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public void clickOnProductbtn()
	{
		clickoncreateProduct.click();
	}
}
