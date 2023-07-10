package emp;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

import org.testng.annotations.*;

import goRest.Payload;

public class EmployeeAPI {
	
	//create a method in diferent class which returns String(json content)
	// ex payload(class)... getdata(method)
	@Test(priority=1)
public void newEmp() {
	
	given()
	
	   .header("Content-type","application/json")
	    .body(Payload.empdata())
	
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
