package automationScript;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import bean.GroupChatData;
import utilites.BussenesCompernat;
import utilites.Ngagedriver;
import utilites.PropertyHandler;
import dblayer.InPutExcelDataExtractor;
import dblayer.InPutIOSTestCase;
/*@Authore:Tapana
 *@Description:In this class user creating group and  sending text,image video,and verify all function is working or not.  
 * 
 * 
 * 
 * */
public class CreatingGroup
{
	private static Logger Log = Logger.getLogger(CreatingGroup.class.getName());
	InPutExcelDataExtractor excelDataExtractor=new InPutExcelDataExtractor();
	BussenesCompernat utili=new BussenesCompernat();
	InPutIOSTestCase inPutIOSTestCase=new InPutIOSTestCase();
	private String FriendName=null;
	private String TextMessage=null;
	public static  List<WebElement> allElements = null;	
	@BeforeClass
	public void stratServer() throws Exception
	{
		utili.getStartServer();
		//excelDataExtractor.copyData();
	}
	@BeforeMethod
	public void loadLocater()
	{
		DOMConfigurator.configure(PropertyHandler.getProperty("Loger"));
	}
	@Test
	public void creatingGroupTest() throws IOException, InvalidFormatException
	{
		try{
		//user should click group button
		utili.getWaitForNamePresent("Group", 90);
		Ngagedriver.Driver.findElement(By.name("Group")).click();
		Log.info("group button sucessfuly clicked!");
		//user should click on create new group button.
		utili.getWaitForNamePresent("Create New Group",90);
		Ngagedriver.Driver.findElement(By.name("Create New Group")).click();
		Log.info("sucessfuly user click create new group button!");
		List<GroupChatData>getGroupChatDataList = excelDataExtractor.getGroupChatDataList(2);
		Log.info("getOnetoOneChatDataList:"+getGroupChatDataList.size());
		for(int i = 0; i < getGroupChatDataList.size(); i++)
		{
			try
			{
				String FriendName=getGroupChatDataList.get(i).getGroupMember();
				Ngagedriver.Driver.findElement(By.xpath("//UIASearchBar[1]")).clear();
				Ngagedriver.Driver.findElement(By.xpath("//UIASearchBar[1]")).sendKeys(FriendName);
				Ngagedriver.Driver.findElement(By.name("Search")).click();
				allElements=Ngagedriver.Driver.findElements(By.xpath("//UIATableView[1]/UIATableCell"));
				Log.info("allElements:"+allElements.size());
				
				for(int k=1;k<=allElements.size();k++)
				{
					try
					{
						String ActualName=getGroupChatDataList.get(i).getExceptedName();
						String ExceptedName=Ngagedriver.Driver.findElement(By.xpath("//UIATableView[1]/UIATableCell["+k+"]/UIAStaticText[1]")).getText();
						excelDataExtractor.setExcelStringData(2, i+1,1,ExceptedName);
						Log.info("ExceptedName:"+ExceptedName);
						Log.info("ActualName:"+ActualName);
						ActualName=ActualName.replaceAll("\\s+", "");
						ExceptedName=ExceptedName.replaceAll("\\s+", "");
						if(ExceptedName.equalsIgnoreCase(ActualName))
						{
							Ngagedriver.Driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIATableView[1]/UIATableCell["+k+"]/UIAStaticText[1]")).click();
							excelDataExtractor.setExcelStringData(2,i+1,2,"FriendSelectionComplte");
						}
						else
						{
							Log.info("friend name not display!");
						}
					}catch(Exception e)
					{
						Log.info("Exception in select friend!");
					}
				}
				
			}
			catch(Exception e)
			{
				Log.info("exception in selection friends!");
				excelDataExtractor.setErrorMessage(2, 1, i+1,"exception in selection friends!");
			}

		}
		String GroupName=PropertyHandler.getProperty("GROUPNAME");
		Log.info("GroupName");
		Ngagedriver.Driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIATextField[1]")).sendKeys(GroupName);
		Ngagedriver.Driver.findElement(By.name("group_profile_pic.png")).click();
		Log.info("user is able to click group profile icon!");
		Ngagedriver.Driver.findElement(By.xpath(" //UIAApplication[1]/UIAWindow[2]/UIAStaticText[12]")).click();
		//Ngagedriver.Driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIATableView[1]/UIATableCell[1]")).click();
		Ngagedriver.Driver.findElement(By.name("PhotoCapture")).click();
		Log.info("user click on image button!");
		//Ngagedriver.Driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIACollectionView[1]/UIACollectionCell[30]")).click();
		Log.info("user attchment of image sucessfuly!");
		utili.getWaitForNamePresent("Use Photo",90);
		Ngagedriver.Driver.findElement(By.name("Use Photo")).click();
		utili.getWaitForElementPresent("//UIAApplication[1]/UIAWindow[2]/UIAStaticText[5]", 90);
		Ngagedriver.Driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAStaticText[5]")).click();
		Log.info("user should attchmnet image in gallery!");
		utili.getWaitForElementPresent("//UIAApplication[1]/UIAWindow[2]/UIAStaticText[3]", 90);
		Ngagedriver.Driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAStaticText[3]")).click();
		try
		{
		utili.getWaitForNamePresent(GroupName,90);
		Log.info("Group creating sucessfuly!");
		}catch(Exception e)
		{
			Log.info("group not creating");
		}
		inPutIOSTestCase.setExcelStringData(0,23,2,"PASS");
		}catch(Exception e)
		{
			utili.getTakeScreenShot("Group Creating");
			Ngagedriver.Driver.quit();
		}
	}
	@AfterMethod
	public void validateApplicationTest()
	{

	}
	@AfterClass
	public void closeApplication()
	{
		Ngagedriver.Driver.quit();
	}
}
