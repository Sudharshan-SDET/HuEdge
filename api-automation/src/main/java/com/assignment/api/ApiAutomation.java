package com.assignment.api;

import com.assignment.api.pojos.CreateUserRequest;
import com.assignment.api.pojos.CreateUserResponse;
import com.assignment.api.pojos.RegisterResponse;
import com.assignment.api.pojos.User;
import com.assignment.api.utils.TestDataHelper;
import com.assignment.api.utils.LoggerUtil;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import static io.restassured.RestAssured.given;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ApiAutomation {

    private RequestSpecification requestSpec;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://reqres.in";
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(RestAssured.baseURI)
                .setContentType("application/json")
                .build();

        // Set up ExtentReports
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extentReports.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
       
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        test = extent.createTest(method.getName());
        LoggerUtil.logInfo("Starting test: " + method.getName());
    }

    @Test(priority = 1)
    public void listUsers() {
        Response response = given()
                .spec(requestSpec)
                .get("/api/users?page=2");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.jsonPath().get("data"));
        LoggerUtil.logInfo("List users test passed.");
        test.pass("List users test passed.");
        test.info("Status Code..."+response.getStatusCode());
        test.info("Body..."+response.getBody().asString());


    }

    @Test(priority = 2)
    public void singleUser() {
        Response response = given()
                .spec(requestSpec)
                .get("/api/users/2");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.jsonPath().get("data"));
        LoggerUtil.logInfo("Single user test passed.");
        test.pass("Single user test passed.");
    }

    @Test(priority = 3)
    public void singleUserNotFound() {
        Response response = given()
                .spec(requestSpec)
                .get("/api/users/23");

        Assert.assertEquals(response.getStatusCode(), 404);
        LoggerUtil.logInfo("Single user not found test passed.");
        test.pass("Single user not found test passed.");
    }

    @Test(priority = 4)
    public void listResources() {
        Response response = given()
                .spec(requestSpec)
                .get("/api/unknown");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.jsonPath().get("data"));
        LoggerUtil.logInfo("List resources test passed.");
        test.pass("List resources test passed.");
    }

    @Test(priority = 5)
    public void singleResource() {
        Response response = given().log().all()
                .spec(requestSpec).
                when().get("/api/unknown/2").then().log().all().extract().response();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.jsonPath().get("data"));
        LoggerUtil.logInfo("Single resource test passed.");
        test.pass("Single resource test passed.");
    }

    @Test(priority = 6)
    public void createUser() {
        CreateUserRequest requestBody = TestDataHelper.getCreateUserRequest();

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .post("/api/users");

        Assert.assertEquals(response.getStatusCode(), 201);
        CreateUserResponse responseBody = response.as(CreateUserResponse.class);
        Assert.assertNotNull(responseBody.getId());
        LoggerUtil.logInfo("Create user test passed.");
        test.pass("Create user test passed.");
    }

    @Test(priority = 7)
    public void registerSuccessful() {
        Map<String, String> requestBody = TestDataHelper.getRegisterRequest();

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .post("/api/register");

        Assert.assertEquals(response.getStatusCode(), 200);
        RegisterResponse registerResponse = response.as(RegisterResponse.class);
        Assert.assertNotNull(registerResponse.getToken());
        LoggerUtil.logInfo("Registration successful test passed.");
        test.pass("Registration successful test passed.");
    }

    @Test(priority = 8)
    public void registerUnsuccessful() {
        Map<String, String> requestBody = TestDataHelper.getFailedRegisterRequest();

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .post("/api/register");

        Assert.assertEquals(response.getStatusCode(), 400);
        Assert.assertNotNull(response.jsonPath().get("error"));
        LoggerUtil.logInfo("Failed registration test passed.");
        test.pass("Failed registration test passed.");
    }

    @Test(priority = 9)
    public void loginSuccessful() {
        Map<String, String> requestBody = new HashMap<String, String>();
        requestBody.put("email", "eve.holt@reqres.in");
        requestBody.put("password", "cityslicka");

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .post("/api/login");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.jsonPath().get("token"));
        LoggerUtil.logInfo("Login successful test passed.");
        test.pass("Login successful test passed.");
    }

    @Test(priority = 10)
    public void loginUnsuccessful() {
        Map<String, String> requestBody = new HashMap<String, String>();
        requestBody.put("email", "peter@klaven");

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .post("/api/login");

        Assert.assertEquals(response.getStatusCode(), 400);
        Assert.assertNotNull(response.jsonPath().get("error"));
        LoggerUtil.logInfo("Failed login test passed.");
        test.pass("Failed login test passed.");
    }

    @Test(priority = 11)
    public void updateUserPut() {
        Map<String, String> requestBody = new HashMap<String, String>();
        requestBody.put("name", "Morpheus");
        requestBody.put("job", "Zion Resident");

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .put("/api/users/2");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().get("job"), "Zion Resident");
        LoggerUtil.logInfo("Update user PUT test passed.");
        test.pass("Update user PUT test passed.");
    }

    @Test(priority = 12)
    public void updateUserPatch() {
        Map<String, String> requestBody = new HashMap<String, String>();
        requestBody.put("name", "Morpheus");
        requestBody.put("job", "Zion Leader");

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .patch("/api/users/2");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().get("job"), "Zion Leader");
        LoggerUtil.logInfo("Update user PATCH test passed.");
        test.pass("Update user PATCH test passed.");
    }

    @Test(priority = 13)
    public void deleteUser() {
        Response response = given()
                .spec(requestSpec)
                .delete("/api/users/2");

        Assert.assertEquals(response.getStatusCode(), 204);
        LoggerUtil.logInfo("Delete user test passed.");
        test.pass("Delete user test passed.");
    }

    @AfterMethod
    public void afterMethod() {
        extent.flush();
    }

    @AfterClass
    public void teardown() {
        LoggerUtil.logInfo("All tests completed.");
    }
}






