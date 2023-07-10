package reqres;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.response.Response;

public class Validations_different {
	
	//@Test
	public void validateResponse_1() {
		
					given()
					.header("Content-type","application/json")
					.pathParams("p1","api","p2","users")//for chain of request with many parameter
					.queryParam("page", 2)
					.queryParam("id", 8)
				
					
					.when()
					.get("https://reqres.in/{p1}/{p2}")//no need to specify query parameters
					
					.then()
					.statusCode(200)
					.time(Matchers.lessThan(3000L))
					.header("Server", "cloudflare")
					.body("data.first_name", Matchers.matchesPattern("[a-zA-Z]+"));
					//.cookie(null)
					
				}
	
	@Test
	public void validateResponse_2() {
		
		SoftAssert asrt= new SoftAssert();
		
		Response res=
					given()
					.header("Content-type","application/json")
					.pathParams("p1","api","p2","users")//for chain of request with many parameter
					.queryParam("page", 2)
					.queryParam("id", 8)
				
					
					.when()
					.get("https://reqres.in/{p1}/{p2}")//no need to specify query parameters
					
					.then()
					.extract().response();
		
	int code =	res.getStatusCode();
	String c=  res.getCookie("");
	
	//JsonPath js= new JsonPath();
		//testng has asseet class which compare expoected vs actual
			//hard asssert		
//		Assert.assertEquals(200, res.getStatusCode());
//	Assert.assertEquals("cloudflare", res.getHeader("Server"));
		//soft assert
		asrt.assertEquals(res.getStatusCode(), 201);
		asrt.assertEquals(res.getHeader("Server"), "cloudflare");
		
		System.out.println("hello");
	
		//even if asert fails  soft execurtioncontinues
		asrt.assertAll();
					
				}
	
	

}
