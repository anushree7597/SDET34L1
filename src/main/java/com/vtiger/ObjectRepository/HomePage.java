package com.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34l1.genericUtility.WebDriverBrowserUtility;


// create the class as webpage name and make it as public
public class HomePage 
//declare all the elements and specify the access specifier as private
{
	@FindBy(linkText="More")
	private WebElement moreDropDown;

	@FindBy(name="Campaigns")
	private WebElement campaignTab;

	@FindBy(xpath="//a[@href='index.php?module=Contacts&action=index']")
	private WebElement contactsTab;

	@FindBy(xpath="//a[.='Organizations']")
	private WebElement organizationsTab;

	@FindBy(xpath="//a[.='Products']")
	private WebElement clickOnProductTab;

	@FindBy(xpath="//a[.='Documents']")
	private WebElement clickOnDocumentTab;

	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement administratorIcon;

	@FindBy(xpath="//a[.='Sign Out']")
	private WebElement signOutLink;

	//initialize the driver address to all the elements through constructors and make it as public
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//business lib
	public void clickCampaign(WebDriver driver)

	{
		WebDriverBrowserUtility.mouseHoverOnTheElement(moreDropDown,driver);
		campaignTab.click();
	}

	public void clickContactTab()
	{
		contactsTab.click();
	}
	public void clickOrgTab()
	{
		organizationsTab.click();
	}

	public void clickOnProduct()
	{
		clickOnProductTab.click();
	}

	public void clickOnDocument() 
	{
		clickOnDocumentTab.click();
	}


	public void signOut(WebDriver driver)
	{
		WebDriverBrowserUtility.mouseHoverOnTheElement(administratorIcon,driver);
		signOutLink.click();
	}
}
