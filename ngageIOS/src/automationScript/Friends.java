package automationScript;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import bean.FriendAddNameData;
import bean.GroupChatData;
import utilites.BussenesCompernat;
import utilites.Ngagedriver;
import utilites.PropertyHandler;
import dblayer.InPutExcelDataExtractor;
import dblayer.InPutIOSTestCase;

public class Friends 
{
	private static Logger Log = Logger.getLogger(RegisterPage.class.getName());
	InPutExcelDataExtractor excelDataExtractor=new InPutExcelDataExtractor();
	InPutIOSTestCase inPutIOSTestCase=new InPutIOSTestCase();
	BussenesCompernat utili=new BussenesCompernat();
	private List<WebElement>FriendList=null;
	private List<WebElement>FriendListMyFriends=null;
	private String FriendNameInvites=null;
	private String FriendNameMyFriends=null;
	@BeforeClass
	public void startServer() throws Exception
	{
		utili.getStartServer();
	}
	@BeforeMethod
	public void loadLocater()
	{
		DOMConfigurator.configure(PropertyHandler.getProperty("Loger"));
	}
	@Test
	public void friendsTest()
	{
		Ngagedriver.Driver.findElement(By.xpath("//UIAButton[16]")).click();
		List<FriendAddNameData>friendAddNameDataList = excelDataExtractor.getFriendAddNameDataList(26);
		Log.info("FriendAddNameData:"+friendAddNameDataList.size());
		for(int i = 0; i < friendAddNameDataList.size(); i++)
		{
			utili.getWaitForElementPresent("//UIAButton[1]",60);
			Ngagedriver.Driver.findElement(By.xpath("//UIAButton[1]")).click();
			Log.info("user should click pluss buttion.");
			Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[23]")).click();
			Ngagedriver.Driver.findElement(By.name("Button 1")).click();
			//user send name in add manually pop up window.
			Log.info("FriendNAme:"+friendAddNameDataList.get(i).getFriendName());
			String ExceptedFreindName=friendAddNameDataList.get(i).getFriendName();
			String CountrtySelection=friendAddNameDataList.get(i).getCountrySelection();
			String MobileNumber=friendAddNameDataList.get(i).getPhoneNumber();
			String ConfMobileNumber=friendAddNameDataList.get(i).getConfirmPhoneNumber();
			String MailId=friendAddNameDataList.get(i).getEmailId();
			Ngagedriver.Driver.findElement(By.xpath("//UIATextField[1]")).sendKeys(ExceptedFreindName);
			//Click Country selection layer.
			Ngagedriver.Driver.findElement(By.xpath("//UIAButton[15]")).click();
			Ngagedriver.Driver.findElement(By.name(CountrtySelection)).click();
			Ngagedriver.Driver.findElement(By.xpath("//UIATextField[3]")).sendKeys(MobileNumber);
			Log.info("mobile number:"+MobileNumber);
			Ngagedriver.Driver.findElement(By.xpath("//UIATextField[5]")).sendKeys(ConfMobileNumber);
			Log.info("ConfMobileNumber:"+ConfMobileNumber);
			Ngagedriver.Driver.findElement(By.xpath("//UIATextField[6]")).sendKeys(MailId);
			Log.info("ConfMobileNumber sending complete!");
			Ngagedriver.Driver.findElement(By.name("Add")).click();
			Log.info("friend name add buttion clicked!");
			//String ConformAddingMessage=Ngagedriver.Driver.findElement(By.xpath("//UIAAlert[1]/UIAScrollView[1]/UIAStaticText[2]")).getText();
			//Log.info("ConformAddingMessage:"+ConformAddingMessage);
			utili.getWaitForNamePresent("OK",90);
			Ngagedriver.Driver.findElement(By.name("OK")).click();

			//Ngagedriver.Driver.findElement(By.name("Invites")).click();
			//Ngagedriver.Driver.findElement(By.name("Invite by SMS")).click();
			//UIATableView[1]/UIATableCell[1]/UIAStaticText[2]
			//UIATableView[1]/UIATableCell[2]/UIAStaticText[2]
			//UIAApplication[1]/UIAWindow[2]/UIATableView[1]/UIATableCell[2]/UIAStaticText[2]
			//UIAApplication[1]/UIAWindow[2]/UIATableView[1]/UIATableCell[3]/UIAStaticText[2]
			//FriendList=Ngagedriver.Driver.findElements(By.xpath("//UIATableView[1]/UIATableCell"));
			//Log.info("FriendList size:"+FriendList.size());
			//for(int k=1;k<=FriendList.size();k++)
			//{
			//	FriendNameInvites=Ngagedriver.Driver.findElement(By.xpath("//UIATableView[1]/UIATableCell["+k+"]/UIAStaticText[2]")).getText();
			//	Log.info("FriendName:"+FriendNameInvites);
			//}
			//Ngagedriver.Driver.findElement(By.name("My Friends")).click();
			//FriendListMyFriends=Ngagedriver.Driver.findElements(By.xpath("//IATableView[1]/UIATableCell"));
			//for(int j=1;j<=FriendListMyFriends.size();j++)
			//{
			//	FriendNameMyFriends=Ngagedriver.Driver.findElement(By.xpath("//UIATableView[1]/UIATableCell["+j+"]/UIAStaticText[2]")).getText();
			//	Log.info("FriendNameMyFriends:"+FriendNameMyFriends);
			//}
			
			
			
			//scroll that adding friend name list
			try
			{
				HashMap<String, String> scrollObject = new HashMap<String, String>();
				RemoteWebElement element = (RemoteWebElement)((AppiumDriver) Ngagedriver.Driver).findElement(By.name(ExceptedFreindName));
				JavascriptExecutor js = (JavascriptExecutor) Ngagedriver.Driver;
				String webElementId = ((RemoteWebElement) element).getId();
				System.out.println(webElementId);System.out.println(element);
				scrollObject.put("text", "Load more");
				scrollObject.put("element", webElementId);
				js.executeScript("mobile: scrollTo", scrollObject);
				//element.click();
				WebElement ele=Ngagedriver.Driver.findElement(By.name(ExceptedFreindName));
				TouchAction action = new TouchAction(Ngagedriver.Driver);
				action.press(ele, 10, 10).release().perform();
				Ngagedriver.Driver.findElement(By.name("View Profile")).click();
				String ActualUser=Ngagedriver.Driver.findElement(By.xpath("//UIAScrollView[1]/UIATextField[1]")).getText();
				Log.info("ActualUser:"+ActualUser);
				Assert.assertEquals(ActualUser, ExceptedFreindName);
				Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[1]")).click();
			}catch(Exception e)
			{
				Log.info("Friend Do Not Add In contact list!");
			}
			
		}

	}
	@AfterMethod
	public void validateApplication()
	{

	}
	@AfterClass
	public void closeApplication()
	{
		Ngagedriver.Driver.quit();
	}

}
