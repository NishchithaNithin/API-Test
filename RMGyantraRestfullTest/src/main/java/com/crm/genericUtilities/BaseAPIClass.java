package com.crm.genericUtilities;

import java.sql.SQLException;

/**
 * 
 * @author Nishchitha
 *
 */

public class BaseAPIClass {
	

	public JavaUtility jlib=new JavaUtility();
	public DataBaseUtility dlib=new DataBaseUtility();
	public RestAssured_library rlib=new RestAssured_library();
/**
 * 
 * @throws SQLException
 */
	@BEF
	public void bsConfig() throws SQLException
	{
		dlib.connectToDB("projects");
	}

	@AfterSuite
	
	public void asConfig() throws SQLException
	{
		dlib.closeDB();
	}
}
