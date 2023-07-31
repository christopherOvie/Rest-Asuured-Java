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

public class CreateUser_DaraProvider {
	
	HashMap<String,String> hm = new HashMap<String, String>();
	
	@DataProvider(name="d1")
	public String [][] getData() throws IOException
	{
		  FileInputStream fin = new FileInputStream("C:\\Users\\odibo ovie\\Documents\\data.xlsx");
			XSSFWorkbook wb = new XSSFWorkbook(fin);//workbook in the file
			XSSFSheet    ws = wb.getSheet("Sheet2");//get sheet in the workbook
			Row row;
			String data[][] = new String[ws.getLastRowNum()][ws.getRow(0).getLastCellNum()];
			
		for(int r = 1;r<=ws.getLastRowNum();r++)    //for all the rows in the sheet
      {
		        row=ws.getRow(r);
	            for(int c=0;c<row.getLastCellNum();c++) {
	            	data[r-1][c]=row.getCell(c).getStringCellValue();
	            }
	            }
		return data;
	   }
		@Test (dataProvider = "d1")
	public void createUser(String str[])
	{
			
		hm.put("name", str[0]);
		hm.put("email", str[1]);
		hm.put("gender", str[2]);
		hm.put("status", str[3]);
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
	//}
	}
}
