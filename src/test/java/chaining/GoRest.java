package chaining;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resBody.Users;

public class GoRest {

	
	int userid;
	Users user= new Users();
	Faker faker;
	
	String username,useremail,gender= "male",status="active";
	@Test(priority =1)
public void createUser() {
		
		//Users user= new Users();
		faker = new Faker();
		
		username =faker.name().fullName();
		useremail = faker.internet().safeEmailAddress();
		
		user.setName(username);
		user.setEmail(useremail);	
		user.setGender(gender);
		user.setStatus(status);
		
	Response resp=	
		given()
		.header("Content-type","application/json")
		.header("Authorization","Bearer 41e83b1281f1b4006db368d3cff321b17e3d445697fd818f818b834177cf4e6c")
		.body(user)
		
	.when()
	.post("https://gorest.co.in/public/v2/users")
	
	.then()
	.statusCode(201)
	.body("name", equalTo(username))
	.body("email", equalTo(useremail))
	.extract().response();

	JsonPath js = new JsonPath(resp.asString());
userid=	js.get("id");
;	}
	
	@Test(priority =2)
	public void getUser() {
		
		given()
		.header("Content-type","application/json")
		.header("Authorization","Bearer 41e83b1281f1b4006db368d3cff321b17e3d445697fd818f818b834177cf4e6c")
		.pathParam("newuserid", userid)
		
	.when()
	.get("https://gorest.co.in/public/v2/users/{newuserid}")
	
	.then()
	.statusCode(200)
	.body("name", equalTo(username))
	.body("email", equalTo(useremail));
	//.extract().response();
		
	}

	@Test(priority =3)
public void updateUser() {
	
	username= faker.name().username();
	useremail=faker.internet().safeEmailAddress();
	
	user.setEmail(useremail);
	user.setName(username);
	given()
	.header("Content-type","application/json")
	.header("Authorization","Bearer 41e83b1281f1b4006db368d3cff321b17e3d445697fd818f818b834177cf4e6c")
	.pathParam("newuserid", userid)
	.body(user)
	
.when()
.patch("https://gorest.co.in/public/v2/users/{newuserid}")

.then().log().all()
.statusCode(200)
.body("name", equalTo(username))
.body("email", equalTo(useremail));

	}

	@Test(priority =4)
public void deleteUser() {
	given()
	.header("Content-type","application/json")
	.header("Authorization","Bearer 41e83b1281f1b4006db368d3cff321b17e3d445697fd818f818b834177cf4e6c")
	.pathParam("newuserid", userid)
	.body(user)
	
.when()
.delete("https://gorest.co.in/public/v2/users/{newuserid}")

.then()
.statusCode(204);

}
}
