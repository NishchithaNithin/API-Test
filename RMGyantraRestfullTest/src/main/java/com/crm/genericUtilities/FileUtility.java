package com.crm.genericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
/**
 * 
 * @author Nishchitha
 *
 */
public class FileUtility {
	/**
	 * its is used get common data from property file based on the key which you have specified as a argument
	 * @return
	 */
	public String getPropertKeyValue(String key){
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(IConstants.filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties pres=new Properties();
		try {
			pres.load(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String value = pres.getProperty(key);
		return value;
	}
}