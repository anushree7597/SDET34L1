package com.sdet34l1.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class is used to maintain all excel specific common methods
 * @author DELL
 *
 */
public class ExcelsheetUtility
{
	static Workbook wb;
	public static void openExcel(String filepath) throws EncryptedDocumentException, IOException
	{
		FileInputStream fisexe = new FileInputStream(filepath);
		wb= WorkbookFactory.create(fisexe);
	}
	/**This method is used to fetch the data from the excel
	 * This 
	 * @param sheetName
	 * @param rowNumber
	 * @param cellNumber
	 * @return
	 */
	public static String getDataFromExcel(String sheetName,int rowNumber, int cellNumber)
	{
		String data=wb.getSheet(sheetName).getRow(rowNumber).getCell(cellNumber).getStringCellValue();
		return data;	
	}

	/**
	 * This method is used to set the value into the excel
	 * @param sheetName
	 * @param rowNumber
	 * @param cellNumber
	 * @return
	 */
	public static void setDataToExcel(String sheetName, int rowNumber, int cellNumber,String value)
	{
		wb.getSheet(sheetName).getRow(rowNumber).createCell(cellNumber).setCellValue(value);

	}

	/**
	 * This method is used to write the data in the excel
	 * @param fileSavePtah
	 * @throws IOException 
	 */
	public static void saveExcelData(String fileSavePtah) throws IOException
	{
		try {
			FileOutputStream fos = new FileOutputStream(fileSavePtah);
			wb.write(fos);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to Close the excel
	 * @throws IOException
	 */
	public static void closeExcel()
	{
		try {
			wb.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("while closing excel some exception occured....please check the ExcelBrowserUtility");
		}
	}

}


