package com.latime.app.pageobject;

import java.util.List;

import org.assertj.core.api.AssertDelegateTarget;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import io.appium.java_client.android.AndroidDriver;

public class MenuScreenPageObject{
	
	
    AndroidDriver<WebElement> androidDriver;
    
    @FindBy(xpath = "//android.widget.ImageButton[@content-desc='Close navigation drawer']")
    private WebElement menuBackBtn;
    
    @FindAll({@FindBy(id = "navigation_drawer")})
    private static List<WebElement> menuNavigationDrawer;
    
    @FindBy(name = "Account")
    private WebElement menuItemAccount;
    
    @FindBy(name = "Edit Sections")
    private WebElement menuItemEditSection;
    
    @FindBy(name = "Offline Reading")
    private WebElement menuItemOfflineReading;
    
    @FindBy(name = "Settings")
    private WebElement menuItemSettings;
    
	public MenuScreenPageObject(AndroidDriver<WebElement> androidDriver) {
		this.androidDriver = androidDriver;
		PageFactory.initElements(androidDriver, this);
	}
	
    public void clickMenuBackBtn(){
    	menuBackBtn.click();
    }

    public Boolean isMenuNavigationDrawerVisible(){
    	return menuNavigationDrawer.size() != 0;
    }
	
    public void clickMenuItemAccount(){
    	menuItemAccount.click();
    }

    public void clickMenuItemEditSection(){
    	menuItemEditSection.click();
    }
    
    public void clickMenuItemOfflineReading(){
    	menuItemOfflineReading.click();
    }
    
    public void clickMenuItemSettings(){
    	menuItemSettings.click();
    }
    
    
    
}
