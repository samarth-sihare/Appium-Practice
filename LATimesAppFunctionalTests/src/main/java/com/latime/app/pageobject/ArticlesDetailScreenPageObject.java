package com.latime.app.pageobject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

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
}
