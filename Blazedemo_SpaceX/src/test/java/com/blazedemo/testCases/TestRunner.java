package com.blazedemo.testCases;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.blazedemo.base.BaseTest;
import com.blazedemo.pages.ConfirmationPage;
import com.blazedemo.pages.HomePage;
import com.blazedemo.pages.PurchasePage;
import com.blazedemo.pages.ReservePage;

public class TestRunner extends BaseTest{	
	HomePage lp;
	ReservePage rp;
	PurchasePage pp;
	ConfirmationPage cp;
	String departureCity;
	String destinationCity;
	
	@BeforeMethod
	//Launch App
	public void launchApplication() {
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("--disable-extensions");
		options.addArguments("--disable-infobars");

		driver = new ChromeDriver(options);
		driver.get(repo.getProperty("testsiteurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		departureCity = getTestData("departure");
		destinationCity = getTestData("destination");
	}
	
	@Test(priority=1)
	//Scenario to validate correct departure and destination in the Reserve page
	public void ValidatePlacesEntered(){
		lp = PageFactory.initElements(driver, HomePage.class);
		rp = lp.findFlight(departureCity, destinationCity);
		rp.validatePlaces(departureCity, destinationCity);
		Reporter.log("Selected departure as "+departureCity+" and destination as "+destinationCity);
	}
	
	@Test(priority=2)
	//Scenario to Validate the booking ID after successful booking
	public void ValidateBookingId(){
	lp = PageFactory.initElements(driver, HomePage.class);
	rp = lp.findFlight(departureCity, destinationCity);
	pp = rp.chooseSpecificAirlineFlight(getTestData("airline"));
	cp = pp.fillPurchaseForm(getTestData("name"), getTestData("address"), getTestData("city"),
			getTestData("state"), getTestData("zipcode"), getTestData("cardtype"), getTestData("ccn"), 
			getTestData("month"), getTestData("year"), getTestData("noc"));
	cp.validateTicketPurchase(getTestData("confirmationMessage"));
	cp.validateBookingId();
	Reporter.log("Id validation is scuuceefull");
	}
	
	@AfterMethod
	//Close browser
	public void closeBrowser() {
		driver.close();
	}
}
