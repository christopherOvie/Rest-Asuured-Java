package reqres;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

import org.testng.annotations.*;

public class Parameteters_Ex {
	
	//@Test
	public void getUsers() {
		
//https://reqres.in/api/users/2
		given()
		.header("Content-type","application/json")
		.pathParam("p1","users")
		.queryParam("page", "2")
		.queryParam("id", 8)
		.log().all()
		
		.when()
		.get("https://reqres.in/{p1}")
		
		.then()
		.log().body();
		
	}
	//@Test
	public void getUsersTwoPathParameter() {
		
		//https://reqres.in/api/users/2
				given()
				.header("Content-type","application/json")
				.pathParam("p1","api")
				.pathParam("p2","users")
				.queryParam("page", 2)
				.queryParam("id", 8)
				//.log().all()
				
				.when()
				.get("https://reqres.in/{p1}/{p2}")
				
				.then()
				.log().body();
				
			}
	@Test
public void getUsersSpecifyAllPathParameterAsSingle() {
		
		//https://reqres.in/api/users/2
				given()
				.header("Content-type","application/json")
				.pathParams("p1","api","p2","users")//for chain of request with many parameter
				.queryParam("page", 2)
				.queryParam("id", 8)
				//.log().all()
				
				.when()
				.get("https://reqres.in/{p1}/{p2}")//no need to specify query parameters
				
				.then()
				.log().body();
				
			}
			
}
