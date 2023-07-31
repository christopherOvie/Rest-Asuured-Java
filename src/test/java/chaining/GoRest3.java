package chaining;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.github.javafaker.Faker;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resBody.Users;

public class GoRest3 {

	int userid;
	Users user= new Users();
	Faker faker;
	
	String username,useremail,gender= "male",status="active";
		
	public static ExtentSparkReporter spark;
	public static ExtentReports report;
	public static ExtentTest test;
	
	@BeforeSuite 
	public void initialize() {
		spark = new ExtentSparkReporter(new File("./Reports/goRest.html"));
		report = new ExtentReports();
		report.attachReporter(spark);
		spark.config().setDocumentTitle("GoRestAPITesting");
		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("GoRest3");
		
		report.setSystemInfo("Environment", "TestEnv");
		report.setSystemInfo("TesterName", "Tester1");
	}
	@Test(priority =1)
public void createUser() {
		
		//Users user= new Users();
		faker = new Faker();
		username =faker.name().fullName();
		useremail = faker.internet().safeEmailAddress();
		
		user.setName(username);
		user.setEmail(useremail);	
		user.setGender(gender);
		user.setStatus(status);
		
	Response resp=	
		given()
		.header("Content-type","application/json")
		.header("Authorization","Bearer 41e83b1281f1b4006db368d3cff321b17e3d445697fd818f818b834177cf4e6c")
		.body(user)
		
	.when()
	.post("https://gorest.co.in/public/v2/users")
	
	.then()

	.extract().response();
	
	JsonPath js = new JsonPath(resp.asString());
userid=	js.get("id");
	test=report.createTest("CreateUserAPI");
	if(resp.getStatusCode()==201) {
		test.log(Status.PASS,"response status code is 201");
	}else 
		test.log(Status.FAIL, "response status code is not 201");
	
if(userid>0) {
	test.log(Status.PASS, "new userid generated for create user");
}
else 
	test.log(Status.FAIL, "new userid is not generated for create user");

if(js.getString("name").matches(username)) {
	test.log(Status.PASS, "username created is correct");
}
	else 
		test.log(Status.FAIL, "username created is not  correct");
	}
	
	@Test(priority =2)
	public void getUser() {
	Response resp=	
	given()
		.header("Content-type","application/json")
		.header("Authorization","Bearer 41e83b1281f1b4006db368d3cff321b17e3d445697fd818f818b834177cf4e6c")
		.pathParam("newuserid", userid)
		
	.when()
	    .get("https://gorest.co.in/public/v2/users/{newuserid}")
	
	.then().extract().response();
	     test=report.createTest("GetUserAPI");
		if(resp.getStatusCode()==200) {
			test.log(Status.PASS,"response status code is 200");
		}else 
			test.log(Status.FAIL, "response status code is not 200");
		
	if(userid>0) {
		test.log(Status.PASS, "new userid generated for create user");
	}
	else 
		test.log(Status.FAIL, "new userid is not generated for create user");
		
	}

	@Test(priority =3)
public void updateUser() {
	
	username= faker.name().username();
	useremail=faker.internet().safeEmailAddress();
	user.setEmail(useremail);
	user.setName(username);
	
	Response resp=
	given()
	.header("Content-type","application/json")
	.header("Authorization","Bearer 41e83b1281f1b4006db368d3cff321b17e3d445697fd818f818b834177cf4e6c")
	.pathParam("newuserid", userid)
	.body(user)
	.when()
     .patch("https://gorest.co.in/public/v2/users/{newuserid}")

.then().extract().response();
	test=report.createTest("UpdateUserAPI");
	if(resp.getStatusCode()==200) {
		test.log(Status.PASS,"response status code is 200");
	}else 
		test.log(Status.FAIL, "response status code is not 200");
	
if(userid>0) {
	test.log(Status.PASS, "new userid updated for create user");
}
else 
	test.log(Status.FAIL, "new userid is not updated for create user");
	

	}

	@Test(priority =4)
public void deleteUser() {
		Response resp=
	given()
	.header("Content-type","application/json")
	.header("Authorization","Bearer 41e83b1281f1b4006db368d3cff321b17e3d445697fd818f818b834177cf4e6c")
	.pathParam("newuserid", userid)
	.body(user)
	
.when()
.delete("https://gorest.co.in/public/v2/users/{newuserid}")

.then().extract().response();
		test=report.createTest("DeleteUserAPI");
		if(resp.getStatusCode()==204) {
			test.log(Status.PASS,"response status code is 204");
		}else 
			test.log(Status.FAIL, "response status code is not 204");
		
	if(userid>0) {
		test.log(Status.PASS, "new userid deleted for create user");
	}
	else 
		test.log(Status.FAIL, "new userid is not deleted for create user");
		

}
	@AfterSuite
	public void saveReport() {
		report.flush();
	}
}
