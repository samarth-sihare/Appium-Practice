package com.tribuneqa.layout.tests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Rotatable;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.tribuneqa.framework.properties.FrameworkProperties;
import com.tribuneqa.utilities.FrameworkUtilities;

import io.appium.java_client.ios.IOSDriver;

import com.galenframework.api.Galen;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.model.LayoutReport;


/**
* latimes.com Subscription Pages Test Suite 
* Author: QA-DART
* Created on: 03/16/2016
* History of Change: 
*/

public class LATimesSubscriptionPagesIOSLayoutTestSuite {
	
//	private IOSDriver<WebElement> driver;
	private WebDriver driver;
	
	private LayoutReport layoutReport;
	private FrameworkUtilities utilities;
	private String testName = "This is Test Report Title";
	private String testLayoutInfo = "This is Inner Layout Info";
	
	private final List<GalenTestInfo> tests = new LinkedList<GalenTestInfo>();
	
	@Parameters({"device_Name", "platform_Name", "browser_Name", "bundle_ID", "orientation", "iPadUDID"})
	@BeforeMethod(alwaysRun = true)
	public void testSetUp(String device_Name, String platform_Name, String browser_Name, String bundle_ID, String orientation, String iPadUDID) throws MalformedURLException, InterruptedException{
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("udid", iPadUDID);
		capabilities.setCapability("deviceName", device_Name);
		capabilities.setCapability("platformName", platform_Name);
		capabilities.setCapability("browserName", browser_Name);
		capabilities.setCapability("bundleID", bundle_ID);
		
//		if(orientation.equalsIgnoreCase("PORTRAIT")){
//			capabilities.setCapability("orientation", ScreenOrientation.PORTRAIT);
//		}
//		else if (orientation.equalsIgnoreCase("LANDSCAPE")){
//			capabilities.setCapability("orientation", ScreenOrientation.LANDSCAPE);
//			System.out.println("Device Orientation is :"+orientation+"------------------------");
//		}
		
		System.setProperty("galen.config.file", FrameworkProperties.GALEN_CONFIG_FILE_PATH);
		
		driver = new IOSDriver<WebElement>(new URL("http://10.20.101.58:8002/wd/hub"), capabilities);
//		FrameworkUtilities.waitForPageToLoadiOS(driver, 50);

		if(orientation.equalsIgnoreCase("PORTRAIT")){
			((Rotatable)driver).rotate(ScreenOrientation.PORTRAIT);
		}
		else if (orientation.equalsIgnoreCase("LANDSCAPE")){
			((Rotatable)driver).rotate(ScreenOrientation.LANDSCAPE);
			System.out.println("Device Orientation is :"+orientation+" Rotatable------------------------");
		}
	}
		
	//Test for "Subscribe" Page
	@Parameters({"device_Name", "tags" })
	@Test(groups = { "Subscription" })
	public void verifyTheLayoutOfSubscriptionPage(String device, String tags_Value) throws IOException{
		
		testName = "Subscription Page Test on " + device + " View Port";
		testLayoutInfo = "Check Layout: SubscribePage.gspec include tags: " + tags_Value;

		driver.get(FrameworkProperties.URL_SUBSCRIBE_LANDING_PAGE);
		
		layoutReport = Galen.checkLayout(driver, "gspec/SubscribePage.gspec", Arrays.asList(tags_Value));
		
		GalenTestInfo test = GalenTestInfo.fromString(testName, Arrays.asList("SubscribePage", tags_Value));
		test.getReport().layout(layoutReport, testLayoutInfo);
		tests.add(test);
		
		if (layoutReport.errors() > 0)
			Assert.fail("\nThe test '" + testName + "' has failed with " + layoutReport.errors() + " error(s) in gspec... \n Printing errors in gspec: " + layoutReport.getValidationErrorResults() +"\n");
		
	}
	
	

	
	@AfterMethod(alwaysRun = true)
	public void killDriver() throws IOException{
		driver.quit();
		
	}
	
	@Parameters({"device_Name"})
	@AfterClass(alwaysRun = true)
	public void buildHTMLReport(String device) throws IOException{
		new HtmlReportBuilder().build(tests, FrameworkProperties.GALEN_HTML_REPORT_FOLDER + "/latimes-Subscription-html-reports-" + device);	
		
	}

}
