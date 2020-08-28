package com.spacex.apiTesting;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.blazedemo.base.BaseTest;

import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class SpaceXAPITesting extends BaseTest{
	
	@BeforeMethod
	public void BaseUri() {
		RestAssured.baseURI = getTestData("base.uri");
	}

	@Test(priority=1)
	public void getResponse() {

		given().header("Content-Type", "application/json")
		.when().get(getTestData("get.endpoint"))
		.then().log().all().assertThat().statusCode(200);
	}
	
	@Test(priority=2)
	public void validateResponseHeader() {
		
		Response response = given().header("Content-Type", "application/json")
		.when().get(getTestData("get.endpoint"))
		.then().log().all().assertThat().statusCode(200).extract().response();
		
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, getTestData("response.contentType"));
		
		String server = response.header("Server");
		Assert.assertEquals(server, getTestData("response.server"));
		
		String contentEncoding = response.header("Content-Encoding");
		Assert.assertEquals(contentEncoding, getTestData("response.contentEncoding"));
	}
	
	@Test(priority=3)
	public void verifyInvalidEndPoint() {

		String response = given().header("Content-Type", "application/json")
		.when().get(getTestData("get.invalid.endpoint"))
		.then().log().all().assertThat().statusCode(404).extract().asString();
		
		Assert.assertEquals(response, getTestData("error.response"));
	}
}
