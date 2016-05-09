package com.latime.app.functional.tests;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.latime.app.framework.properties.FrameworkProperties;
import com.latime.app.pageobject.AccountLoginScreenPageObject;
import com.latime.app.pageobject.AccountsScreenPageObject;
import com.latime.app.pageobject.ArticlesDetailScreenPageObject;
import com.latime.app.pageobject.EditSectionScreenPageObject;
import com.latime.app.pageobject.SectionFrontPageObject;
import com.latime.app.pageobject.MenuScreenPageObject;
import com.latime.app.pageobject.ResetPasswordPageObject;
import com.latime.app.pageobject.SettingsScreenPageObject;
import com.latime.app.pageobject.TestPageObject;
import com.latime.app.utilities.CreateAndroidDriver;

import io.appium.java_client.android.AndroidDriver;

public class LATimesAndroidAppFunctionalTests_ExtendsDriverClass extends CreateAndroidDriver{

	
	private AndroidDriver<WebElement> driver;

	private SectionFrontPageObject sectionFront;
	private MenuScreenPageObject menuScreen;
	private AccountsScreenPageObject accountsScreen;
	private AccountLoginScreenPageObject accountsLoginScreen;
	private ResetPasswordPageObject resetPasswordScreen;
	private EditSectionScreenPageObject editSectionScreen;
	private SettingsScreenPageObject settingsScreen;
	private ArticlesDetailScreenPageObject articleDetails;
	private TestPageObject testPO;
	
	
	@Parameters({"device_Name", "device_ServerPort", "platform_Name", "app_Activity", "app_package"})
	@BeforeMethod(alwaysRun = true)
	public void testSetUp(String device_Name, String device_ServerPort, String platform_Name, String app_Activity, String app_package) throws InterruptedException, IOException{
		
		driver = androidDriver(device_Name, device_ServerPort, platform_Name, app_Activity, app_package);
		
		sectionFront = new SectionFrontPageObject(driver);
		menuScreen = new MenuScreenPageObject(driver);
		accountsScreen = new AccountsScreenPageObject(driver);
		accountsLoginScreen = new AccountLoginScreenPageObject(driver);
		resetPasswordScreen = new ResetPasswordPageObject(driver);
		editSectionScreen = new EditSectionScreenPageObject(driver);
		settingsScreen = new SettingsScreenPageObject(driver); 
		articleDetails = new ArticlesDetailScreenPageObject(driver); 
		testPO = new TestPageObject(driver);
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	
	
	@AfterMethod(alwaysRun = true)
	public void Exit() {
		driver.quit();
		
	}
	
	//Verify if user is able to swipe through tabs for corresponding right side tab in the main screen
	@Test(enabled = false)
	public void verifySwipeThroughTabsInMainScreen_ForRigthTab() throws InterruptedException{
		int initialSelectedTab = sectionFront.getSelectedTabTitleIndex();

		sectionFront.swipeRightToLeftPortraitMode(driver);
		int afterActionSelectedTab = sectionFront.getSelectedTabTitleIndex();
		
		//Assertion
		assert (initialSelectedTab ==  afterActionSelectedTab - 1) : "Page was not swipped as expected";
	}
	
	//Verify if user is able to swipe through tabs for corresponding left side tab in the main screen
	@Test(enabled = false)
	public void verifySwipeThroughTabsInMainScreen_ForLeftTab() throws InterruptedException{
		//Verify if selected tab is first
		if(sectionFront.getSelectedTabTitleIndex() == 0)
			sectionFront.swipeRightToLeftPortraitMode(driver);
		
		int initialSelectedTab = sectionFront.getSelectedTabTitleIndex();
		
		sectionFront.swipeLeftToRightInPortraitMode(driver);
		int afterActionSelectedTab = sectionFront.getSelectedTabTitleIndex();
		
		//Assertion
		assert (initialSelectedTab == afterActionSelectedTab + 1) : "Page was not swipped as expected";
	}
	
	//Verify if user is able to follow the article topic 
	@Test(enabled = false)
	public void verifyFollowTopicFunctionality() throws InterruptedException{
		int instance = 0;
		String topicToFollow;
		
		sectionFront.waitForPageHeaderLogo();
		topicToFollow = sectionFront.getCellTopicText(instance);
		sectionFront.clickCellTopic(instance);
		
		//Assertion
		assert sectionFront.getlastTabTitle().equals(topicToFollow) : "Followed topic was not found as the last tab on MainScreen";
		
	}

	//Search Functionality Verification
	@Test(enabled = false)
	public void verifySearchFunctionality() throws IOException{
		
		String searchKeyWord = "latimes";
		
		sectionFront.clickHeaderSearchBtn();
		sectionFront.enterSearchTextInSearchTextBox(searchKeyWord);
		sectionFront.clickKeyboardEnterKey();
		//Assertion
		sectionFront.assertSearchResults(searchKeyWord);
		
	}
	
	//Verify Search Page Back Button functionality
	@Test(enabled = false)
	public void verifySearchPageBackBtnFunctionality() throws InterruptedException{
		sectionFront.clickHeaderSearchBtn();
		sectionFront.clickPageBackBtn();
		//Assertion
		assert sectionFront.isHeaderLogoDisplayed() : "Main Screen Header logo is not visible on tapping back button";
	}
	
	//Verify header main menu button functionality
	@Test(enabled = false)
	public void verifyManinMenuBtnFunctionality() throws InterruptedException{
		sectionFront.clickHeaderMenuBtn();
		//Assertion
		assert menuScreen.isMenuNavigationDrawerVisible() : "Menu options' drwaer is not vsible on clicking Header menu button";
	}
	
	//Verify header main menu back button functionality
	@Test(enabled = false)
	public void verifyManinMenuBackBtnFunctionality() throws InterruptedException{
		sectionFront.clickHeaderMenuBtn();
		menuScreen.clickMenuBackBtn();
		//Assertion
		assert menuScreen.isMenuNavigationDrawerVisible().equals(false) : "Menu options' drwaer is vsible on clicking menu back button";
	}
	
	
	//Verify if Accounts menu-item on header Menu is working
	@Test(enabled = false)
	public void verifyAccountsMenuItemFunctionality() throws InterruptedException{
		String expectedTitle = "ACCOUNT";
		
		sectionFront.clickHeaderMenuBtn();
		menuScreen.clickMenuItemAccount();
		//Assertion
		assert accountsScreen.getTitle().contentEquals(expectedTitle) : "Expected Page title was : " + expectedTitle + "\nActual Page Title was: " + accountsScreen.getTitle();
	}
	
	//Verify Account Screen back button functionality
	@Test(enabled = false)
	public void verifyAccountScreenBackBtnFunctionality() throws InterruptedException{
		sectionFront.clickHeaderMenuBtn();
		menuScreen.clickMenuItemAccount();
		accountsScreen.clickPageBackBtn();
		//Assertion
		assert sectionFront.isHeaderLogoDisplayed() : "Main Screen Header logo is not visible on tapping back button";
	}
	
	
	//Verify if user is able to login with valid credentials
	@Test(enabled = false)
	public void verifyIfUserIsAbleLoginWithValidCredentials() throws InterruptedException{
		
		String userName = "janhavin@bitwiseglobal.com";
		String password = "tribune1";

		sectionFront.clickHeaderMenuBtn();
		menuScreen.clickMenuItemAccount();
		accountsScreen.clickLogInLink();
		accountsLoginScreen.enterTextInEmailTextBox(userName);
		accountsLoginScreen.enterTextInPasswordTextBox(password);
		accountsLoginScreen.clickLoginBtn();
		
		//Assertion
		assert accountsScreen.getLoginIdText().contentEquals(userName) : "Expected user ID text was: " + userName + "\nActual user ID text was: " + accountsScreen.getLoginIdText();

		accountsScreen.logOut();
	}
	
	
	//Verify the title of Forgot Password Page
	@Test(enabled = false)
	public void verifyTitleOfForgotPasswordScreen() throws InterruptedException{
		String expectedPageTitle ="Reset Password";
		
		sectionFront.clickHeaderMenuBtn();
		menuScreen.clickMenuItemAccount();
		accountsScreen.clickLogInLink();
		accountsLoginScreen.clickForgotPasswordLink();
		//Assertion
		assert resetPasswordScreen.getTitle().contentEquals(expectedPageTitle) : "Expected Page title was : " + expectedPageTitle + "\nActual Page Title was: " + resetPasswordScreen.getTitle();
		
	}

	
	//Verify Edit Section Screen back button functionality
	@Test(enabled = false)
	public void verifyEditSectionScreenBackBtnFunctionality() throws InterruptedException{
		sectionFront.clickHeaderMenuBtn();
		menuScreen.clickMenuItemEditSection();
		editSectionScreen.clickBackBtn();
		//Assertion
		assert sectionFront.isHeaderLogoDisplayed() : "Main Screen Header logo is not visible on tapping back button";
	}
	
	
	//Verify if user is able to reorder the sections in edit section page 
	@Test(enabled = false)
	public void verifyEditSectionReorderingFunctionality() throws InterruptedException{
		sectionFront.clickHeaderMenuBtn();
		menuScreen.clickMenuItemEditSection();
		
		//Getting initial values of section title  
		String innitialSection1Name = editSectionScreen.getSectionItemName(0);
		String innitialSection2Name = editSectionScreen.getSectionItemName(1);
		String innitialSection3Name = editSectionScreen.getSectionItemName(2);
		String innitialSection4Name = editSectionScreen.getSectionItemName(3);
		
		editSectionScreen.dragElementAtPosition1ToPosition3();

		//Assertion
		//1. Element 1 has moved to position 3
		assert innitialSection1Name.equals(editSectionScreen.getSectionItemName(2)) : "Section Item at Position:One was not moved to Position: Three";
		//2. Element 2 has moved to position 1
		assert innitialSection2Name.equals(editSectionScreen.getSectionItemName(0)) : "Section Item at Position:Two was not moved to Position: One";
		//3. Element 3 has moved to position 2
		assert innitialSection3Name.equals(editSectionScreen.getSectionItemName(1)) : "Section Item at Position:Three was not moved to Position: Two"; 
		//4. Element 4 remains in its position
		assert innitialSection4Name.equals(editSectionScreen.getSectionItemName(3)) : "Section Item at Position:Four is not at Position: Four"; 
	}
	
	//Verify if changes of edit title section is reflected on Home Screen
	@Test(enabled = false)
	public void verifyEditSectionReorderingFunctionalityForHomeScreen() throws InterruptedException{
		//Getting initial values of section title  
		String innitialTag1Title = sectionFront.getTabTitle(0);
		String innitialTag2Title = sectionFront.getTabTitle(1);
		String innitialTag3Title = sectionFront.getTabTitle(2);
		String innitialTag4Title = sectionFront.getTabTitle(3);
		
		sectionFront.clickHeaderMenuBtn();
		menuScreen.clickMenuItemEditSection();
		editSectionScreen.dragElementAtPosition1ToPosition3();
		editSectionScreen.clickBackBtn();
		
		//Assertion
		//1. Tab 1 has moved to position 3
		assert innitialTag1Title.equals(sectionFront.getTabTitle(2)) : "Tab at Position:One was not moved to Position: Three";
		//2. Tab 2 has moved to position 1
		assert innitialTag2Title.equals(sectionFront.getTabTitle(0)) : "Tab at Position:Two was not moved to Position: One";
		//3. Tab 3 has moved to position 2
		assert innitialTag3Title.equals(sectionFront.getTabTitle(1)) : "Tab at Position:Three was not moved to Position: Two";
		//4. Tab 4 remains in its position
		assert innitialTag4Title.equals(sectionFront.getTabTitle(3)) : " Tab at Position:Four is not at Position: Four";
	}

	//Verify Article BookMark functionality for TopNews
	@Test(enabled = false)
	public void verifyArticleBookMarkFunctionalityForTopNews() throws InterruptedException{
		int articleIndex = 0;
		
		sectionFront.cleanSavedArticles();

		sectionFront.clickTopNewsTab();
		String articleTitle =  sectionFront.getNewsArticleCellTitle(articleIndex);
		sectionFront.clickArticleCellBookMarkBtn(articleIndex);
		
		sectionFront.clickSaveTab();
		//Assertion
		assert sectionFront.getNewsArticleCellTitle(0).contentEquals(articleTitle) : "Expected Article to be saved was " + articleTitle + "\nBut was " + sectionFront.getNewsArticleCellTitle(0);
	}

	
	//Verify the title of settings screen
	@Test(enabled = false)
	public void verifyTheTitleOfSettingsScreen() throws InterruptedException{
		String expectedTitle = "SETTINGS";
		
		sectionFront.clickHeaderMenuBtn();
		menuScreen.clickMenuItemSettings();
		
		//Assertion
				assert settingsScreen.getTitle().contentEquals(expectedTitle) : "Expected Page title was : " + expectedTitle + "\nActual Page Title was: " + settingsScreen.getTitle();
	}
	

	//Verify if user is able to swipe articles in articles details page
	@Test(enabled = false)
	public void verifySwipeThroughArticles() throws InterruptedException{
		sectionFront.clickArticleTitle(0);
		
		String articleBeforeAction = articleDetails.getArticleTitle();
		articleDetails.swipeRightToLeftPortraitMode(driver);
		String articleAfterAction = articleDetails.getArticleTitle();
		//Assertion
		assert (articleBeforeAction == articleAfterAction) : "Page was not swipped as expected";  
	}
	
	
	//Verify if user is able to save article from articles details page
	@Test(enabled = true)
	public void verifySaveFunctionalityOnArticlesPage() throws InterruptedException{
		sectionFront.cleanSavedArticles();
		sectionFront.clickTopNewsTab();
		sectionFront.clickArticleTitle(0);
		String savedArticleTitle = articleDetails.getArticleTitle();
		articleDetails.clickSaveArticle();
		articleDetails.clickPageBackBtn();
		sectionFront.clickSaveTab();
		//Assertion
		assert savedArticleTitle.contentEquals(sectionFront.getNewsArticleCellTitle(0)) : "Saved Article was not found in Saved tab ";
	}
	
}
