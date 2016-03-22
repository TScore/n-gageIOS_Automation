package automationScript;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utilites.BussenesCompernat;
import utilites.Ngagedriver;
import utilites.PropertyHandler;
import bean.OneToOneCurrentLocationData;
import bean.OneToOneDoodleData;
import dblayer.InPutExcelDataExtractor;
import dblayer.InPutIOSTestCase;

public class OneToOneCurrentLocation {
	private static Logger Log = Logger.getLogger(OneToOneCurrentLocation.class.getName());
	InPutExcelDataExtractor excelDataExtractor=new InPutExcelDataExtractor();
	InPutIOSTestCase inPutIOSTestCase=new InPutIOSTestCase();
	BussenesCompernat utili=new BussenesCompernat();
	private String FriendName=null;
	@BeforeClass
	public void startApplication() throws Exception
	{
	//	utili.getStartServer();
		//excelDataExtractor.copyData();
		//inPutIOSTestCase.copyData();
		//utili.getSearchFriend();
	}
	@BeforeMethod
	public void loadLoger()
	{
		DOMConfigurator.configure(PropertyHandler.getProperty("Loger"));
	}
	@Test
	public void oneToOneCurrentLocationTest() throws Exception
	{
		try{

	//	try
	//	{
	//	utili.getWaitForNamePresent("Start a New Conversation",10);	
	//		Ngagedriver.Driver.findElement(By.name("Start a New Conversation")).click();
	//		FriendName=PropertyHandler.getProperty("FRIENDSELECTION");
	//		utili.getSearchFriend(FriendName);
	//		Log.info("user click on Start a New Conversation");
	//		//user click on start new conversation.
	//		Log.info("user click on Start a New Conversation");
	//	}catch(Exception e)
	//	{
	//		Log.info("All rady clicked");
	//	}
		List<OneToOneCurrentLocationData>oneToOneCurrentLocationList = excelDataExtractor.getOneToOneCurrentLocationList(10);
		Log.info("getResisterDataList:"+oneToOneCurrentLocationList.size());
		for(int i = 0; i < oneToOneCurrentLocationList.size(); i++)
		{
			try
			{
			//	FriendName=oneToOneCurrentLocationList.get(i).getFriendName();
			//	Log.info("FriendName:"+FriendName);
				
			//	utili.getWaitForElementPresent("//UIAButton[3]",90);
			//	String ActualFriendName=Ngagedriver.Driver.findElement(By.xpath("//UIAButton[3]")).getText();
			//	Log.info("ActualFriendName:"+ActualFriendName);
			//	excelDataExtractor.setExcelStringData(10, i+1,4,ActualFriendName);
				String str = oneToOneCurrentLocationList.get(i).getNoOfCurrentLocation();
				Log.info("str:"+str);
				int num = Integer.parseInt(str);
				Log.info("str:"+str);
				String MessageType=oneToOneCurrentLocationList.get(i).getTypeOfCurrentLOcation();
				Log.info("MessageType:"+MessageType);
				for(int m=1;m<=num;m++)
				{
					try{
						if(MessageType.equalsIgnoreCase("Normal"))
						{	
							try
							{
								utili.getCurrentLocation();
								Log.info("CurrentLocation message complete");
								inPutIOSTestCase.setExcelStringData(0,13,2,"PASS");
							}catch(Exception chat)
							{
								Log.info("exception in normal chat!");
								inPutIOSTestCase.setExcelStringData(0,13,2,"FAIL");
							}
						}else if(MessageType.equalsIgnoreCase("Blink"))
						{
							try
							{
								utili.getBlinkButton();
								utili.getCurrentLocation();
								inPutIOSTestCase.setExcelStringData(0,14,2,"PASS");
							}catch(Exception blink)
							{
								Log.info("exception in blink message!");
								inPutIOSTestCase.setExcelStringData(0,14,2,"FAIL");
							}
						}

					}
					catch(Exception e)
					{
						Log.info("exception in sending image!");
						utili.getTakeScreenShot("exception sending CurrentLocation!");


					}

				}
				//String validation= utili.getMessageValidation("Current Location  sending proper or Not?");
				//Log.info("validation:"+validation);
				//excelDataExtractor.setExcelStringData(10, i+1,4,validation);
			//	utili.getTakeScreenShot("CurrentLocation");
		//		Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[2]")).click();
			}
			catch(Exception e)
			{
				Log.info("Exception in OneToOneTextTest");
				utili.getTakeScreenShot("Fail In SearchFriend!");
				excelDataExtractor.setExcelStringData(10, i+1,4,"Fail In SearchFriend!");
				
			}
		}
		}catch(Exception e)
		{
			Ngagedriver.Driver.quit();
			utili.getStartServer();
			utili.getSearchFriend();
		}
	}
	@AfterMethod
	public void validateTest()
	{

	}
	@AfterClass
	public void closeApplication()
	{

	}
}
