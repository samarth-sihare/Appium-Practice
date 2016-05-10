package com.latime.app.functional.tests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
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
import com.latime.app.pageobject.AppOnboardingScreenPageObject;
import com.latime.app.pageobject.ArticlesDetailScreenPageObject;
import com.latime.app.pageobject.EditSectionScreenPageObject;
import com.latime.app.pageobject.SectionFrontPageObject;
import com.latime.app.pageobject.MenuScreenPageObject;
import com.latime.app.pageobject.ResetPasswordPageObject;
import com.latime.app.pageobject.SettingsScreenPageObject;
import com.latime.app.utilities.FrameworkUtilities;

import io.appium.java_client.android.AndroidDriver;

public class LATimesAndroidAppFunctionalTests{

	
	private AndroidDriver<WebElement> driver;

	private SectionFrontPageObject sectionFront;
	private MenuScreenPageObject menuScreen;
	private AccountsScreenPageObject accountsScreen;
	private AccountLoginScreenPageObject accountsLoginScreen;
	private ResetPasswordPageObject resetPasswordScreen;
	private EditSectionScreenPageObject editSectionScreen;
	private SettingsScreenPageObject settingsScreen;
	private ArticlesDetailScreenPageObject articleDetails;
	private AppOnboardingScreenPageObject appOnboardingScreen;
	
