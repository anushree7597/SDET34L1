package com.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateProductNamePage 
{
	@FindBy(name="productname")
	private WebElement createProductTxt;

	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	public CreateProductNamePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public void createProductname(String productname)
	{
		createProductTxt.sendKeys(productname);
	}
	public void saveProductname() 
	{
		saveBtn.click();
	}
}
