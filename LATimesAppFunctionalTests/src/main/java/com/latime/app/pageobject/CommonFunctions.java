package com.latime.app.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.latime.app.utilities.AndroidTouchScreenGestures;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public abstract class CommonFunctions extends AndroidTouchScreenGestures {
	
    AndroidDriver<WebElement> androidDriver;
	
    @FindBy(xpath = "//android.widget.ImageButton[@content-desc='Navigate up']")
    private WebElement backSearchPageBackBtn;
	
    @FindBy(id = "snackbar_text")
    private WebElement snackBar;
    
    
	public CommonFunctions(AndroidDriver<WebElement> androidDriver) {
		this.androidDriver = androidDriver;
		PageFactory.initElements(androidDriver, this);
	}
    
    public void clickPageBackBtn(){
        backSearchPageBackBtn.click();
    }
    
    public void clickKeyboardEnterKey(){
    	androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
    }
	
    public String getTextFromSnackBar(){
    	return snackBar.getText();
    }
	
}
