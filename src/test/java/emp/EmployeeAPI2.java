package emp;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.Test;

import goRest.Payload;


public class EmployeeAPI2 {
	
	//Hashmap  
	//put the data ino hashmap, which stores values like <key> value
	@Test(priority=1)
	public void newEmp() {
		
		HashMap data= new HashMap();//its one of collection in java
		data.put("empname", "peterson");//stores value like key and value
		data.put("desig", "qa");
		
		String arr []= {"readingBooks", "travels"};
		data.put("hobbies", arr);
			given()
		.header("Content-type","application/json")
		  .body(data)
		
		.when()
		   .post("http://localhost:3000/Employees")
		   
		 .then() 
		 .statusCode(201)
		 .body("empname", equalTo("peterson"))
		 .log().body();
			   
		
	}
		@Test(priority=2)
		public void deleteEmp() {
			given()
			
			.when()
			.delete("http://localhost:3000/Employees/4")
			.then()
			.log().all();
		}


}
