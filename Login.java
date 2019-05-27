package com.test.selenium.webLogin;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import util.testUtil;

public class Login {

	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		System.out.println(System.getProperty("user.dir"));
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/driver/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://qa.journeylabs.io/");
	}
	
	@DataProvider(name= "SimpleExcelData")
	public Object[][] simpleDataProvider() throws Exception
	{
		return testUtil.getExcelData("/home/charmy/eclipse workspace/journeylabs/src/main/java/testData/loginData.xlsx","Sheet1");
	}
	
	//login
	@Test(dataProvider="SimpleExcelData")
	public void signIn(String username, String password) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.findElement(By.id("exampleInputEmail1")).sendKeys(username);
		driver.findElement(By.id("exampleInputPassword1")).sendKeys(password);
		driver.findElement(By.tagName("button")).click();
		String ExpectedUrl="https://qa.journeylabs.io/home";
		Thread.sleep(2000);
		System.out.println(ExpectedUrl);
		String ActualUrl=driver.getCurrentUrl();
		try {
			Assert.assertEquals(ExpectedUrl, ActualUrl);
			System.out.println("Login successfull");
		}
		catch(Throwable pageNavigationError){
			System.out.println("Login not successfull");
		}
	Thread.sleep(5000);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
