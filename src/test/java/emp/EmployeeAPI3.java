package emp;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.json.JSONTokener;

import org.testng.annotations.Test;

import goRest.Payload;

public class EmployeeAPI3 {
	//us json
	@Test(priority=1)
public void newEmp() throws IOException {
	
//		File file = new File("./testdata/emp.json");
//		FileReader fr = new FileReader(file);
//		JSONTokener token = new JSONTokener(fr);
//		JSONObject data= new JSONObject(token);
		
	byte arr[]=	Files.readAllBytes(Paths.get("./testdata/emp.json"));
	String  data = new String(arr);
	given()
	
	   .header("Content-type","application/json")
	    .body(data.toString())
	
	.when()
	   .post("http://localhost:3000/Employees")
	   
	 .then() 
	 .statusCode(201)
	 .body("empname", equalTo("peterson"))
	 .log().body();
		   
	
}
	//@Test(priority=2)
	public void deleteEmp() {
		given()
		
		.when()
		.delete("http://localhost:3000/Employees/4")
		.then()
		.log().all();
	}


}
