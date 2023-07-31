package goRest;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

import org.testng.annotations.*;


public class User {

	
	@Test
	public void newUser() {
		
		given()
		
		.when()
		.get("https://gorest.co.in/public/v2/users")
		
		.then()
		.statusCode(200);
	}
	
	@Test
	public void digest() {
		
		given()
		
		.when()
		.get("https://gorest.co.in/public/v2/users")
		
		.then()
		.statusCode(200);
	}

}
