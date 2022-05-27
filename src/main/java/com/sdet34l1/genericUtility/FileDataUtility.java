package com.sdet34l1.genericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileDataUtility 
{
	static Properties property;
	/**
	 * This method is used to open the property file
	 * @return 
	 * @throws IOException
	 */

	public static  void openPropertyFile(String filepath) throws IOException

	{
		FileInputStream fis = new FileInputStream(filepath);
		property=new Properties();
		property.load(fis);
	}
	
	/**
	 * This method is used get the data from the property file
	 * @param key
	 * @return
	 */
	public static String getDataFromPropertyFile(String key)
	{
		String value = property.getProperty(key);
		return value;
		
	}

    }
