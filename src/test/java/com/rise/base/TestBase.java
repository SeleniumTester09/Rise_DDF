package com.rise.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;




public class TestBase {
	/*
	 * WebDriver - Done
	 * Properties - Done
	 * Logs - log4j - log4j Property file
	 * Extent Reports
	 * DB 
	 * Excel
	 * Mail	
	 * ReportNG, Extent Reports
	 * Jenkins
	 *
	*/
	
	public static WebDriver driver;
	
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static  Logger log = Logger.getLogger("devpinoyLogger");
	
	
	@BeforeSuite
	public void setup()
	{
		
		log.debug("Test Exectution Starting !!!");
		
		if(driver==null) {
			
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			config.load(fis);
			log.debug("config file loaded !!!");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			OR.load(fis);
			log.debug("OR file loaded !!!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		if(config.getProperty("browser").equals("firefox")){
			
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\geckodriver.exe");
			driver = new FirefoxDriver();
			log.debug("Firefox Browser Launched !!!");
			}
		else if(config.getProperty("browser").equals("chrome"))  {
		
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
			driver = new ChromeDriver();
			log.debug("Chrome Browser Launched !!!");
		}
		
		else if(config.getProperty("browser").equals("ie")) {
			System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			log.debug("IE Browser Launched!!!");
		}
		
		
		driver.get(config.getProperty("testsiteurl"));
		log.debug("Navigated to = "+config.getProperty("testsiteurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("imp.wait")), TimeUnit.SECONDS);
		
	}

}		
	
	
	public boolean isElementPresent(By by) {
		
		try {
			driver.findElement(by);
			return true;
		}
		catch (NoSuchElementException e) {
			// TODO: handle exception
			return false;
		}
	}
	
	@AfterSuite
	public void tearDown()
	{
		if(driver!=null) {
			driver.quit();
		}
		log.debug("Test Exectution Completed");
	}
}
