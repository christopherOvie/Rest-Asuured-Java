package goRest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Read {

	public static void main(String[] args) throws IOException {
		
         FileInputStream fin = new FileInputStream("C:\\Users\\odibo ovie\\Documents\\data.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fin);
		XSSFSheet    ws = wb.getSheet("Sheet1");//get sheet in the workbook
		
		Row row;
		
	for(int r=0;r<ws.getLastRowNum();r++)
	        {
			
		   row=	ws.getRow(r);
		   for(int c=0;c<row.getLastCellNum();c++) {
		        System.out.println(row.getCell(c).getStringCellValue());	
		      };
		}

	}

}
