package resBody;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class ResponsBodyEx2 {
	@Test
public void getData() {
	//JsonPath jpath = new JsonPath(Res.getres1());
	//System.out.println(jpath.getString("name"));
	//System.out.println(jpath.getInt("salary"));
		JSONObject js = new JSONObject(Res.getres2());
JSONArray arr=	js.getJSONArray("employees");

for(int j=0;j<arr.length();j++) {
	//System.out.println(arr.getJSONObject(j).get(i));
JSONObject singleObject =	arr.getJSONObject(j);
//System.out.println(singleObject);

JSONArray objNames=singleObject.names();
//System.out.println(objNames);
  for(int k =0;k<objNames.length();k++) {
 // System.out.println(singleObject.get(objNames.getString(k)));
  System.out.println(objNames.get(k)+ "     "+singleObject.get(objNames.getString(k)));
	
  }
//  System.out.println("=======================");
//  System.out.println(singleObject.get("empName"));
//  System.out.println(singleObject.get("phone"));
//  System.out.println(singleObject.get("designature"));
//  System.out.println(singleObject.get("salary"));
//  
  System.out.println("=======================");
}
	
}

}
