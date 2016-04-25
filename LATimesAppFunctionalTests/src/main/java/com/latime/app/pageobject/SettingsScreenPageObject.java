package com.latime.app.pageobject;

import java.util.List;

import org.assertj.core.api.AssertDelegateTarget;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import io.appium.java_client.android.AndroidDriver;

public class SettingsScreenPageObject{
	
	
    AndroidDriver<WebElement> androidDriver;
    
    @FindAll({@FindBy(id = "title")})
    private List<WebElement> titles;
    
    @FindAll({@FindBy(id = "switchWidget")})
    private List<WebElement> toggleSwitch;
    
    @FindBy(id = "login_label")
    private WebElement screenTitle;
    
	public SettingsScreenPageObject(AndroidDriver<WebElement> androidDriver) {
		this.androidDriver = androidDriver;
		PageFactory.initElements(androidDriver, this);
	}
          
    

	
//    public void setStateVibrateToggleSwitch(String valueON_OFF){
//
//    	if (!toggleSwitch.get(0).getText().contains("ON"))
//    		toggleSwitch.get(0).click();
//
//    	switch (valueON_OFF.toUpperCase()){
//    		case "ON": {
//    			if(toggleSwitch.get(1).getText() == "ON")
//    				toggleSwitch.get(1).click();
//
//    			break;
//    		}
//    		case "OFF":{
//    			if(toggleSwitch.get(1).getText() == "OFF")
//    				toggleSwitch.get(1).click();
//
//    			break;
//    		}
//    	}
//    }
	
	
    public String getStateOfToggleSwitchVibrate(){
    	return toggleSwitch.get(1).getText();
    }

    public boolean isToggleSwitchVibrateEnabled(){
    	String enabledState = toggleSwitch.get(1).getAttribute("enabled");
    	
    	if (enabledState == "true")
    		return true;
    	else 
    		return false;
    }
    
    public String getStateOfToggleSwitchEnableNotification(){
    	return toggleSwitch.get(0).getText();
    }
    
    public void turnOnSwitchEnableNotification(){
    	if (getStateOfToggleSwitchVibrate() == "OFF")
    		toggleSwitch.get(0).click();
    }
    
	public void turnOnSwitchVibrate(){
		if (getStateOfToggleSwitchVibrate() == "OFF"){
			turnOnSwitchEnableNotification();
			toggleSwitch.get(1).click();
		}
	}
    
    public void turnOffSwitchEnableNotification(){
    	if (getStateOfToggleSwitchVibrate() == "ON")
    		toggleSwitch.get(0).click();
    }
    
	public void turnOffSwitchVibrate(){
		if (getStateOfToggleSwitchVibrate() == "ON"){
			turnOnSwitchEnableNotification();
			toggleSwitch.get(1).click();
		}
			
	}
	
    
    
	public String getTitle(){
    	return titles.get(0).getText();
	}
	
	
    
}
