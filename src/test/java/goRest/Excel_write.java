package goRest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_write {
	
	public static void main(String[] args) throws IOException {
		
		FileInputStream fin = new FileInputStream("C:\\Users\\odibo ovie\\Documents\\data.xlsx");
		
		XSSFWorkbook wb = new XSSFWorkbook(fin);
		XSSFSheet    ws = wb.getSheet("Sheet1");//get sheet in the workbook
		
		Row row= ws.createRow(0);
		row.createCell(0).setCellValue("ajitha");
		row.createCell(1).setCellValue("favour");
		row.createCell(2).setCellValue("ayasha");
		row.createCell(3).setCellValue("abhishek");
		
		
		row= ws.createRow(1);  //create a row in a sheet
		row.createCell(0).setCellValue("100");
		row.createCell(1).setCellValue("200");
		row.createCell(2).setCellValue("300");
		row.createCell(3).setCellValue("400");
		
		
		FileOutputStream fout = new FileOutputStream("C:\\Users\\odibo ovie\\Documents\\data.xlsx");
		wb.write(fout);
		
		
	}

}
