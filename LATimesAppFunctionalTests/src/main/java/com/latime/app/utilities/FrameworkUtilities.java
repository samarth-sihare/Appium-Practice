package com.latime.app.utilities;

import org.openqa.selenium.WebElement;

import io.appium.java_client.NetworkConnectionSetting;
import io.appium.java_client.android.AndroidDriver;


public class FrameworkUtilities {
	
	private static boolean isWiFiEnabled(AndroidDriver<WebElement> driver){
		try{
			NetworkConnectionSetting networkConnection = ((AndroidDriver<WebElement>) driver).getNetworkConnection();
			
			Boolean status = networkConnection.wifiEnabled();
			
			return status;
		}
		catch (Exception e){
			System.out.println(e);
			return false;
		}
		
	}
	
	public static void continueIfWiFiIsConnected(AndroidDriver<WebElement> driver) throws Exception{
		if(!isWiFiEnabled(driver)){
			throw new Exception("Skipping test(s) because Wi-Fi is not connected.");
		}
			
	}

}
