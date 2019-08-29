package practice.program;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;


public class TaskOne {
	
//Get Response
@Test
public void getResponse() {
	Response Resp = RestAssured.get("https://jsonplaceholder.typicode.com/posts/1");
	int code = Resp.getStatusCode();
	System.out.println("Status Code "+code);
	Assert.assertEquals(code,200);
	
}

//Main Method
public static void main(String[] args) {
	TaskOne to = new TaskOne();
	to.getResponse();

	}

}
