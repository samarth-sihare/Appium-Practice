package com.latime.app.pageobject;

import java.util.List;

import org.assertj.core.api.AssertDelegateTarget;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import io.appium.java_client.android.AndroidDriver;

public class ResetPasswordPageObject{
	
	
    AndroidDriver<WebElement> androidDriver;
    
    @FindBy(id = "etUserEmail")
    private WebElement emailTextBox;
    
    @FindBy(id = "etUserEmail")
    private WebElement sendMeLinkBtn;
    
    @FindBy(id = "login_label")
    private WebElement screenTitle;
    
	public ResetPasswordPageObject(AndroidDriver<WebElement> androidDriver) {
		this.androidDriver = androidDriver;
		PageFactory.initElements(androidDriver, this);
	}
	
    public void enterTextEmailTextBox(String Text){
    	emailTextBox.sendKeys(Text);
    }
    
    public void clickSendMeLinkBtn(){
    	sendMeLinkBtn.click();
    }
    
    public String getTitle(){
    	return screenTitle.getText();
    }
    
}
