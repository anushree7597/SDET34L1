package com.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClickOnNewDocumentsPage {

	@FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement clickOnDocumentBtn;
	
	public ClickOnNewDocumentsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void clickDocumentbutton() 
	{
		clickOnDocumentBtn.click();
	}
}
