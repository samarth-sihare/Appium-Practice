package com.latime.app.pageobject;

import java.util.List;

import org.assertj.core.api.AssertDelegateTarget;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import io.appium.java_client.android.AndroidDriver;
/*
 * This is test pageobjectclass for different app
 * Right now s planner
 */
public class TestPageObject{
	
	
    AndroidDriver<WebElement> androidDriver;
    
    @FindBy(id = "floating_action_button")
    private WebElement addNewEventFloatingBtn;
    
    @FindBy(id = "title")
    private WebElement titleTextField;
    
    @FindBy(id = "location")
    private WebElement locationTextField;
    
	public TestPageObject(AndroidDriver<WebElement> androidDriver) {
		this.androidDriver = androidDriver;
		PageFactory.initElements(androidDriver, this);
	}
	
    public void clickAddNewEventFloatingBtn(){
    	addNewEventFloatingBtn.click();;
    }
    
    public void enterTextTitleTextField(String Text){
    	titleTextField.sendKeys(Text);
    }
    
    public void enterTextLocationTextField(String Text){
    	locationTextField.sendKeys(Text);
    }
    
    
    
    
    
}
