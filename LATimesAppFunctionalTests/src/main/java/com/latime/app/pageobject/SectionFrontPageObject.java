package com.latime.app.pageobject;

import java.util.List;

import org.assertj.core.api.AssertDelegateTarget;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.latime.app.utilities.AndroidTouchScreenGestures;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

import org.openqa.selenium.support.ui.ExpectedConditions;

public class SectionFrontPageObject extends CommonFunctions{
	
	
    AndroidDriver<WebElement> androidDriver;
    
    By headerLogo = By.id("toolbar_logo");
    
//  @FindAll({@FindBy(id = "toolbar_logo")})
//  private List<WebElement> headerLogo;
    
    @FindBy(className = "android.widget.ImageButton")
    private WebElement headerMenuBtn;

    @FindBy(id = "search")
    private WebElement headerSearchBtn;
    
    @FindAll({@FindBy(id = "tabTitle")})
    private List<WebElement> tabTitle;
    
    @FindAll({@FindBy(id = "cell_topic")})
    private List<WebElement> cellTopics;
    
    @FindAll({@FindBy(id = "cell_title")})
    private List<WebElement> cellTitle;

    @FindAll({@FindBy(id = "cell_button_save")})
    private List<WebElement> articleCellBookMarkBtn;
    
    @FindAll({@FindBy(id = "tv_no_saved_message")})
    private List<WebElement> noSavedMessages;
    
    @FindBy(id = "search_src_text")
    private WebElement searchTextBox;
    
    @FindAll({@FindBy(id = "search_msg")})
	private List<WebElement> noSearchResultFoundText;
    
    @FindBy(id = "search_title")
    private WebElement searchResultFirstTitle;

    @FindBy(name = "Top News")
    private WebElement topNewsTab;
    
    @FindBy(name = "Saved")
    private WebElement saveTab;
    
    @FindBy(xpath = "//android.widget.TextView[contains(@selected, 'true')]")
    private WebElement selected;
    
    @FindAll({@FindBy(name = "Downloading Content")})
    private List<WebElement> downloadingContentNotification;
    
    @FindBy(name = "Cancel Download")
    private WebElement notificationCancelDownloadBtn;
    
    
    public SectionFrontPageObject(AndroidDriver<WebElement> androidDriver) {
		super(androidDriver);
		this.androidDriver = androidDriver;
//		PageFactory.initElements(androidDriver, this);
	}
	
    private WebElement lastTab(){
    	return tabTitle.get(getlastTabIndex());
    }
    
    private WebElement firstTab(){
    	return tabTitle.get(0);
    }
    
	public boolean isHeaderLogoDisplayed(){
		try{
			return androidDriver.findElement(headerLogo).isDisplayed();
		}
		catch(NoSuchElementException e){
			return false;
		}
	}
	
	public void waitForPageHeaderLogo() throws InterruptedException{
		waitUntil(ExpectedConditions.presenceOfElementLocated(headerLogo), 15);
	}
	
    public void clickHeaderMenuBtn() throws InterruptedException{
    	waitForPageHeaderLogo();
		headerMenuBtn.click();
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
    
    
    public int getSelectedTabTitleIndex() throws InterruptedException{
    	Thread.sleep(1000);
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
    
    
    public String getSelectedTabTitle() throws InterruptedException{
    	String tabTitleValue = null;
    	for (WebElement tab : tabTitle){
    		String attributeValue = tab.getAttribute("selected").toString();
    		if(attributeValue.contains("true")){
    			tabTitleValue = tab.getAttribute("text");
    			break;
    		}
    	}
    	return tabTitleValue;
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
    	Thread.sleep(2000);
    }
    
    private int getlastTabIndex(){
    	return tabTitle.size()-1;
    }
    
    public String getlastTabTitle(){
    	return tabTitle.get(getlastTabIndex()).getText();
    }
    
    public void clickTabNextToSelectedTab() throws InterruptedException{
    	tabTitle.get(getSelectedTabTitleIndex()+1).click();
    }
    
    public boolean isDownloadingContentNotificationVisible(){
    	return downloadingContentNotification.size() != 0;
    }
    
    public void clickNotificationCancelDownloadBtn(){
    	notificationCancelDownloadBtn.click();
    }
    
    public void checkSwipeSectionFrontToLastTab() throws InterruptedException{
    	while (getlastTabIndex() != getSelectedTabTitleIndex()){
			String befoerActionSelectedTab = getSelectedTabTitle();
			swipeRightToLeftPortraitMode(androidDriver);
			String afterActionSelectedTab = getSelectedTabTitle();
			
			assert (befoerActionSelectedTab ==  afterActionSelectedTab) : "Page was not swipped as expected"; 
			
    	}
    }
    
    public void checkJumpSectionFrontToLastTab() throws InterruptedException{
		while (getlastTabIndex() != getSelectedTabTitleIndex()){
			String befoerActionSelectedTab = getSelectedTabTitle();
		
			clickTabNextToSelectedTab();
			String afterActionSelectedTab = getSelectedTabTitle();
		
			//Assertion
			assert (befoerActionSelectedTab !=  afterActionSelectedTab) : "User is unable to jump sections as expected";
		}

    }
    
    public void findAndClickTabWithTitle(String jumpToTab){
    	
    	boolean clicked = false;
    	
    	while(clicked == false){
    		for (WebElement tab : tabTitle){
    			String title = tab.getText();
    		
    			if(title.equalsIgnoreCase(jumpToTab)){
    				tab.click();
    				clicked = true;
    				break;
    			}
    		}
    		
    		if(clicked)
    			break;
    		
    		String beforeActionLastTabTitle = getlastTabTitle();
    		moveElemetnt1ToElement2Position(androidDriver, lastTab(), firstTab());
    		String afterActionLastTabTitle = getlastTabTitle();
    		
    		if(beforeActionLastTabTitle.contentEquals(afterActionLastTabTitle)){
    			assert false : "The tab you are looking for is not visible";
    		}
    	}
    }
}
    
