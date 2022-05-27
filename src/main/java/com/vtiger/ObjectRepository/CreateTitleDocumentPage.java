package com.vtiger.ObjectRepository;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateTitleDocumentPage
{
	@FindBy(xpath="//input[@name='notes_title']")
	private WebElement createTitleTxt;
	
	@FindBy(xpath="//input[@name='notes_title']")
	private WebElement enterTitleText;
	
	@FindBy(xpath="//body[@class='cke_show_borders']")
	private WebElement frametext;
	
	@FindBy(id="cke_5")
	private WebElement boldText;
	
	@FindBy(id="cke_6")
	private WebElement italicText;
	
	@FindBy(xpath="//b[.='File Information']/../../following-sibling::tr[4]//input[@title='Save [Alt+S]']")
	private WebElement savedoc;
	
	@FindBy(xpath="//input[@name='filename']")
	private WebElement saveFile;

	public CreateTitleDocumentPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public void createDocumentTitle()
	{
		createTitleTxt.click();
	}
	
	public void createTextTitle(String titlename)
	{
		enterTitleText.sendKeys(titlename);
	}
	
	public void enterframetext(String text)
	{
		frametext.sendKeys(text, Keys.CONTROL+"a");
	}
	
	public void boldTheText() {
		boldText.click();
	}
	
	public void italicTheText() {
		italicText.click();
	}
	
	public void documentFile(String text)
	{
		saveFile.sendKeys(text);
	}
	
	public void saveTheDoc() {
		savedoc.click();
	}
	
}
