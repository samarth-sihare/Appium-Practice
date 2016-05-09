package com.latime.app.pageobject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.latime.app.utilities.AndroidTouchScreenGestures;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public abstract class CommonFunctions extends AndroidTouchScreenGestures {
	
    AndroidDriver<WebElement> androidDriver;
	
    @FindBy(xpath = "//android.widget.ImageButton[@content-desc='Navigate up']")
    private WebElement backSearchPageBackBtn;
	
    @FindBy(id = "snackbar_text")
    private WebElement snackBar;
    
    @FindBy(id = "action_share")
    private WebElement shareBtn;
    
    @FindBy(name = "Facebook")
    private WebElement facebookShareIcon;
    
    @FindAll({@FindBy(xpath = "//android.widget.LinearLayout[@package='com.facebook.katana']")})
    private List <WebElement> facbookWidget;

    @FindBy(name = "Tweet")
    private WebElement twitterShareIcon;
    
    @FindAll({@FindBy(xpath = "//android.widget.LinearLayout[@package='com.twitter.android']")})
    private List <WebElement> twitterWidget;
    
    @FindBy(xpath = "//android.widget.EditText[@resource-id='com.twitter.android:id/tweet_text']")
    private WebElement twitterSharedText;
    
    @FindBy(name = "Gmail")
    private WebElement gmailShareIcon;
    
    @FindAll({@FindBy(xpath = "//android.widget.FrameLayout[@package='com.google.android.gm']")})
    private List <WebElement> gmailApp;
 
    @FindAll({@FindBy(xpath = "//android.widget.EditText[@package='com.android.contacts']")})
    private List <WebElement> phoneCallApp;
    
    @FindBy(id = "com.google.android.gm:id/to")
    private WebElement gmailToTextBox;
    
    @FindBy(id = "com.google.android.gm:id/subject")
    private WebElement gmailSubjectSharedText;
    
    @FindBy(id = "com.android.contacts:id/digits")
    private WebElement phoneDialerScreen;
    
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
	
    public void shareOnFaceBook(){
    	shareBtn.click();
    	facebookShareIcon.click();
    }
    
    public boolean isFacebookWidgetVisible(){
    	int facebookWidgerSize = facbookWidget.size() ;
    	return facebookWidgerSize != 0;
    }

    public void shareOnTwitter(){
    	shareBtn.click();
    	twitterShareIcon.click();
    }
    
    public boolean isTwitterWidgetVisible(){
    	int twitterWidgerSize = twitterWidget.size() ;
    	return twitterWidgerSize != 0;
    }
    
    public String getTwitterWidgetSharedTitle(){
    	String sharedText = twitterSharedText.getText();
    	String[] actualTitle = sharedText.split("\\nhttp");
    	return actualTitle[0];
    	}
    
    public void shareOnGmail(){
    	shareBtn.click();
    	clickGmailIcon();
    }
    
    public boolean isGmailAppVisible(){
    	int gmailWidgerSize = gmailApp.size() ;
    	return gmailWidgerSize != 0;
    }
    
    public boolean isPhoneCallAppVisible(){
    	int phoneCallAppSize = phoneCallApp.size() ;
    	return phoneCallAppSize != 0;
    }
    
    public String getGmailAppToTextBoxText(){
    	return gmailToTextBox.getText();
    }
    
    public String getGmailAppSubjectLineText(){
    	return gmailSubjectSharedText.getText();
    }
    
    public String getGmailAppSubjectSharedTitle(){
    	String sharedText = getGmailAppSubjectLineText();
    	String[] actualTitle = sharedText.split("\\nhttp");
    	return actualTitle[0];
    }
    
    public void clickGmailIcon(){
    	gmailShareIcon.click();
    }

    public String getPhoneDialerScreenText(){
    	return phoneDialerScreen.getText();
    }
    
    public <T> T waitUntil(ExpectedCondition<T> condition, int time){
        return new WebDriverWait(androidDriver, time).until(condition);
    }

}
