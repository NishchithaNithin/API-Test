package com.crm.genericUtilities;

import java.util.Date;
import java.util.Random;

/**
 * 
 * @author SanjayBabu
 *
 */
public class JavaUtility {
	/**
	 * its used to generate a random number
	 * @return
	 */
	public int getRandomNumber() {
		Random random=new Random();
		int randNum = random.nextInt(1000);
		return randNum;
	}
	/**
	 * its used to get systemDateAndTime in IST Format
	 * @return
	 */
	public String getSystemDateAndTimeInISTformat() {
		Date date=new Date();
		return date.toString();
	}
	/**
	 * its used to get system date and Time in required format
	 * @return
	 */
	public String getSystemDateAndTimeInFormat() {
		Date date=new Date();
		String dateAndTime = date.toString();

		String YYYY = dateAndTime.split(" ")[5];
		String DD = dateAndTime.split(" ")[2];
		int MM = date.getMonth()+1;

		String finalFormat = YYYY+" "+DD+" "+MM;
		return finalFormat;
	}
}
