package com.latime.app.pageobject;

import java.util.List;

import org.assertj.core.api.AssertDelegateTarget;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class AccountLoginScreenPageObject{
	
	
    AndroidDriver<WebElement> androidDriver;
    
    @FindAll({@FindBy(className = "android.widget.EditText")})
	private static List<WebElement> allTextBoxes;
    
    @FindBy(id = "etUserEmail")
    private WebElement emailTextBox;
    
    @FindBy(id = "login_forgot_password")
    private WebElement forgotPasswordLink;
    
    @FindBy(id = "etPassword")
    private WebElement passwordTextBox;
    
    @FindBy(id = "login_submit")
    private WebElement loginBtn;
    
    @FindBy(name = "Register")
    private WebElement registerTab;
    
    @FindBy(id = "login_trouble")
    private WebElement havingTroubleLoggingInLink;
    
    @FindBy(id = "register_submit")
    private WebElement registerBtn;
    
    
	public AccountLoginScreenPageObject(AndroidDriver<WebElement> androidDriver) {
		this.androidDriver = androidDriver;
		PageFactory.initElements(androidDriver, this);
	}
	
    public void enterTextInEmailTextBox(String email) throws InterruptedException{
    	emailTextBox.sendKeys(email);
    }
    
    public void enterTextInPasswordTextBox(String password){
    	passwordTextBox.sendKeys(password);
    	
    }
    
    public void clickLoginBtn(){
    	loginBtn.click();
    }
    
    public void clickForgotPasswordLink(){
    	forgotPasswordLink.click();
    }
    
    public void clickRegisterTab(){
    	registerTab.click();
    }
    
    public void clickHavingTroubleLoggingInLink(){
    	havingTroubleLoggingInLink.click();
    }
    
    public void clickRegisterBtn(){
    	registerBtn.click();
    }

}
