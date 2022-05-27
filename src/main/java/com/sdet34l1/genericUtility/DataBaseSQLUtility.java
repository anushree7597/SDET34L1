package com.sdet34l1.genericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Statement;
import com.mysql.cj.jdbc.Driver;

/**
 * This class is used to maintain all the data base specific common methods
 * @author DELL
 *
 */
public class DataBaseSQLUtility 
{
	static Connection connection;
	static Statement statement;
	/**
	 * This method is used to open the database connection and initialize the connection, statement
	 * @param dBUrl
	 * @param dbUserName
	 * @param dbPassword
	 * @throws SQLException
	 */
	public static void openDBConnection(String dBUrl, String dbUserName, String dbPassword) throws SQLException
	{
		Driver d=new Driver();
		DriverManager.registerDriver(d);
		connection=DriverManager.getConnection(dBUrl, dbUserName, dbPassword);
		statement=connection.createStatement(); 
	}



	/**
	 * This method is used fetch data from the database/to do the DQL actions on database
	 * @param query
	 * @param columnName
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<String> getDataFromDataBase(String query, String columnName) throws SQLException
	{
		ArrayList<String> list = new ArrayList<>();
		ResultSet result = statement.executeQuery(query);
		while(result.next())
		{
			list.add(result.getString(columnName));
		}
		return list;
	}


	/**
	 * This method is used to validate the data whether it is present in database or not
	 * @param query
	 * @param columnName
	 * @param expectedData
	 * @return
	 */
	public static boolean validateDataInDatabase(String query, String columnName, String expectedData)
	{
		ArrayList<String> list = null;
		try {
			list = getDataFromDataBase(query, columnName);
		} catch (SQLException e) 
		{

			e.printStackTrace();
		}
		boolean flag=false;
		for(String actualData:list)
		{
			if(actualData.equalsIgnoreCase(expectedData))
			{
				flag=true;
				break;
			}
		}
		return flag;
	}




	/**
	 * This method is used to store/modify/insert/delete the data in database /to do the DML and DDL actions on database
	 * @param query
	 */
	public static void setDataInDataBase(String query)
	{
		int result = 0;
		try {
			result = statement.executeUpdate(query);
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		if(result>=1)
		{
			System.out.println("Data entered/modified successfully");
		}
	}

/**
 * This method is used close the data base connection 
 */
	public static void closeDBConnection()
	{
		try {
			connection.close();
		}
		catch(SQLException e) 
		{
			System.out.println(e.getMessage());
			System.out.println("while closing the Database connection we get exception");
		}
	}
}




