package chaining;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resBody.Users;

public class GoRest2 {
	
	int userid;
	Users user= new Users();
	Faker faker;
	String username,useremail,gender= "male",status="active";
	RequestSpecification reqspec;
	ResponseSpecification respspec;
	
	@BeforeClass
	public void initialize()
	{
		reqspec = new RequestSpecBuilder()
				.addHeader("Content-type","application/json")
				.addHeader("Authorization","Bearer 41e83b1281f1b4006db368d3cff321b17e3d445697fd818f818b834177cf4e6c")
				.build();
		
		respspec = new ResponseSpecBuilder()
				.expectResponseTime(Matchers.lessThan(3000L))
				.expectStatusCode(Matchers.oneOf(200,201,204))
				//.expectContentType(ContentType.JSON)
				.expectHeader("Server", equalTo("cloudflare"))
				.build();
	}
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
		//.header("Content-type","application/json")
		//.header("Authorization","Bearer 41e83b1281f1b4006db368d3cff321b17e3d445697fd818f818b834177cf4e6c")
		.spec(reqspec)
		.body(user)
		//.log().all()
		
.when()
	.post("https://gorest.co.in/public/v2/users")
	
.then()
	//.statusCode(201)
.log().body()
	.spec(respspec)
	.body("name", equalTo(username))
	.body("email", equalTo(useremail))
	.extract().response();

	JsonPath js = new JsonPath(resp.asString());
userid=	js.get("id");
;	}
	
	@Test(priority =2)
	public void getUser() {
		
		given()
		//.header("Content-type","application/json")
		.spec(reqspec)
		//.header("Authorization","Bearer 41e83b1281f1b4006db368d3cff321b17e3d445697fd818f818b834177cf4e6c")
		.pathParam("newuserid", userid)
		
	.when()
	.get("https://gorest.co.in/public/v2/users/{newuserid}")
	
	.then()
	//.statusCode(200)
	.spec(respspec)
	.body("name", equalTo(username))
	.body("email", equalTo(useremail))
		.log().body();
	//.extract().response();
		
	}

	@Test(priority =3)
public void updateUser() {
	
	username= faker.name().username();
	useremail=faker.internet().safeEmailAddress();
	
	user.setEmail(useremail);
	user.setName(username);
	given()
	
	.spec(reqspec)
	//.header("Content-type","application/json")
	//.header("Authorization","Bearer 41e83b1281f1b4006db368d3cff321b17e3d445697fd818f818b834177cf4e6c")
	.pathParam("newuserid", userid)
	.body(user)
	
.when()
.patch("https://gorest.co.in/public/v2/users/{newuserid}")

.then().log().all()
//.statusCode(200)
.spec(respspec)
.body("name", equalTo(username))
.body("email", equalTo(useremail))
.log().body();

	}

	@Test(priority =4)
public void deleteUser() {
	given()
	.spec(reqspec)
	//.header("Content-type","application/json")
	//.header("Authorization","Bearer 41e83b1281f1b4006db368d3cff321b17e3d445697fd818f818b834177cf4e6c")
	.pathParam("newuserid", userid)
	.body(user)
	
.when()
.delete("https://gorest.co.in/public/v2/users/{newuserid}")
.then()
//.statusCode(204);
	.spec(respspec)
	.log().body();

}

}
