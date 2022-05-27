package com.vtiger.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.sdet34l1.genericUtility.FileDataUtility;
import com.sdet34l1.genericUtility.IconstantPathOfPropertyUtility;

public class FetchDataFromPropertyFile 
{
 public static void main(String[] args) throws IOException 
 {
	// convert the physical file into java readable object
	// FileInputStream fis = new FileInputStream("./src/test/resources/commonData.properties");
	 
	 //create object for properties class
	 // Properties property = new Properties();
	  
	  //load all keys 
	  //property.load(fis);
	  
	  //fetch the data by using key
	  FileDataUtility.openPropertyFile(IconstantPathOfPropertyUtility.PROPERTYFILEPATH);
		String url = FileDataUtility.getDataFromPropertyFile("url");
		String username = FileDataUtility.getDataFromPropertyFile("username");
		String password = FileDataUtility.getDataFromPropertyFile("password");
		String timeout = FileDataUtility.getDataFromPropertyFile("timeout");
		String browser = FileDataUtility.getDataFromPropertyFile("browser");

	  System.out.println(url);
	  System.out.println(username);
	  System.out.println(password);
	  System.out.println(timeout);
	  System.out.println(browser);
}
}
