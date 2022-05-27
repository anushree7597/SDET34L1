package com.vtiger.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34l1.genericUtility.WebDriverBrowserUtility;

public class SearchOrganizationPage {
	
	
	@FindBy(xpath="//img[@tabindex=\"\"]")
	private WebElement organizationNewButton;
	

	@FindBy(xpath="//input[@name='search_text']")
	private WebElement searchOrganizationText;
	
	@FindBy(xpath="//input[@type='button']")
	private WebElement clickOnSearchBtn;
	
	
	public SearchOrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void switchToChild(WebDriver driver,String title)
	{
		WebDriverBrowserUtility.switchToChild(driver, title);
	}
	public void switchToParent(WebDriver driver,String title)
	{
		WebDriverBrowserUtility.switchToChild(driver, title);
	}
	
	public void newOrganizationButton()
	{
		organizationNewButton.click();
	}
	
	public void selectOrganization(String organizationName, WebDriver driver)
	{
		WebDriverBrowserUtility.switchToWindowBasedOnTitle(driver, "Organizations");
		searchOrganizationText.sendKeys(organizationName);
		clickOnSearchBtn.click();
		driver.findElement(By.xpath("//a[@id='1']")).click();
		WebDriverBrowserUtility.switchToWindowBasedOnTitle(driver, "Contacts");
	}
	
	
	
	
	
	
	
}
