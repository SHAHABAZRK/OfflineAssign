package com.blazedemo.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

	protected WebDriver driver;
	public Properties repo = new Properties();
	public FileInputStream fis;
	
	@BeforeClass
	public void setUp() {

		if (driver == null) {
			
			//Load property file
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\properties\\repo.properties");
				repo.load(fis);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String getTestData(String prop) {
		return repo.getProperty(prop);
	}
}
