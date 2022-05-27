package com.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34l1.genericUtility.WebDriverBrowserUtility;

public class CreateNewOrgNamePage {
	@FindBy(xpath="//input[@name='accountname']")
	private WebElement createNewOrgTxt;
	
	@FindBy(xpath="//select[@name='industry']")
	private WebElement selectIndusdrop;
	
	@FindBy(xpath="//select[@name='accounttype']")
	private WebElement selectTypeDrop;
	
	@FindBy(xpath="//input[@name='assigntype'][2]")
	private WebElement selectGrpradio;
	
	@FindBy(xpath="//input[@value='T']")
	private WebElement selectgroup;
	
	@FindBy(xpath="//span[@id='dtlview_Organization Name']")
	private WebElement validateorgname;
	
	@FindBy(xpath="//input[@name='emailoptout']")
	private WebElement emailWithOrganization;
	
	@FindBy(xpath="//select[@name='rating']")
	private WebElement selectRating;
	
	@FindBy(xpath="//input[@class='crmbutton small save']")
	private WebElement clickOnSaveBtn;

	@FindBy(xpath="//span[@class='dvHeaderText']")  
	private WebElement title;
	
	public CreateNewOrgNamePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public void createnewOrgname(String orgname)
	{

		createNewOrgTxt.sendKeys(orgname);
	}
	
	public void createIndusdrop(String text)
	{
		WebDriverBrowserUtility.InitializeSelect(selectIndusdrop);
		WebDriverBrowserUtility.dropDownHandleByvalue(text, selectIndusdrop);
	}
	
	public void createTypedrop1(String text)
	{
		WebDriverBrowserUtility.InitializeSelect(selectTypeDrop);
		WebDriverBrowserUtility.dropDownHandleByvalue(text, selectTypeDrop);
	}
	
	public void selectTheRatingForOrganization(String text)
	{
		WebDriverBrowserUtility.InitializeSelect(selectRating);
		WebDriverBrowserUtility.dropDownHandleByvalue(text, selectRating);
	}

	public void selectRadioGroup()
	{
		selectGrpradio.click();
	}
	
	public String validateOrgname()
	{
		return validateorgname.getText();
	}
	
	public void organizationWithEmail()
	{
		emailWithOrganization.click();
	}
	
	public void createGroup()
	{
		selectgroup.click();
	}
	
	public void saveOrganization(WebDriver driver)
	{
		clickOnSaveBtn.click();
		WebDriverBrowserUtility.explicitlyWait(driver, 10);
		WebDriverBrowserUtility.waitUntillElementVisible(title);
	}
	

}
