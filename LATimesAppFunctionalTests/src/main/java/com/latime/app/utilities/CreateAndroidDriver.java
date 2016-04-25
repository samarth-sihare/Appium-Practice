package com.latime.app.utilities;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.latime.app.framework.properties.FrameworkProperties;

import io.appium.java_client.android.AndroidDriver;

public abstract class CreateAndroidDriver {

	public AndroidDriver<WebElement> androidDriver(String device_Name, String device_ServerPort, String platform_Name, String app_Activity, String app_package) throws MalformedURLException{
		
		
		
		DesiredCapabilities capabilities = DesiredCapabilities.android();
		capabilities.setCapability("deviceName", device_Name);
		capabilities.setCapability("platformName", platform_Name);
		capabilities.setCapability("appActivity", app_Activity);
		capabilities.setCapability("app-wait-activity", app_Activity);
		capabilities.setCapability("appPackage", app_package);
		capabilities.setCapability("unicodeKeyboard", "true");
		capabilities.setCapability("resetKeyboard", "true");
		
		AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(new URL("http://" + FrameworkProperties.APPIUM_ANDROID_SERVER_IP + ":" + device_ServerPort +"/wd/hub"), capabilities);

		return driver;
		
	}
}
