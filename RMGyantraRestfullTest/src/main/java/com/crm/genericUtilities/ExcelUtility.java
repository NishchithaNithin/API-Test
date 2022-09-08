package com.crm.genericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * 
 * @author SanjayBabu
 *
 */
public class ExcelUtility {
	/**
	 *its used to read the data from excel file  
	 * @return
	 */
	public String readDataFromExcel(String sheetName,int rowNum,int cellNum){
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(IConstants.excelPath);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Workbook workbook = null;
		try {
			workbook = WorkbookFactory.create(fileInputStream);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rowNum);
		Cell cell = row.getCell(cellNum);
		//String data = cell.toString();
		DataFormatter dataFormatter = new DataFormatter();
		String data=dataFormatter.formatCellValue(cell);
		return data;
	}
	
	/**
	 * its used to write data into excel file
	 * @param sheetName
	 * @return
	 * @throws Throwable
	 */
	public void writeDataIntoExcel(String sheetName,int rowNum,int cellNum,String data) {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(IConstants.excelPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Workbook workbook = null;
		try {
			workbook = WorkbookFactory.create(fileInputStream);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rowNum);
		Cell cell = row.createCell(cellNum);
		cell.setCellValue(data);
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(IConstants.excelPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			workbook.write(fileOutputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
