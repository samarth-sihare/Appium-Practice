package com.latime.app.pageobject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.testng.SkipException;

import io.appium.java_client.android.AndroidDriver;

public class ArticlesDetailScreenPageObject extends CommonFunctions{
	
	AndroidDriver<WebElement> androidDriver;
	
    @FindBy(id = "content_item_title")
    private WebElement lockedArticleTitle;
    
    @FindBy(id = "article_title")
    private WebElement unlockedArticleTitle;
    
    @FindBy(id = "action_save")
    private WebElement saveArticleBtn;
    
    @FindAll({@FindBy(id = "subscription_meter_button")})
    private List <WebElement> seeThisStoryBtn;
    
    @FindBy(id = "tvPageIndicator")
    private WebElement galaryPageIndicator;
   	
    @FindBy(id = "content_photo")
    private WebElement contentPhoto;
    
    @FindBy(id = "share_facebook")
    private WebElement facebookIcon;
    
    @FindBy(id = "share_twitter")
    private WebElement twitterIcon;
    
    @FindBy(id = "share_gmail")
    private WebElement emailIcon;
    
    @FindBy(id = "subscription_meter_message")
    private WebElement subscriptionMeterMessage;
    
    
	public ArticlesDetailScreenPageObject(AndroidDriver<WebElement> androidDriver) {
		super(androidDriver);
		this.androidDriver = androidDriver;
	}

	public String getArticleTitle(){
		
		if(seeThisStoryBtn.size()==0)
			return unlockedArticleTitle.getText();
		
		else
			return lockedArticleTitle.getText();
	}
	
	public void clickSaveArticle(){
		saveArticleBtn.click();
	}
	
	public String getGalaryPageIndicatorText(){
			return galaryPageIndicator.getText();
	}
	
	public void clickContentPhoto(){
		contentPhoto.click();
	}

	public void scrollToPageBottomSocialMediaIcons(){
		androidDriver.scrollTo("2016, Los Angeles Times");
	}
	
	public void unlockArtickleIfLocked(){
		if (seeThisStoryBtn.size()!=0){
			if (isFreeArticleAvailable())
				seeThisStoryBtn.get(0).click();
			else
				throw new SkipException("Could not execute the test as no free articles are available to open/unlock"); 
		}
	}
	
	public void clickFacebookIcon(){
		unlockArtickleIfLocked();
		scrollToPageBottomSocialMediaIcons();
		facebookIcon.click();
	}
	
	public void clickTwitterIcon(){
		unlockArtickleIfLocked();
		scrollToPageBottomSocialMediaIcons();
		twitterIcon.click();
	}
	
	public void clickEmailIcon(){
		unlockArtickleIfLocked();
		scrollToPageBottomSocialMediaIcons();
		emailIcon.click();
	}
	
	private boolean isFreeArticleAvailable(){
		return !subscriptionMeterMessage.getText().contains("You've read 10 of 10 free stories this month");
	}
	
	
}
