package com.vtiger.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34l1.genericUtility.WebDriverBrowserUtility;

public class SearchProductIntextPage 
{
	@FindBy(name="search_text")
	private WebElement searchProduct;

	@FindBy(name="search")
	private WebElement searchproductbutton;

	public  SearchProductIntextPage (WebDriver driver)
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
	
	public void selectProducts(String productName, WebDriver driver)
	{
		WebDriverBrowserUtility.switchToWindowBasedOnTitle(driver, productName);
		searchProduct.sendKeys(productName);;
		searchproductbutton.click();
		driver.findElement(By.xpath("//a[@id='1']")).click();
		WebDriverBrowserUtility.switchToWindowBasedOnTitle(driver, "Campaigns");
	}
	

}
