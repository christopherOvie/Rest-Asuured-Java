package reqres;

import static io.restassured.RestAssured.given;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.*;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import org.hamcrest.Matchers;


public class ResponseValidations {
	
	//@Test
	public void validateResponse() {
		
					given()
					.header("Content-type","application/json")
					.pathParams("p1","api","p2","users")//for chain of request with many parameter
					.queryParam("page", 2)
					.queryParam("id", 8)
				
					
					.when()
					.get("https://reqres.in/{p1}/{p2}")//no need to specify query parameters
					
					.then()
					.log().all()
					.time(Matchers.lessThan(3000L))
					
					.statusCode(Matchers.oneOf(200,201))
					.body("data.first_name", equalTo("Lindsay"))
					.body("data.last_name", equalTo("Ferguson"))
					.body("data.email", equalTo("lindsay.ferguson@reqres.in"))
					
					.body("data.first_name", Matchers.matchesPattern("[a-zA-Z]+"));//val first_name is a;lphabeth
					
					//.body("data.id", Matchers.matchesPattern("\\d*"));//val 
					
					
					//check body contains string	
				}
	//@Test
	public void validateCookies() {
		
		
		
	when()
		.get("https://www.google.com")//no need to specify query parameters
		
	.then()
	.cookie("CONSENT",not("PENDING+425"))
	//.cookie("CONSENT",Matchers.containsString("CONSENT="))
		.log().all();
		}
		
	//@Test
	public void extractResponse() {
		
		
	Response res=	
	when()
		.get("https://www.google.com")//no need to specify query parameters
		
	.then()
	.extract().response();
	
 Map<String, String>allCookie=	res.cookies();

 for(String k : allCookie.keySet()) {
	 System.out.println(k+ "    :"+res.getCookie(k));
 }
	
		}
	
@Test
	public void getHeader() {
	
	Response res=
		given()
		.header("Content-type","application/json")
		.pathParams("p1","api","p2","users")//for chain of request with many parameter
		.queryParam("page", 2)
		.queryParam("id", 8)
	
		
		.when()
		.get("https://reqres.in/{p1}/{p2}")//no need to specify query parameters
		
		.then().log().all().extract().response();
	
	Headers allHeaders  = res.headers();
	for(Header h:allHeaders){
		System.out.println(h.getName()+ ""+h.getValue());
	}
	
		
	}
	
	}


