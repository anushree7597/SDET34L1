package com.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ValidateProductnamePage 
{
	@FindBy(xpath="//span[@class='lvtHeaderText']")
	private WebElement confirmNameText;

	public  ValidateProductnamePage (WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public  String confirmProductName(String text)
	{
		return confirmNameText.getText();
	}
}
