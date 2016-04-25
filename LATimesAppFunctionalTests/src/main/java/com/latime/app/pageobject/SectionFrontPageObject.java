package com.latime.app.pageobject;

import java.util.List;

import org.assertj.core.api.AssertDelegateTarget;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.latime.app.utilities.AndroidTouchScreenGestures;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class SectionFrontPageObject extends CommonFunctions{
	
	
    AndroidDriver<WebElement> androidDriver;
    
    @FindBy(className = "android.widget.ImageButton")
    private WebElement headerMenuBtn;

    @FindBy(id = "search")
    private WebElement headerSearchBtn;
    
    @FindAll({@FindBy(id = "tabTitle")})
    private static List<WebElement> tabTitle;
    
    @FindAll({@FindBy(id = "cell_topic")})
    private static List<WebElement> cellTopics;
    
    @FindAll({@FindBy(id = "cell_title")})
    private static List<WebElement> cellTitle;

    @FindAll({@FindBy(id = "cell_button_save")})
    private static List<WebElement> articleCellBookMarkBtn;
    
    @FindAll({@FindBy(id = "toolbar_logo")})
    private static List<WebElement> headerLogo;

    @FindAll({@FindBy(id = "tv_no_saved_message")})
    private static List<WebElement> noSavedMessages;
    
    @FindBy(id = "search_src_text")
    private WebElement searchTextBox;
    
    @FindAll({@FindBy(id = "search_msg")})
	private static List<WebElement> noSearchResultFoundText;
    
    @FindBy(id = "search_title")
    private WebElement searchResultFirstTitle;

    @FindBy(name = "Top News")
    private WebElement topNewsTab;
    
    @FindBy(name = "Saved")
    private WebElement saveTab;
    
    
    public SectionFrontPageObject(AndroidDriver<WebElement> androidDriver) {
		super(androidDriver);
		this.androidDriver = androidDriver;
//		PageFactory.initElements(androidDriver, this);
	}
	
	public boolean isHeaderLogoDisplayed(){
		return headerLogo.size() != 0;
	}
	
	public void waitForPageHeaderLogo() throws InterruptedException{
		while(!isHeaderLogoDisplayed())
			Thread.sleep(500);
	}
	
    public void clickHeaderMenuBtn() throws InterruptedException{
    	waitForPageHeaderLogo();
		headerMenuBtn.click();
    }
    
    public boolean menuBtnisVisible(){
    	return headerMenuBtn.isDisplayed();
    }
    
    public boolean menuBtnisEnabled(){
    	return headerMenuBtn.isEnabled();
    }
	
    
//	Search Related functions    
    public void clickHeaderSearchBtn() {
    	headerSearchBtn.click();
    }
    
    public void enterSearchTextInSearchTextBox(String searchText){
    	searchTextBox.sendKeys(searchText);
    }
    
    
    //Search results page assertion
    public void assertSearchResults(String searchText){
    	
    	if (noSearchResultFoundText.size() != 0)
    		assert noSearchResultFoundText.get(0).getText().contentEquals("No results for '" + searchText + "'"): "No results page message was not as expected";
    			 
    	else if(searchResultFirstTitle.isDisplayed()) 
    		assert searchResultFirstTitle.getText().contains(searchText) : "Actual Value is of calculator display doesnot contain: " + searchText;
    
    	else
    		assert false : "Neither results were listed nor 'No results..' page was displayed";
    		
    }
    
    public String getTabTitle(int tabPosition){
    	return tabTitle.get(tabPosition).getText();
    }
    
    
    public int getSelectedTabTitleIndex(){
    	int index = 0;
    	for (WebElement tab : tabTitle){
    		String attributeValue = tab.getAttribute("selected").toString();
    		if(attributeValue.contains("true")){
    			break;
    		}
    		index++;
    	}
    	return index;
    }
    
    
    public void clickTopNewsTab(){
    	topNewsTab.click();
    }
   
    public void clickSaveTab(){
    	saveTab.click();
    }
 
    
    public String getNewsArticleCellTitle(int articleNumber){
    	return cellTitle.get(articleNumber).getText();
    }
    
    public void clickArticleTitle(int index){
    	cellTitle.get(index).click();
    }
    
    public void clickArticleCellBookMarkBtn(int articleNumber){
    	articleCellBookMarkBtn.get(articleNumber).click();
    }
    
    public void cleanSavedArticles() throws InterruptedException{
    	clickSaveTab();
    	if(noSavedMessages.size() == 0){
    		for( WebElement bookMarkBtn : articleCellBookMarkBtn){
    			bookMarkBtn.click();
    			Thread.sleep(4000);
    		}
    	  	androidDriver.closeApp();
    		androidDriver.launchApp();
    	}
    }
    
    public String getCellTopicText(int instance){
    	return cellTopics.get(instance).getText();
    }
    
    public void clickCellTopic(int instance) throws InterruptedException{
    	cellTopics.get(instance).click();
    	Thread.sleep(3000);
    }
    
    public String getlastTabTitle(){
    	return tabTitle.get(tabTitle.size()-1).getText();
    }
   
}
