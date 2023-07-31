package resBody;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class ResponseBodyEx4 {
	
	@Test
	public void getData() {
		
		JSONArray arr = new JSONArray(Res.getres4());
		System.out.println(arr.length());
		
		for(int i=0;i<arr.length();i++) {
			
			JSONObject oneemp= arr.getJSONObject(i);
			System.out.println(oneemp.get("empName"));
			System.out.println(oneemp.get("designature"));
			System.out.println(oneemp.getInt("salary"));
			System.out.println(oneemp.get("phone"));
		//	System.out.println("xxxxxxxxxxxxxxx");
			
		JSONArray hobbies =	oneemp.getJSONArray("hobbies");
		
		for(int j=0;j<hobbies.length();j++) {
			System.out.println(hobbies.get(j));
		}
			System.out.println("yyyyyyyyyyyyyyyyyyyy");
		}
	}

}
