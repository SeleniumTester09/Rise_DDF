 package com.rise.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rise.base.TestBase;

public class AddCustTest extends TestBase {
	
	@Test(dataProvider= "getData")
	public void addCust(String firstName, String lastName, String postCode, String alerttext) throws InterruptedException
	{
		driver.findElement(By.xpath(OR.getProperty("addcust"))).click();
		driver.findElement(By.xpath(OR.getProperty("firstname"))).sendKeys(firstName);;
		driver.findElement(By.xpath(OR.getProperty("firstname"))).sendKeys(lastName);
		driver.findElement(By.xpath(OR.getProperty("postcode"))).sendKeys(postCode);
		driver.findElement(By.xpath(OR.getProperty("addbtn"))).click();
		
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(alerttext)); 
		alert.accept();
		
		Thread.sleep(3000);
	}
	
	
	@DataProvider
	public Object [][]  getData()
	{

		String sheetName = "AddCustomerTest";
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][1];
		
		//Hashtable<String,String> table = null;

		for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2

			//table = new Hashtable<String,String>();
			
			for (int colNum = 0; colNum < cols; colNum++) {

				// data[0][0]
			//	table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum - 2][0] = excel.getCellData(sheetName, colNum, rowNum);//table;
			}

		}

		return data;
		
	}

}
