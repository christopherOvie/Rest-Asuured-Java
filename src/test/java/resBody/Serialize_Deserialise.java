package resBody;

import static io.restassured.RestAssured.given;


import static io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;


import org.testng.annotations.Test;



import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Serialize_Deserialise {
	
	
	@Test
	public  void createUser () throws Exception{
	
		Users user = new Users();
		user.setEmail("abctest@test.com");
		user.setGender("mal2");
		user.setName("mike");
		user.setId(0);
		
		ObjectMapper objectMapper= new ObjectMapper();
String jsondata=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
		
	given()
	.header("Content-type","application/json")
	.header("Authorization","Bearer 41e83b1281f1b4006db368d3cff321b17e3d445697fd818f818b834177cf4e6c")
	.body(jsondata)
	.log().all()
		
	.when()
		.post("https://gorest.co.in/public/v2/users")
	.then()	
	.statusCode(201)
	.log().all();	
	
}

	
	@Test
	public  void getUser () throws Exception{
	
		String resp=
		
	given()
		
	.when()
		.get("https://gorest.co.in/public/v2/users/3708305")
		
	.then()
	
		.extract().response().asString();	
		 //System.out.println(resp);
			
		ObjectMapper objectMapper= new ObjectMapper();
		Users user=	objectMapper.readValue(resp,Users.class);   //convert json file to java object desiarialise
		objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
		
		System.out.println(user.getId());
		System.out.println(user.getName());
		System.out.println(user.getGender());
		System.out.println(user.getStatus());
		System.out.println(user.getEmail());
	
	
}

}
