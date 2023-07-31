package resBody;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

//import com.google.gson.JsonObject;

public class ResponseBodyEx3 {
	
	@Test
	public void getData() {
		//ist root is object
		//inside object is array
		//inside array has object and also one more array hobbies
		
		JSONObject js= new JSONObject(Res.getres3());
		JSONArray arr = js.getJSONArray("employees");
		for(int i =0;i<arr.length();i++) {
			JSONObject oneEmp= arr.getJSONObject(i);
			
			System.out.println(oneEmp.get("empName"));
			System.out.println(oneEmp.get("designature"));
			System.out.println(oneEmp.getInt("salary"));
			System.out.println(oneEmp.get("phone"));
		  //   System.out.println("xxxxxxxxxxxxxxx");
		     
		     JSONArray hobbies= oneEmp.getJSONArray("hobbies");
		     for(int j=0;j<hobbies.length();j++) {
		    	 System.out.println(hobbies.get(j));
		     }
		     System.out.println("yyyyyyyyyyyyyyyy");
		}
		
		
		
		
		
		
	}

}
