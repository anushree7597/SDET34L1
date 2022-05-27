package com.sdet34l1.genericUtility;


import java.text.SimpleDateFormat;
import java.util.Random;

import org.apache.poi.hpsf.Date;
import org.openqa.selenium.WebElement;

/**
 * This class contains only java specific reusable methods
 * @author DELL
 *
 */

public class JavaJdkUtility 
{
	/**
	 * This method is used to convert string value to long datatype
	 * @param value
	 * @return
	 */
	public long stringToLong(String value)
	{
		return Long.parseLong(value);

	}
	/**
	 * This method used to get the random number
	 * @param limit
	 * @return
	 */
	public int getRandomNumber(int limit)
	{
		Random ran=new Random();
		return ran.nextInt(limit);

	}

	/**
	 * This message is used to print the message
	 * @param message
	 */
	public void printStatement(String message)
	{
		System.out.println(message);
	}	

	/**
	 * This method is used to print the statement
	 * @param ele
	 * @param value
	 */
	public void ifValidation(String ele, String value)
	{
		if(ele.contains("value"))
		{
			System.out.println("Validated");
		}

		else
		{
			System.out.println("Not validated");
		}
	}

	/**
	 * This method is used to verify
	 * @param actualResult
	 * @param expectedResult
	 * @param testCaseName
	 */
	public  String assertionThroughIfCondition(String actualResult,String expectedResult)
	{
		if(actualResult.contains(expectedResult))
		{
			System.out.println("created successfully");
			System.out.println("tc pass");
		}
		return expectedResult;
	}


	/**
	 * 
	 * @param element
	 * @param pollingTime
	 * @param duration
	 * @throws InterruptedException
	 */
	public void customWait(WebElement element, long pollingTime, int duration) throws InterruptedException
	{
		int count=0;
		while(count<=duration)
		{
			try {
				element.click(); 
				break;
			}
			catch(Exception e)
			{
				Thread.sleep(pollingTime);
				count++;
			}
		}
	}


	/**
	 * This method is used to get date
	 * @return
	 */
	public String dateTimeInFormat()
	{
		return new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
	}
}