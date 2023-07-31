package resBody;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

public class User_Create_faker {
	
	@Test
	public void createUser() {
		
		Users user= new Users();
		Faker fake = new Faker();
		
		user.setName(fake.name().fullName());
		user.setEmail(fake.internet().safeEmailAddress());
		user.setGender("male");
		user.setStatus("active");
		
		//ObjectMapper obj = new ObjectMapper();
		given()
		.header("Content-type","application/json")
		.header("Authorization","Bearer 41e83b1281f1b4006db368d3cff321b17e3d445697fd818f818b834177cf4e6c")
		.body(user)
		.log().all()
			
		.when()
			.post("https://gorest.co.in/public/v2/users")
		.then()	
		.statusCode(201)
		.log().all();	
		
	}

	

}
