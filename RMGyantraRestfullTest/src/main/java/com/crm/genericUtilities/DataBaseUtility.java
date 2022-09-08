package com.crm.genericUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

import com.mysql.cj.jdbc.Driver;



/**
  * 
  * @author Nishchitha
  *
  */
public class DataBaseUtility {


	static Driver driver;
	static Connection connection;
	static ResultSet result;	
	
		
	public void connectToDB(String DBname)throws SQLException
	{
	
	try {
		   driver=new Driver();
		DriverManager.registerDriver(driver);
			
		connection=DriverManager.getConnection(IConstants.DbUrl+DBname,IConstants.DBUsername,IConstants.DBPassword);
		System.out.println("database connection is successful");
	}
   catch(SQLException e)	
	{
	   e.printStackTrace();
	}
}
	/**
	 * Closing DB
	 */
	public void closeDB() throws SQLException
	{
		try {
			connection.close();
		System.out.println("close DB connection");
		}
		catch(Exception e)
		{
			
		}
	}
	
	public boolean executeQuery(String query, int columnNumber,String expectedData) throws Throwable
	{
		result=connection.createStatement().executeQuery(query);
		boolean flag = false;
		while(result.next())
		{
			if(result.getString(columnNumber).contains(expectedData))
			{
				flag=true;
				break;
			}
		}
		if(flag==true)
		{
			System.out.println("data is present");
			return flag;
		}else
		{
			System.out.println("data is not present");
			return flag;
		}
	}
	/**
	 * this methods id used to perform execute update
	 * @param query
	 * @throws Throwable
	 */
	
	public void executeUpdate(String query) throws Throwable
	{
		int result = connection.createStatement().executeUpdate(query);
		if(result==1)
		{
			System.out.println("data is updated");
		}else
		{
			System.out.println("data is not updated");
		}
	}
	
}
