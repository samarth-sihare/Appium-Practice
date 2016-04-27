package com.latime.app.pageobject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
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
    
    @FindBy(name = "Email")
    private WebElement emailShareIcon;
    
    @FindAll({@FindBy(xpath = "//android.view.View[@package='com.android.email']")})
    private List <WebElement> emailApp;
 
    @FindBy(xpath = "//android.widget.EditText[@resource-id='com.android.email:id/composer_subject_edit']")
    private WebElement EmailSubjectSharedText;
    
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
    
    public void shareOnEmail(){
    	shareBtn.click();
    	emailShareIcon.click();
    }
    
    public boolean isEmailAppVisible(){
    	int twitterWidgerSize = emailApp.size() ;
    	return twitterWidgerSize != 0;
    }
    
    public String getEmailAppSubjectSharedTitle(){
    	String sharedText = EmailSubjectSharedText.getText();
    	String[] actualTitle = sharedText.split("\\nhttp");
    	return actualTitle[0];
    }
    
}
