package xmlresp;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.testng.Assert;
import org.testng.annotations.*;

import io.restassured.response.Response;
public class AdequateShop {
	
	//@Test
	public void getTravelAndValidate1() {
		
		given()
		
		.queryParam("page", 1)
		.when()
		.get("http://restapi.adequateshop.com/api/Traveler")
		
		.then()
		.log().body()
		.body("TravelerinformationResponse.page", equalTo("1"))
		.body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Developer"));
	
		
	}
	
	@Test
	public void getTravelAndValidate2() {
		
		
		Response resp=
		given()
		
		.queryParam("page", 1)
		.when()
		.get("http://restapi.adequateshop.com/api/Traveler")
		
		.then()
	   .extract().response();
		
		Assert.assertEquals(resp.statusCode(), 200);
		Assert.assertEquals(resp.xmlPath().get("TravelerinformationResponse.page"), "1");
		Assert.assertEquals(resp.xmlPath().get("ravelerinformationResponse.travelers.Travelerinformation[0].name"), "Developer");
//		.body("TravelerinformationResponse.page", equalTo("1"))
//		.body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Developer"));
	
	JSONObject js=	XML.toJSONObject(resp.asString());
JSONArray arr=	js.getJSONObject("TravelerinformationResponse").getJSONObject("travelers").getJSONArray("Travelerinformation");
		for(int i=0;i<arr.length();i++) {
			JSONObject oneObj=	arr.getJSONObject(i);
			System.out.println(oneObj.get("name").toString());
			System.out.println(oneObj.get("email").toString());
			System.out.println(oneObj.get("adderes").toString());
			System.out.println("xxxxxxxxxxxx");
		}
	}

}
