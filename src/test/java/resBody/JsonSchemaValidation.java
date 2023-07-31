package resBody;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

import org.testng.annotations.*;

import io.restassured.module.jsv.JsonSchemaValidator;

public class JsonSchemaValidation {

	
	
	@Test
	public  void getUser (){
	
		given()
		
	.when()
		.get("https://gorest.co.in/public/v2/users/3750852")
		
	.then()
		.log().body();
		//.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonschema.json"));

	}

}
