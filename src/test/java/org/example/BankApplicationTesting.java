package org.example;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;

public class BankApplicationTesting {
	
	ResponseSpecification obj = null;
	
	@BeforeClass
	public void setResponseSpecification()
	{
		RestAssured.baseURI = Config.serverURL;
		
		obj = RestAssured.expect();
		
		obj.contentType(ContentType.JSON);
		
		obj.statusCode(200);
		
		obj.time(Matchers.lessThan(5000L));
		
		obj.statusLine("HTTP/1.1 200 ");
		
	}
	
	@Test
	public void createCustomerTest()
	{
		JSONObject params = new JSONObject();
		
		params.put("name", "Rajagopal");
		params.put("accountNumber", "123456");
		params.put("ifscCode", "1234567");
		params.put("phoneNumber", "9447135064");
		params.put("address", "ghijkl");
		params.put("branchName", "Thycaud");
		params.put("emailId", "raj@gmail.com");
		params.put("password", "hello");
		
		given().header("Content-Type","Application/json").body(params.toJSONString()).when().post("/customers/createCustomer").then().spec(obj).body("message",Matchers.equalTo("Email id already"));;
		
		
		
	}
	
	@Test
	public void test()
	{

		given().when().get("/customers/testing").then().spec(obj).body("message",Matchers.equalTo("Email id already exists"));
		
		
	}
	
	
	

}
