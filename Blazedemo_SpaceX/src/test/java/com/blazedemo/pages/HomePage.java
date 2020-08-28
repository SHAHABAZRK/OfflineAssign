package com.blazedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

//import com.apttus.base.Page;

public class HomePage{
	
	private WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	// Get the departure
	@FindBy(name="fromPort")
	private WebElement departureCity;
	
	// Get the destination
	@FindBy(name="toPort")
	private WebElement destinationCity;
	
	// Find Flights button
	@FindBy(xpath="//input[@value=\"Find Flights\"]")
	private WebElement findFlightsBtn;
	
	// Method to choose and click on Find Flights
	public ReservePage findFlight(String depCity, String destCity){
		
		//Check if Departure and Destination are equal
		Assert.assertNotEquals(depCity, destCity);
		
		//Select departure city
		departureCity.sendKeys(depCity);
		
		//Select destination city
		destinationCity.sendKeys(destCity);
		
		//Click on Find Flights button
		findFlightsBtn.click();
		
		return PageFactory.initElements(driver, ReservePage.class);
	}
}

