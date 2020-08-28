package com.blazedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class ConfirmationPage {

private WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		this.driver = driver;
	}
	
	//locator for heading in the page
	@FindBy(xpath="//h1")
	private WebElement header;
	
	@FindBy(xpath="//pre")
	private WebElement jsonResponse;
	
	@FindBy(xpath="//table//tbody//tr//td[text()='Id']//following-sibling::td")
	private WebElement bookingId;
	
	public ConfirmationPage validateTicketPurchase(String expected) {
		String actual = header.getText();
		Assert.assertEquals(actual, expected);
		return this;
	}
	
	public void validateBookingId() {
		String json = jsonResponse.getText();
		JsonPath jp = new JsonPath(json);
		Assert.assertEquals(bookingId.getText(), jp.getString("id"));
	}
}
