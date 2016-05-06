package com.latime.app.pageobject;

import java.util.List;

import org.assertj.core.api.AssertDelegateTarget;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import io.appium.java_client.android.AndroidDriver;

public class SettingsScreenPageObject extends CommonFunctions{
	
	
    AndroidDriver<WebElement> androidDriver;
    
    @FindAll({@FindBy(id = "title")})
    private List<WebElement> titles;
    
    @FindAll({@FindBy(id = "switchWidget")})
    private List<WebElement> toggleSwitch;
    
    @FindBy(id = "login_label")
    private WebElement screenTitle;
    
    @FindBy(name = "Email")
    private WebElement emailLink;
    
    @FindBy(name = "Phone")
    private WebElement phoneLink;
    
	public SettingsScreenPageObject(AndroidDriver<WebElement> androidDriver) {
		super(androidDriver);
		this.androidDriver = androidDriver;
//		PageFactory.initElements(androidDriver, this);
	}

    private String getStateOfToggleSwitchEnableNotification(){
    	return toggleSwitch.get(0).getText();
    }
	
	private String getStateOfToggleSwitchVibrate(){
    	return toggleSwitch.get(1).getText();
    }

    public boolean isToggleSwitchVibrateEnabled(){
    	System.out.println(toggleSwitch.get(1).getAttribute("enabled") + "--------enabled or disabled");
    	return toggleSwitch.get(1).getAttribute("enabled").contains("true");

    }
    
    public void turnOnSwitchEnableNotification(){
    	if (getStateOfToggleSwitchEnableNotification().contains("OFF"))
    		toggleSwitch.get(0).click();
    }
    
	public void turnOnSwitchVibrate(){
		if (getStateOfToggleSwitchVibrate().contains("OFF")){
			turnOnSwitchEnableNotification();
			toggleSwitch.get(1).click();
		}
	}
    
    public void turnOffSwitchEnableNotification(){
    	System.out.println(getStateOfToggleSwitchEnableNotification());
    	if (getStateOfToggleSwitchEnableNotification().contains("ON"))
    		toggleSwitch.get(0).click();
    }
    
	public void turnOffSwitchVibrate(){
		if (getStateOfToggleSwitchVibrate().contains("ON")){
			turnOnSwitchEnableNotification();
			toggleSwitch.get(1).click();
		}
			
	}
	
	public String getTitle(){
    	return titles.get(0).getText();
	}
	
	public void clickEmailLink(){
		emailLink.click();
	}
    
	public void clickPhoneLink(){
		phoneLink.click();
	}
	
}
