package com.latime.app.pageobject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.assertj.core.api.AssertDelegateTarget;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.latime.app.utilities.AndroidTouchScreenGestures;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class EditSectionScreenPageObject extends CommonFunctions {
	
	
    AndroidDriver<WebElement> androidDriver;
    
    @FindBy(xpath = "//android.widget.ImageButton[@content-desc='Navigate up']")
    private WebElement backBtn;
    
    @FindAll({@FindBy(xpath = "//android.widget.CheckBox[@checked='false']")})
    private List<WebElement> uncheckedSeeBtns;
    
    @FindAll({@FindBy(id = "nav_drag_handle")})
    private List<WebElement> navigationDragBtns;
    
    @FindAll({@FindBy(id = "nav_sections_text")})
    private List<WebElement> sectionItemNames;
    
    @FindAll({@FindBy(id = "nav_sections_checkBox")})
    private List<WebElement> seeItemBtns;
    
    
    public EditSectionScreenPageObject(AndroidDriver<WebElement> androidDriver){
		super(androidDriver);
		this.androidDriver = androidDriver;
//		PageFactory.initElements(androidDriver, this);
	}
	
    public void dragElementAtPosition1ToPosition3(){
    	moveElemetnt1ToElement2Position(androidDriver, navigationDragBtns.get(0), navigationDragBtns.get(2));
    }
    
    public String getSectionItemName(int itemPostion){
    	return sectionItemNames.get(itemPostion).getText();
    }
    
    public void clickBackBtn(){
    	backBtn.clear();
    }
    
	private String getLastSectionItemName(){
		return sectionItemNames.get(sectionItemNames.size()-1).getText();
	}
	
	private WebElement getLastSectionItem(){
		return sectionItemNames.get(sectionItemNames.size()-1);
	}
    
    private ImmutableList<String> getListOfEditSectionItems(){
    	List<String> tempSectionItemNameList = new ArrayList<String>();
    	boolean proceed = true;
    	
    	while (proceed){
    		
    		for(WebElement sectionItemName : sectionItemNames){
    			tempSectionItemNameList.add(sectionItemName.getText());
    		}
    		
    		String itemBeforeAction = getLastSectionItemName();
    		moveElemetnt1ToElement2Position(androidDriver, getLastSectionItem(), sectionItemNames.get(0));
    		String itemAfterAction = getLastSectionItemName();
    		
    		if (itemBeforeAction.equals(itemAfterAction))
    			proceed = false;
    	}
    	
    	ImmutableList<String> sectionItemNameList = ImmutableSet.copyOf(tempSectionItemNameList).asList();
    	
    	return sectionItemNameList;
    }
    
    public int getIndexOfSectionItemByName(String SectionItem){
    	ImmutableList<String> items =  getListOfEditSectionItems();

    	return items.indexOf(SectionItem);
    }
    
    //TODO
    private boolean isSeeBtnChecked(int index){
    	if (seeItemBtns.get(index).getAttribute("checked").contains("true"))
    		return true;
    	else
    		return false;
    }
    
    //TODO
    public void checkSeeBtn(String sectionItem){
    	
    	ImmutableList<String> items =  getListOfEditSectionItems();
    	
    	if(items.contains(sectionItem)){
    
    		androidDriver.scrollTo(sectionItem);
    		int index = items.indexOf(sectionItem);
    		
    		if(!isSeeBtnChecked(index))
    			seeItemBtns.get(index).click();
    	}
    	
    	else{
    		assert false : "Item you want to disable is not available in edit section page";
    	}
    }
    
	//TODO
	public ImmutableList<WebElement> unCheckedSeeBtnList(){

		List<WebElement> tempUncheckedSeeBtnList = new ArrayList<WebElement>();
    	boolean proceed = true;

    	while (proceed){
    		
    		for(WebElement uncheckedSeeBtn : uncheckedSeeBtns){
    			tempUncheckedSeeBtnList.add(uncheckedSeeBtn);
    		}
    		
    		String itemBeforeAction = getLastSectionItemName();
    		moveElemetnt1ToElement2Position(androidDriver, getLastSectionItem(), sectionItemNames.get(0));
    		String itemAfterAction = getLastSectionItemName();
    		
    		if (itemBeforeAction.equals(itemAfterAction))
    			proceed = false;
    	}
    	
    	
    	ImmutableList<WebElement> uncheckedSeeBtnList = ImmutableSet.copyOf(tempUncheckedSeeBtnList).asList();

    	return uncheckedSeeBtnList;
		
	}
	
	
	

}
