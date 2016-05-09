package com.latime.app.utilities;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.NetworkConnectionSetting;


public class FrameworkUtilities {
	
	private static boolean hasInternetAccess() throws MalformedURLException, IOException {
	
		HttpURLConnection urlc = (HttpURLConnection) 
				(new URL("http://clients3.google.com/generate_204").openConnection());
		urlc.setRequestProperty("User-Agent", "Android");
		urlc.setRequestProperty("Connection", "close");
		urlc.setConnectTimeout(1500); 
		urlc.connect();
		
		return (urlc.getResponseCode() == 204 && urlc.getContentLength() == 0);
	}
	
	
	public static void continueIfInternetIsWorking() throws Exception{
		if(!hasInternetAccess())
			System.exit(1);
			throw new Exception("Skipping test(s) because internet is not available.");
	}

}
