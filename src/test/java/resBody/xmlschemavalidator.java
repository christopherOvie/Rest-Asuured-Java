package resBody;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;

public class xmlschemavalidator {
	
	       @Test
	
		public void getTravellers() {
			
			
	    	   given()
	   		
	   		.queryParam("page", 1)
	   		.when()
	   		.get("http://restapi.adequateshop.com/api/Traveler")
	   		
	   		.then()
	   		.log().body()
	   		.body(RestAssuredMatchers.matchesXsdInClasspath("travell.xsd"));
		
		}

}
