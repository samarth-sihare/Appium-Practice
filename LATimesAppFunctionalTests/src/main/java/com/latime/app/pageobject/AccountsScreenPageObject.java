package com.latime.app.pageobject;

import java.util.List;

import org.assertj.core.api.AssertDelegateTarget;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.latime.app.utilities.AndroidTouchScreenGestures;

import io.appium.java_client.android.AndroidDriver;

public class AccountsScreenPageObject extends CommonFunctions{
	
	
    AndroidDriver<WebElement> androidDriver;
    
    @FindBy(name = "Log In")
    private WebElement logInLink;
    
    @FindBy(name = "Log Out")
    private WebElement logOutLink;
    
    @FindBy(id = "button1")
    private WebElement popUpLogOutBtn;
    
    @FindAll({@FindBy(id = "title")})
	private static List<WebElement> pageTitles;
    
    @FindAll({@FindBy(id = "summary")})
    private static List<WebElement> summartTexts;
    
	public AccountsScreenPageObject(AndroidDriver<WebElement> androidDriver) {
		super(androidDriver);
		this.androidDriver = androidDriver;
//		PageFactory.initElements(androidDriver, this);
	}
	
    public void clickLogInLink(){
    	logInLink.click();
    }
    
    public String getTitle(){
    	return pageTitles.get(0).getText();
    }

    //When Logged IN
    
    public String getLoginIdText(){
    	return summartTexts.get(0).getText();
    }
    
    public void logOut(){
    	logOutLink.click();
    	popUpLogOutBtn.click();
    }
    
}
