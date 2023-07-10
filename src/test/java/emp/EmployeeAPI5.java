package emp;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.json.JSONObject;
import org.testng.annotations.Test;

public class EmployeeAPI5 {
	@Test(priority=1)
	public void newEmp() {
		
		
		JSONObject data= new JSONObject();
		data.put("empname", "peterson");//stores value like key and value
		data.put("desig", "qa");
		String arr []= {"readingBooks", "travels"};
		data.put("hobbies", arr);
		
	given()
		.header("Content-type","application/json")
		  .body(data.toString())  //.body if it is json object
		
	.when()
		   .post("http://localhost:3000/Employees")
		   
	.then() 
		 .statusCode(201)
		 .body("empname", equalTo("peterson"))
		 .body("desig", equalTo("qa"))
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
