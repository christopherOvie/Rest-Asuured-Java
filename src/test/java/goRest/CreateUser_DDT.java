package goRest;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateUser_DDT {
	
	
	HashMap<String,String> hm = new HashMap<String, String>();
	

		@Test
	public void createNewUser() throws Exception {
			
	        FileInputStream fin = new FileInputStream("C:\\Users\\odibo ovie\\Documents\\data.xlsx");
			XSSFWorkbook wb = new XSSFWorkbook(fin);//workbook in the file
			XSSFSheet    ws = wb.getSheet("Sheet2");//get sheet in the workbook
			Row row;
			
		for(int r  =1;r<=ws.getLastRowNum();r++)    //for all the rows in the sheet
        {
			row=ws.getRow(r);
			hm.put("name", row.getCell(0).getStringCellValue());
			hm.put("email", row.getCell(1).getStringCellValue());
			hm.put("gender", row.getCell(2).getStringCellValue());
			hm.put("status", row.getCell(3).getStringCellValue());
    given()
			.header("Content-type","application/json")
			.header("Authorization", "Bearer 41e83b1281f1b4006db368d3cff321b17e3d445697fd818f818b834177cf4e6c")
			.body(hm)
		
	.when()
		.post("https://gorest.co.in/public/v2/users")
		
	.then()
		.statusCode(201)
		.log().body();
   hm.clear();
	}
	}
}
