package emp;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import goRest.Payload;

public class EmployeeAPI4 {
	//pojo  plain old java object.we store the variable
	@Test(priority=1)
	public void newEmp() {
		
		Employee data = new Employee();
		data.setEmpname("jonathan");
		data.setDesig("teacher");
		
		String arr[]= {"scrabble","soccer"};
		data.setHobbies(arr);
		
		given()
		 .header("Content-type","application/json")
		    .body(data)
		
		.when()
		   .post("http://localhost:3000/Employees")
		   
		 .then() 
		 .statusCode(201)
		 .body("empname", equalTo("jonathan"))
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
