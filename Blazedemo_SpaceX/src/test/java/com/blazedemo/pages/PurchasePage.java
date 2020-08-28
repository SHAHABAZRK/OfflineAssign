package com.blazedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PurchasePage {

private WebDriver driver;
	
	public PurchasePage(WebDriver driver) {
		this.driver = driver;
	}
	
		//locator for name textbox
		@FindBy(id="inputName")
		private WebElement name;
		
		//locator for address textbox
		@FindBy(id="address")
		private WebElement address;
		
		//locator for city textbox
		@FindBy(id="city")
		private WebElement city;
		
		//locator for state textbox
		@FindBy(id="state")
		private WebElement state;
		
		//locator for Zip Code textbox
		@FindBy(id="zipCode")
		private WebElement zipCode;
		
		//locator for Cart type dropdown
		@FindBy(id="cardType")
		private WebElement cardType;
		
		//locator for Credit card number textbox
		@FindBy(id="creditCardNumber")
		private WebElement creditCardNumber;
		
		//locator for Credit card month textbox
		@FindBy(id="creditCardMonth")
		private WebElement month;
		
		//locator for Credit card year textbox
		@FindBy(id="creditCardYear")
		private WebElement year;
		
		//locator for Name on Card textbox
		@FindBy(id="nameOnCard")
		private WebElement nameOnCard;
		
		//locator for Purchase flight button
		@FindBy(xpath="//input[@value='Purchase Flight']")
		private WebElement purchaseFlight;
		
		
		public ConfirmationPage fillPurchaseForm(String fName, String fAddress, String fCity, String fState, String fZipcode, String fCardtype, String fCCN, 
				String fMonth, String fYear, String fNOC) {
			name.sendKeys(fName);
			address.sendKeys(fAddress);
			city.sendKeys(fCity);
			state.sendKeys(fState);
			zipCode.sendKeys(fZipcode);
			cardType.sendKeys(fCardtype);
			creditCardNumber.sendKeys(fCCN);
			month.sendKeys(fMonth);
			year.sendKeys(fYear);
			nameOnCard.sendKeys(fNOC);
			purchaseFlight.click();
			
			return PageFactory.initElements(driver, ConfirmationPage.class);
		}
}
