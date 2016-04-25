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

import com.latime.app.utilities.AndroidTouchScreenGestures;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class EditSectionScreenPageObject extends CommonFunctions {
	
	
    AndroidDriver<WebElement> androidDriver;
    
    @FindBy(xpath = "//android.widget.ImageButton[@content-desc='Navigate up']")
    private WebElement backBtn;
    
    @FindAll({@FindBy(id = "nav_drag_handle")})
    private List<WebElement> navigationDragBtn;
    
    @FindAll({@FindBy(id = "nav_sections_text")})
    private List<WebElement> sectionItemName;
    
	public EditSectionScreenPageObject(AndroidDriver<WebElement> androidDriver){
		super(androidDriver);
		this.androidDriver = androidDriver;
//		PageFactory.initElements(androidDriver, this);
	}
	
    public void dragElementAtPosition1ToPosition3(){
    	moveElemetnt1ToElement2Position(androidDriver, navigationDragBtn.get(0), navigationDragBtn.get(2));
    }
    
    public String getSectionItemName(int itemPostion){
    	return sectionItemName.get(itemPostion).getText();
    }
    
    public void clickBackBtn(){
    	backBtn.clear();
    }
    
}
