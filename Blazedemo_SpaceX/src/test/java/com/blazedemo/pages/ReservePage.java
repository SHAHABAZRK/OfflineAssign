package com.blazedemo.pages;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

public class ReservePage {

private WebDriver driver;
	
	public ReservePage(WebDriver driver) {
		this.driver = driver;
	}
	
	// Get the departure
	@FindBy(xpath="//div[@class='container']//table'")
	private WebElement flightsTable;
	
	//Get header
	@FindBy(xpath="//div[@class=\"container\"]/h3")
	private WebElement header;
	
	
	//Method to Choose flight using Airline
	public PurchasePage chooseSpecificAirlineFlight(String airlineName){
		List<WebElement> rows = driver.findElements(By.xpath("//table//tbody/tr"));
		for(int i=0; i<5; i++) {
			String airline = driver.findElement(By.xpath("//table//tbody/tr["+(i+1)+"]/td[3]")).getText();
			if(airline.equals(airlineName)) {
				driver.findElement(By.xpath("//table//tbody/tr["+(i+1)+"]/td[1]//input[@value='Choose This Flight']")).click();
				break;
			}
			if(i==5) {
				Assert.fail("Airline not present");
			}
		}
		
		return PageFactory.initElements(driver, PurchasePage.class);
	}
	
	public ReservePage validatePlaces(String from, String to) {
		String expected = "Flights from "+from+" to "+to+":";
		String actual = header.getText();
		
		Assert.assertEquals(actual, expected);
		return this;
	}
}
