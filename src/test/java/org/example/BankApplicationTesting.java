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
		
//		RestAssured.baseURI = Config.serverURL;

		RestAssured.baseURI = "https://reqres.in/";
		
		obj = RestAssured.expect();
		
		obj.contentType(ContentType.JSON);
		
		obj.statusCode(200);
		
		obj.time(Matchers.lessThan(5000L));
		
		obj.statusLine("HTTP/1.1 200 OK");
		
	}
	
//	@Test
//	public void createCustomerTest()
//	{
//		System.out.println("BEFORE TEST");
//		ExtendReportBase.test = ExtendReportBase.reports.startTest("Create Customer Test","Create Customer with valid data");
//		System.out.println("After TEST");
//		JSONObject params = new JSONObject();
//
//		params.put("name", "ABCDE");
//		params.put("ifscCode", "HDFC000068");
//		params.put("phoneNumber", "123456789");
//		params.put("address", "ghijkl");
//		params.put("branchName", "Thycaud");
//		params.put("emailId", "hello@gmail.com");
//		params.put("password", "hello");
//
//		ExtendReportBase.test.log(LogStatus.INFO, "Created a request payload",params.toJSONString());
//
////		given().header("Content-Type","Application/json").body(params.toJSONString()).when().post("/customers/createCustomer").then().spec(obj).body("message",Matchers.equalTo("Email id already"));;
//
//		String responseMessage = given().header("Content-Type","Application/json").body(params.toJSONString()).when().post("/customers/createCustomer").then().extract().path("message");
//
//		ExtendReportBase.test.log(LogStatus.INFO, "Expected Output","Customer Created Succesfully");
//
//		ExtendReportBase.test.log(LogStatus.INFO, "Actual Output",responseMessage);
//
//		if(responseMessage.equals("Customer Create Successfully"))
//		{
//			ExtendReportBase.test.log(LogStatus.PASS, "Actual Output",responseMessage);
//		}
//		else
//		{
//			ExtendReportBase.test.log(LogStatus.FAIL, "Actual Output",responseMessage);
//		}
//
//
//	}

//	@Test
//	public void loginTest() {
//
//		System.out.println("BEFORE TEST");
//		ExtendReportBase.test = ExtendReportBase.reports.startTest("Customer Login Test","Login customer with valid data");
//		System.out.println("AFTER TEST");
//		JSONObject params = new JSONObject();
//
//		params.put("emailId", "anuradha25@laxmichitfund.com");
//		params.put("password", "anuradha@123");
//
//		ExtendReportBase.test.log(LogStatus.INFO, "Created a payload", params.toJSONString());
//
//		given().header("Content-Type","Application/json").body(params.toJSONString()).when().
//				post("/login").then().spec(obj).body("message", Matchers.equalTo("Login Successfull"));
//
//		String responseMessage = given().header("Content-Type","Application/json").body(params.toJSONString()).
//				when().post("/login").then().extract().path("message");
//
//		ExtendReportBase.test.log(LogStatus.INFO, "Expected Output","Login Successfull");
//
//		//ExtendReportBase.test.log(LogStatus.INFO, "Actual Output", responseMessage);
//
//		if(responseMessage.equals("Login Successfull"))
//		{
//			ExtendReportBase.test.log(LogStatus.PASS, "Actual Output", responseMessage);
//		}
//		else
//		{
//			ExtendReportBase.test.log(LogStatus.FAIL, "Actual Output", responseMessage);
//		}
//
//	}

	@Test
	public void test1() {
		System.out.println("BEFORE TEST 1");
		ExtendReportBase.test = ExtendReportBase.reports.startTest("List Users", "Listing Users data");
		System.out.println("AFTER TEST 1");

		ExtendReportBase.test.log(LogStatus.INFO, "Created a payload");

		given().when().get("api/users?page=2").then().spec(obj).body("total_pages", Matchers.equalTo(2));

		//String responseMessage = given().when().get("api/users?page=2").then().assertThat().body();

		ExtendReportBase.test.log(LogStatus.INFO, "Expected Output", "2");

		//ExtendReportBase.test.log(LogStatus.INFO, "Actual Output", responseMessage);
	}

	@Test
	public void test2() {
		System.out.println("BEFORE TEST 2");
		ExtendReportBase.test = ExtendReportBase.reports.startTest("Test API 2");
		System.out.println("AFTER TEST 2");

		ExtendReportBase.test.log(LogStatus.INFO, "Created a payload");

		given().when().get("api/unknown/2").then().spec(obj).body("data[\"pantone_value\"]", Matchers.equalTo("17-2031"));

		//String responseMessage = given().when().get("api/users?page=2").then().assertThat().body();

		ExtendReportBase.test.log(LogStatus.INFO, "Expected Output", "17-2031");
	}

	@Test
	public void test3() {
		System.out.println("BEFORE TEST 3");
		ExtendReportBase.test = ExtendReportBase.reports.startTest("Test API 3");
		System.out.println("AFTER TEST 3");

		JSONObject params = new JSONObject();

		params.put("email", "eve.holt@reqres.in");
		params.put("password", "pistol");

		ExtendReportBase.test.log(LogStatus.INFO, "Created a payload");

		given().header("Content-Type","Application/json").body(params.toJSONString()).when().
				post("/api/register").then().body("id", Matchers.equalTo(4));

		ExtendReportBase.test.log(LogStatus.INFO, "Expected Output", "4");

	}

	@Test
	public void test4() {
		System.out.println("BEFORE TEST 4");
		ExtendReportBase.test = ExtendReportBase.reports.startTest("Test API 4");
		System.out.println("AFTER TEST 4");

		JSONObject params = new JSONObject();

		params.put("email", "peter@klaven");

		ExtendReportBase.test.log(LogStatus.INFO, "Created a payload");

		given().header("Content-Type","Application/json").body(params.toJSONString()).when().
				post("/api/login").then().body("error", Matchers.equalTo("Missing password"));

		ExtendReportBase.test.log(LogStatus.INFO, "Expected output", "Missing password");

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
