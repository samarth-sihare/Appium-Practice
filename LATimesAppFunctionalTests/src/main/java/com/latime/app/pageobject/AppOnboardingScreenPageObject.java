package com.latime.app.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;

public class AppOnboardingScreenPageObject {
	
    AndroidDriver<WebElement> androidDriver;
    
    @FindBy(id = "btn_special_next")
    private WebElement nextBtn;
    
    @FindBy(id = "lbl_onboarding_title")
    private WebElement onboardingTitle;
    
    
	public AppOnboardingScreenPageObject(AndroidDriver<WebElement> androidDriver) {
		this.androidDriver = androidDriver;
		PageFactory.initElements(androidDriver, this);
	}
	
	public void clickNextBtn(){
		nextBtn.click();
	}
	
	public String getScreenTitle(){
		return onboardingTitle.getText();
	}

	public void assertOnboardingScreenTitles(String expectedTitle, String screenNumber){
		assert getScreenTitle().contentEquals(expectedTitle) : "The title of " + screenNumber + " onboarding screen is not as expected" ;
	}
	
}
