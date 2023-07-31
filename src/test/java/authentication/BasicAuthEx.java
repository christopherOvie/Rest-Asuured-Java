package authentication;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import resBody.Res;


public class BasicAuthEx {
	
	@Test
	public void basic() {
		
	String resp=
	given()
		.auth()
		.basic("postman", "password")
		
	.when()
		.get("https://postman-echo.com/basic-auth")
		
	.then()
		.log().body().extract().asString();	
		JsonPath path= new JsonPath(resp);
		System.out.println(path.get("authenticated"));
		//Assert.assertEquals(path.getBoolean("authenticated"), "true");	
		Assert.assertTrue(path.getBoolean("authenticated"));
		
	}
	
	//@Test
	public void digest() {
		
		String resp=
	given()
		.auth()
		.digest("postman", "password")
		
	.when()
		.get("https://postman-echo.com/basic-auth")
		
	.then()
		.log().body().extract().response().asString();
		
		
		JsonPath path= new JsonPath(resp);
		//System.out.println(path);
		//Assert.assertEquals(path.getString("authenticated"), "true");	
		Assert.assertTrue(path.getBoolean("authenticated"));
		
	}
	//@Test
		public void oauth2_ex()
		{		
			given()
			    .auth().oauth2("7244b784879a850c10b83709ac14a0e09749c78c")
			    .multiPart("image",new File("C:\\Wallpaper\\Samsung Wallpaper_01.jpg"))
			    .multiPart("title","eclipseprogram")
			.when()
			    .post("https://api.imgur.com/3/upload")
			.then()		   
			   .log().body();			
		}

}
