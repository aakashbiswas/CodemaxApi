package CodeMaxApi.RestAssured;

import static io.restassured.RestAssured.given;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import CodeMaxApi.RestAssured.ExtentNG;
import utility.Resuable_function;
import utility.Xls_Reader;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ValidateGetRequest {

	 ExtentReports re=ExtentNG.beforeTest();
	   ExtentTest we=null;
	Response res=null;
	String resource="";
	int i=0,j=0;
	//Xls_Reader reader=null;
	@BeforeClass
	public void class_start()
	{
		String w="";
		 String s[]=Resuable_function.test_url();
		 String baseuri="http://"+s[0];
		 resource="/"+s[s.length-1];
		 for(int i=1;i<s.length-1;i++)
		 {
			w+="/"+s[i];
		 }
		RestAssured.baseURI=baseuri;
		RestAssured.basePath=w;
		
	}
	@BeforeMethod
	public void method_start()
	{
		res=given().when().get(resource);
		we=re.createTest("test start");	
		
	}
	@Test(priority=1)
	public void status_code_verify()
	{
		we.log(Status.INFO,"status_code_verify started");
		int code = res.getStatusCode();
		String actual_result=Resuable_function.change(code);
		String expected_result=Resuable_function.status_code();
		String expected_val=Resuable_function.value(expected_result);
		if(expected_val.equalsIgnoreCase(actual_result))
		{
			we.log(Status.PASS, actual_result+"is the result");
		}
		else
		{
			i=1;
			we.log(Status.FAIL,actual_result+"is the result");
		}
		
	}
	
	@Test(priority=2)
	public void status_message_verify()
	{
		we.log(Status.INFO,"status_message_verify started");
		String val=res.body().asString();
		JsonPath js=new JsonPath(val);
		String actual_status=js.getString("status");
		String expected_status=Resuable_function.status_message();
		if(expected_status.equalsIgnoreCase(actual_status))
		{
			we.log(Status.PASS,actual_status+"is the status_message");
		}
		else
		{
			we.log(Status.FAIL, actual_status+"is the status_message");
			j=1;
		}

	}
	@Test(priority=3,dependsOnMethods = {"status_code_verify","status_message_verify"})
	public void print_message()
	{
		we.log(Status.INFO, "To print the value of the response");
		String message;
		String data=res.body().prettyPrint();
		we.log(Status.INFO, data);
		if(i==1 || j==1)
		{
			message="Fail";
		}
		else
		{
			message="Pass";
		}
		Resuable_function.test_message(message);
	}
	@AfterMethod
	public void stop()
	{
		res=null;
	}
	@AfterClass
	public void class_stop()
	{
		re.flush();
	}
}