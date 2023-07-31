package authentication;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class WeatherAPI {
	
	@Test
	public void getWeatherReport() {
		
		given()
		   .param("lat", "35.5")
		   .param("lon", "-78.5")
		   .header("X-RapidAPI-Key","c2365b96f5mshc2242cad4a621f5p1f66efjsna97ee89b6105")
		   .header("X-RapidAPI-Host","weatherbit-v1-mashape.p.rapidapi.com")
		
		.when()
		   .get("https://weatherbit-v1-mashape.p.rapidapi.com/forecast/minutely")
		.then()
		  .log().body();
	}

}
