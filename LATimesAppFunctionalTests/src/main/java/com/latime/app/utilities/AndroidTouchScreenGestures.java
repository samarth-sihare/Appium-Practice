package com.latime.app.utilities;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public abstract class AndroidTouchScreenGestures {

	
	//Gesture Swipe left to right in portrait mode 
	public void swipeRightToLeftPortraitMode(AndroidDriver<WebElement> androidDriver){
		androidDriver.swipe(640, 1340, 100, 1340, 300);
	}

	//Gesture Swipe right to left in portrait mode 
	public void swipeLeftToRightInPortraitMode(AndroidDriver<WebElement> androidDriver){
		androidDriver.swipe(100, 1340, 740, 1340, 300);
		
	}
	
    //Gesture Moving element from one position to another
	public void moveElemetnt1ToElement2Position(AndroidDriver<WebElement> androidDriver, WebElement fromElement1, WebElement toElement2){
		TouchAction touchAction = new TouchAction(androidDriver);
		touchAction.longPress(fromElement1).moveTo(toElement2).release().perform();
	}

	//tapping with two fingers
	public void tapwithOnefingures(AndroidDriver<WebElement> androidDriver, WebElement element){
		androidDriver.tap(1, element, 500);
	}
	
	//tapping with two fingers
	public void tapwithTwofingures(AndroidDriver<WebElement> androidDriver, WebElement element){
		androidDriver.tap(2, element, 500);
	}

	
	public void sampleMethod(AndroidDriver<WebElement> androidDriver, WebElement element1, WebElement element2){
		TouchAction touchAction = new TouchAction(androidDriver);
		touchAction.longPress(element1, 0, 250);
	}

}
