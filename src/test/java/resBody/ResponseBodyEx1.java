package resBody;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import io.restassured.path.json.JsonPath;

public class ResponseBodyEx1 {
	
	
	@Test
	public void validateBody() {
		
//JsonPath jpath = new JsonPath(Res.getres1());
//System.out.println(jpath.getString("name"));
//System.out.println(jpath.getInt("salary"));
		
		JSONObject json = new JSONObject(Res.getres1());
		System.out.println(json.get("name"));
		System.out.println(json.getInt("salary"));
		//System.out.println(json.get("design"));
		Assert.assertEquals(json.get("name"), "duggar");
		Assert.assertEquals(json.getInt("salary"), 5000);
		Assert.assertEquals(json.get("design"), "manager");
		

		
	}

}
