package com.tribuneqa.layout.tests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.ios.IOSDriver;

public class IOS_Settings {
	
	private IOSDriver<WebElement> driver;
	
	@BeforeMethod(alwaysRun = true)
	public void main() throws MalformedURLException, InterruptedException{
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "iPhone 5");
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("platformVersion", "9.2");
		capabilities.setCapability("app", "settings");
		
		driver = new IOSDriver<WebElement>(new URL("http://10.20.101.69:8002/wd/hub"), capabilities);
		
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	
	@Test
	public void test(){
		String str = "abc";
	}
}