	@Parameters({"device_Name", "device_ServerPort", "platform_Name", "app_Activity", "app_package"})
	@BeforeMethod(alwaysRun = true)
	public void testSetUp(String device_Name, String device_ServerPort, String platform_Name, String app_Activity, String app_package) throws Exception{
		
		DesiredCapabilities capabilities = DesiredCapabilities.android();
		capabilities.setCapability("deviceName", device_Name);
		capabilities.setCapability("platformName", platform_Name);
		capabilities.setCapability("appActivity", app_Activity);
		capabilities.setCapability("app-wait-activity", "com.apptivateme.next.la/com.tribune.universalnews.MainActivity");
		capabilities.setCapability("appPackage", app_package);
		capabilities.setCapability("unicodeKeyboard", "true");
		capabilities.setCapability("resetKeyboard", "true");
		
//		FrameworkUtilities.continueIfInternetIsWorking();
		
		driver = new AndroidDriver<WebElement>(new URL("http://" + FrameworkProperties.APPIUM_ANDROID_SERVER_IP + ":" + device_ServerPort +"/wd/hub"), capabilities);
		
		FrameworkUtilities.continueIfWiFiIsConnected(driver);
		
		sectionFront = new SectionFrontPageObject(driver);
		menuScreen = new MenuScreenPageObject(driver);
		accountsScreen = new AccountsScreenPageObject(driver);
		accountsLoginScreen = new AccountLoginScreenPageObject(driver);
		resetPasswordScreen = new ResetPasswordPageObject(driver);
		editSectionScreen = new EditSectionScreenPageObject(driver);
		settingsScreen = new SettingsScreenPageObject(driver); 
		articleDetails = new ArticlesDetailScreenPageObject(driver); 
		appOnboardingScreen = new AppOnboardingScreenPageObject(driver);
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	
	
	@AfterMethod(alwaysRun = true)
	public void Exit() {
		driver.quit();
	}
	
	//Verify if user is able to swipe through tabs for corresponding right side tab in the main screen
	@Test(enabled = false)
	public void verifySwipeThroughTabsInSectionFront_ForRigthTab() throws InterruptedException{
		
		sectionFront.checkSwipeSectionFrontToLastTab();
	}
	
	//Verify if user is able to jump to intended tab in section front
	@Test(enabled = false)
	public void jumpToTabByName() throws InterruptedException{
		String tabToJump = "sports";
		
		sectionFront.findAndClickTabWithTitle(tabToJump);
		//Assertion
		assert sectionFront.getSelectedTabTitle().equalsIgnoreCase(tabToJump) : "User is unable to jump to section '" + tabToJump + "' as expected";
		
	}
	
	//Verify if user is able to Jump through tabs one by one in Section front
	@Test(enabled = false)
	public void verifyJumpThroughTabsInSectionFront() throws InterruptedException{
		
		sectionFront.checkJumpSectionFrontToLastTab();
	}
	
	//Verify if user is able to follow the article topic 
	@Test(enabled = false)
	public void verifyFollowTopicFunctionality() throws InterruptedException{
		int index = 0;
		String topicToFollow;
		
		sectionFront.waitForPageHeaderLogo();
		topicToFollow = sectionFront.getCellTopicText(index);
		sectionFront.clickCellTopic(index);

		//Assertion for new tab 
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
		
		//Assertion for Snack Bar
		assert sectionFront.getTextFromSnackBar().contentEquals("Article Saved") : "Snack Bar message was not as expected ";
		
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
	@Test(enabled = true)
	public void verifySwipeThroughArticles() throws InterruptedException{
		sectionFront.clickArticleTitle(0);
		
		String articleBeforeAction = articleDetails.getArticleTitle();
		articleDetails.swipeRightToLeftAt70PercentFromTop(driver);
		String articleAfterAction = articleDetails.getArticleTitle();
		//Assertion
		assert (articleBeforeAction != articleAfterAction) : "Page was not swipped as expected";
	}
	
	
	//Verify if user is able to save article from articles details page
	@Test(enabled = false)
	public void verifySaveFunctionalityOnArticlesPage() throws InterruptedException{
		sectionFront.cleanSavedArticles();
		sectionFront.clickTopNewsTab();
		sectionFront.clickArticleTitle(0);
		String savedArticleTitle = articleDetails.getArticleTitle();
		articleDetails.clickSaveArticle();
		
		//Assertion for Snack Bar
		assert articleDetails.getTextFromSnackBar().contentEquals("Article Saved") : "Snack Bar message was not as expected ";
		
		articleDetails.clickPageBackBtn();
		sectionFront.clickSaveTab();
		//Assertion for Save Tab
		assert savedArticleTitle.contentEquals(sectionFront.getNewsArticleCellTitle(0)) : "Saved Article was not found in Saved tab ";
	}
	
	//Verify Share functionality on Facebook from Article Screen
	@Test(enabled = false)
	public void verifyShareFunctionalityOnFacebookFromArticleScreen(){
		sectionFront.clickArticleTitle(0);
		articleDetails.shareOnFaceBook();
		//Assertion
		assert articleDetails.isFacebookWidgetVisible() : "Facebook widget was not visible on clicking Facebook icon";
	}	
	
	//Verify Share functionality on Twitter from Article Screen
	@Test(enabled = false)
	public void verifyShareFunctionalityOnTwitterFromArticleScreen(){
		sectionFront.clickArticleTitle(0);
		String articleToBeShared = articleDetails.getArticleTitle();
		
		articleDetails.shareOnTwitter();
		//Assertion for twitter widget 
		assert articleDetails.isTwitterWidgetVisible() : "Twitter widget was not visible on clicking Twitter icon";
		//Assertion for the shared article
		assert articleToBeShared.contentEquals(articleDetails.getTwitterWidgetSharedTitle()) : "Shared text doesnot have the actual article title";
	}	
	
	//Verify Share functionality on Gmail from Article Screen
	@Test(enabled = false)
	public void verifyShareFunctionalityOnGmailFromArticleScreen(){
		sectionFront.clickArticleTitle(0);
		String articleToBeShared = articleDetails.getArticleTitle();
		
		articleDetails.shareOnGmail();
		//Assertion for gmail widget 
		assert articleDetails.isGmailAppVisible() : "Email App was not visible on clicking Gmail icon";
		//Assertion for the shared article
		assert articleToBeShared.contentEquals(articleDetails.getGmailAppSubjectSharedTitle()) : "Shared text doesnot have the actual article title";
	}	
		
	//Verify Share functionality on Facebook from Article Screen Bottom
	@Test(enabled = false)
	public void verifyShareFunctionalityOnFacebookFromArticleScreenBottom(){
		sectionFront.clickArticleTitle(0);
		articleDetails.clickFacebookIcon();

		//Assertion
		assert articleDetails.isFacebookWidgetVisible() : "Facebook widget was not visible on clicking Facebook icon";
	}	
	
	//Verify Share functionality on Twitter from Article Screen Bottom
	@Test(enabled = false)
	public void verifyShareFunctionalityOnTwitterFromArticleScreenBottom(){
		sectionFront.clickArticleTitle(0);
		String articleToBeShared = articleDetails.getArticleTitle();
		
		articleDetails.clickTwitterIcon();
		//Assertion
		assert articleDetails.isTwitterWidgetVisible() : "Twitter widget was not visible on clicking Twitter icon";
		//Assertion for the shared article
		assert articleToBeShared.contentEquals(articleDetails.getTwitterWidgetSharedTitle()) : "Shared text doesnot have the actual article title";
	}	
	
	//Verify Share functionality on Gmail from Article Screen Bottom
	@Test(enabled = false)
	public void verifyShareFunctionalityOnEmailFromArticleScreenBottom(){
		sectionFront.clickArticleTitle(1);
		String articleToBeShared = articleDetails.getArticleTitle();
		
		articleDetails.clickEmailIcon();
		//Assertion
		assert articleDetails.isGmailAppVisible() : "Gmail App was not visible on clicking Email icon";
		//Assertion for the shared article
		assert articleToBeShared.contentEquals(articleDetails.getGmailAppSubjectSharedTitle()) : "Shared text doesnot have the actual article title";
	}	
	
	//Verify if Vibrate switch is disabled on turning EnableNotifications Switch to OFF
	@Test(enabled = false)
	public void verifyEnableNotificationSwitchOffDisablesVibrateSwitch() throws InterruptedException{
		sectionFront.clickHeaderMenuBtn();
		menuScreen.clickMenuItemSettings();
		
		settingsScreen.turnOffSwitchEnableNotification();
		//Assertion 
		assert !settingsScreen.isToggleSwitchVibrateEnabled() : "Toggle Switch Vibrate was expected to be disbaled but is Inabled";
	}
	
	//Verify if Vibrate switch is enabled on turning EnableNotifications Switch to ON
	@Test(enabled = false)
	public void verifyEnableNotificationSwitchOnEnablesVibrateSwitch() throws InterruptedException{
		sectionFront.clickHeaderMenuBtn();
		menuScreen.clickMenuItemSettings();
		
		settingsScreen.turnOnSwitchEnableNotification();
		
		assert settingsScreen.isToggleSwitchVibrateEnabled() : "Toggle Switch Vibrate was expected to be enabaled but is disabled";
	}
	
	//Verify if user is able to contact support using email link in settings page
	@Test(enabled = false)
	public void verifySupportFunctionalityThroughEmailOnSettingsScreen() throws InterruptedException{
		sectionFront.clickHeaderMenuBtn();
		menuScreen.clickMenuItemSettings();
		
		settingsScreen.clickEmailLink();
		settingsScreen.clickGmailIcon();
		//Assertion Gmail App Visible
		assert articleDetails.isGmailAppVisible() : "Gmail App was not visible on clicking Email icon";
		//Assertion for the 'To' 
		assert settingsScreen.getGmailAppToTextBoxText().contentEquals("<mobile@latimes.com>, ") : "'TO' email address is not as expected " ;
		//Assertion for subject line
		assert articleDetails.getGmailAppSubjectLineText().contentEquals("App support") : "Shared text doesnot have the actual article title";
	}
	
	//Verify if user is able to contact support using Phone link in settings page
	@Test(enabled = false)
	public void verifySupportFunctionalityThroughPhoneOnSettingsScreen() throws InterruptedException{
		sectionFront.clickHeaderMenuBtn();
		menuScreen.clickMenuItemSettings();
		settingsScreen.clickPhoneLink();
		//Assertion is phone call App was invoked 
		assert settingsScreen.isPhoneCallAppVisible() : "Phone Call app was not loaded on clicking the Phone line";
		//Assertion is dialer screen is pre-filled with expected number
		assert settingsScreen.getPhoneDialerScreenText().contentEquals("8002529141") : "Expected phone number was not pre-filled in phone dialer screen";
	}
	
	//Verify if download starts on clicking Offline Reading menu option 
	@Test(enabled = false)
	public void verifyOfflineReadingFunctionality() throws InterruptedException{
		sectionFront.clickHeaderMenuBtn();
		menuScreen.clickMenuItemOfflineReading();
		driver.openNotifications();
		//Assertion for Notification
		assert sectionFront.isDownloadingContentNotificationVisible() : "Downloading content notification was not found";
		//Stop Downloading
		sectionFront.clickNotificationCancelDownloadBtn();
	}
	
	//Verify App Onboarding on first launch
	@Test(enabled = false)
	public void verifyAppOnboardingFunctionality() throws InterruptedException{
		driver.resetApp();
		//Assertion Screen 1
		appOnboardingScreen.assertOnboardingScreenTitles("Hello", "First");
		
		//Assertion Screen 2
		appOnboardingScreen.clickNextBtn();
		appOnboardingScreen.assertOnboardingScreenTitles("Watch Ready", "Second");
				
		//Assertion Screen 3
		appOnboardingScreen.clickNextBtn();
		appOnboardingScreen.assertOnboardingScreenTitles("Save Stories", "Third");
		
		//Assertion Screen 4
		appOnboardingScreen.clickNextBtn();
		appOnboardingScreen.assertOnboardingScreenTitles("Follow Topics", "fourth");
		
		//Assertion for SectionFront if user is on TOP NEWS on section front
		appOnboardingScreen.clickNextBtn();
		assert sectionFront.getSelectedTabTitle().contentEquals("Top News") : "User is not on TOP NEWS section front on app launch" ;
						
	}

}
