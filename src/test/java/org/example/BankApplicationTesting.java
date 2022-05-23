package org.example;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import org.example.ExtendReportBase;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class BankApplicationTesting {
	
	ResponseSpecification obj = null;
	
	@BeforeClass
	public void setResponseSpecification()
	{
		
		ExtendReportBase.createReport();
		
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
		System.out.println("BEFORE TEST");
		ExtendReportBase.test = ExtendReportBase.reports.startTest("Create Customer Test","Create Customer with valid data");
		System.out.println("After TEST");
		JSONObject params = new JSONObject();
		
		params.put("name", "ABCDE");
		params.put("ifscCode", "HDFC000068");
		params.put("phoneNumber", "123456789");
		params.put("address", "ghijkl");
		params.put("branchName", "Thycaud");
		params.put("emailId", "hello@gmail.com");
		params.put("password", "hello");
		
		ExtendReportBase.test.log(LogStatus.INFO, "Created a request payload",params.toJSONString());
		
//		given().header("Content-Type","Application/json").body(params.toJSONString()).when().post("/customers/createCustomer").then().spec(obj).body("message",Matchers.equalTo("Email id already"));;
		
		String responseMessage = given().header("Content-Type","Application/json").body(params.toJSONString()).when().post("/customers/createCustomer").then().extract().path("message");
		
		ExtendReportBase.test.log(LogStatus.INFO, "Expected Output","Customer Created Succesfully");
		
		ExtendReportBase.test.log(LogStatus.INFO, "Actual Output",responseMessage);
		
		if(responseMessage.equals("Customer Create Successfully"))
		{
			ExtendReportBase.test.log(LogStatus.PASS, "Actual Output",responseMessage);
		}
		else 
		{
			ExtendReportBase.test.log(LogStatus.FAIL, "Actual Output",responseMessage);
		}
	
		
	}
	
	@AfterClass
	public void closeReport()
	{
		ExtendReportBase.reports.flush();
	}
	
	
//	@Test
//	public void test()
//	{
//
//		given().when().get("/customers/testing").then().spec(obj).body("message",Matchers.equalTo("Email id already exists"));
//		
//		
//	}
	
	
	

}
