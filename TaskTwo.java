package practice.program;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TaskTwo {
	
/* Sending POST Request*/
@Test
public void getRequest()
{
	RequestSpecification request = RestAssured.given();
	request.header("Content-Type", "application/json"); //char set=UTF-8
	
	JSONObject json = new JSONObject();
	try {
		json.put("title", "foo");
		json.put("body","bar");
		json.put("userId", "1");
	} catch (JSONException e) {
		e.printStackTrace();
	}
	RequestSpecification req = request.body(json.toString());
}

//Verify Status Code
@Test
public void verifyStatus() {
	Response Resp = RestAssured.get("https://jsonplaceholder.typicode.com/posts");
	int code = Resp.getStatusCode();
	System.out.println("Status Code for "+code);
	Assert.assertEquals(code,200);
}

//Verify Null Parameters
@Test
public void verifyNullParameter() {
	Response Resp = RestAssured.get("https://jsonplaceholder.typicode.com/posts");
	String code = Resp.asString();
	System.out.println("Status Code for "+code);
	if (code!=null) {
		System.out.println("Test Case Passed");
	}
	else {
		System.out.println("Null Value");
	}
}
}
