/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tribuneqa.utilities;

import com.tribuneqa.framework.properties.FrameworkProperties;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileWriter;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
* latimes.com Framework Utilities 
* Author: QA-DART
* Created on: 03/16/2016
* History of Change: 
*/

public class FrameworkUtilities {
    
	WebDriver driver;
   
	public void loadAndResize(WebDriver driver, String uri, int xAxis, int yAxis) {
		
		driver.manage().window().setSize(new Dimension(xAxis, yAxis));
		driver.get(FrameworkProperties.HOMEPAGE_URL + uri);
		
	}
    
	public void appiumStart() throws IOException, InterruptedException { 

		// Start command prompt In background.
		CommandLine command = new CommandLine("cmd");
		
		// Add different arguments In command line which requires to start appium server.
		command.addArgument("/c");
		
		// Add different arguments In command line which requires to start appium server. 
		command.addArgument("appium"); 
		command.addArgument("-a"); 
		command.addArgument("10.20.121.69");
		command.addArgument("-p");
		command.addArgument("8001");
		command.addArgument("-U");
		command.addArgument("4d0081724d5741c7");
		
		// Execute command line arguments to start appium server. 
		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler(); 
		DefaultExecutor executor = new DefaultExecutor(); 
		executor.setExitValue(1); 
		executor.execute(command, resultHandler);
		
		Thread.sleep(15000);
		
	}
	
	public static void appiumStop() throws IOException { 
		// Add different arguments In command line which requires to stop appium server. 
		CommandLine command = new CommandLine("cmd"); 
		command.addArgument("/c"); 
		command.addArgument("taskkill"); 
		command.addArgument("/F"); 
		command.addArgument("/IM"); 
		command.addArgument("node.exe"); 
		
		// Execute command line arguments to stop appium server. 
		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler(); 
		DefaultExecutor executor = new DefaultExecutor(); 
		executor.setExitValue(1); 
		executor.execute(command, resultHandler); 
	}
	
	public static void waitForPageToLoadAndroid(AndroidDriver<WebElement> driver, int time){
		try {
		driver.manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);
		}
		catch (Exception e){
			System.out.println("The Web Pagewas not rendered in " + time + " seconds. But, continuing the test");
		}
	}
	
	
	public static void waitForPageToLoadiOS(IOSDriver<WebElement> driver, int time){
		try {
		driver.manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);
		}
		catch (Exception e){
			System.out.println("The Web Page was not rendered in " + time + " seconds. But, continuing the test");
		}
	}
	

	
}
