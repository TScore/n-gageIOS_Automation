package automationScript;
import java.util.List;
import atu.testng.reports.ATUReports;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import bean.OneToOneData;
import utilites.BussenesCompernat;
import utilites.Ngagedriver;
import utilites.PropertyHandler;
import dblayer.InPutExcelDataExtractor;
import dblayer.InPutIOSTestCase;

import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class,
	MethodListener.class })
public class OneToOneText 
{
	{
		System.setProperty("atu.reporter.config", "../ngageIOS/src/atu.properties");
	}


	private static Logger Log = Logger.getLogger(OneToOneText.class.getName());
	InPutExcelDataExtractor excelDataExtractor=new InPutExcelDataExtractor();
	InPutIOSTestCase inPutIOSTestCase=new InPutIOSTestCase();
	BussenesCompernat utili=new BussenesCompernat();
	private String FriendName=null;
	@BeforeClass
	public void startApplication() throws Exception
	{
		utili.getStartServer();
		excelDataExtractor.copyData();
		inPutIOSTestCase.copyData();
	}
	@BeforeMethod
	public void loadLoger()
	{
		DOMConfigurator.configure(PropertyHandler.getProperty("Loger"));
	}
	@Test
	public void OneToOneTextTest() throws Exception
	{
		try{
			//user click on start new conversation.
			utili.getWaitForNamePresent("Start a New Conversation",10);	
			Ngagedriver.Driver.findElement(By.name("Start a New Conversation")).click();
			Log.info("user click on Start a New Conversation");
			FriendName=PropertyHandler.getProperty("FRIENDSELECTION");
			Log.info("FriendName:"+FriendName);
			utili.getSearchFriend("FriendName");
			//utili.getWaitForElementPresent("//UIAButton[3]",90);
			//String ActualFriendName=Ngagedriver.Driver.findElement(By.xpath("//UIAButton[3]")).getText();
			//Log.info("ActualFriendName:"+ActualFriendName);
			//	excelDataExtractor.setExcelStringData(5, i+1,4,ActualFriendName);
			List<OneToOneData>getOneToOneTextList = excelDataExtractor.getOneToOneTextList(5);
			Log.info("getResisterDataList:"+getOneToOneTextList.size());
			for(int i = 0; i < getOneToOneTextList.size(); i++)
			{
				try
				{
					String str = getOneToOneTextList.get(i).getNoOfText();
					int num = Integer.parseInt(str);
					Log.info("str:"+str);
					String MessageType=getOneToOneTextList.get(i).getTypeOfText();
					Log.info("MessageType:"+MessageType);
					String textMessage=getOneToOneTextList.get(i).getTextMessage();
					Log.info("textMessage:"+textMessage);
					for(int m=1;m<=num;m++)
					{
						try{
							if(MessageType.equalsIgnoreCase("Normal"))
							{	
								try
								{
									utili.textMessage(m+"."+textMessage);
									inPutIOSTestCase.setExcelStringData(0,3,2,"PASS");
								}catch(Exception chat)
								{
									Log.info("exception in normal chat!");
									inPutIOSTestCase.setExcelStringData(0,3,2,"FAIL");
								}
							}else if(MessageType.equalsIgnoreCase("Blink"))
							{
								try
								{
									utili.getBlinkButton();
									utili.textMessage(m+"."+textMessage);
									inPutIOSTestCase.setExcelStringData(0,4,2,"PASS");
								}catch(Exception blink)
								{
									Log.info("exception in blink message!");
									inPutIOSTestCase.setExcelStringData(0,4,2,"FAIL");
								}
							}

						}
						catch(Exception e)
						{
							Log.info("exception in sending message!");
							utili.getTakeScreenShot("exception sending message!");


						}

					}
					//String validation= utili.getMessageValidation("oneToOne Message sending proper?");
					//Log.info("validation:"+validation);
					//excelDataExtractor.setExcelStringData(5, i+1,5,validation);
					//	utili.getTakeScreenShot("sending message!");
					//Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[2]")).click();
				}
				catch(Exception e)
				{
					Log.info("Exception in OneToOneTextTest");
					excelDataExtractor.setExcelStringData(5, i+1,4,"sending normal message!");

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
