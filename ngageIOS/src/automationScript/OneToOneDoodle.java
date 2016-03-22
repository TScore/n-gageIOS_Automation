package automationScript;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utilites.BussenesCompernat;
import utilites.Ngagedriver;
import utilites.PropertyHandler;
import bean.OneToOneDoodleData;
import dblayer.InPutExcelDataExtractor;
import dblayer.InPutIOSTestCase;

public class OneToOneDoodle {
	private static Logger Log = Logger.getLogger(RegisterPage.class.getName());
	InPutExcelDataExtractor excelDataExtractor=new InPutExcelDataExtractor();
	InPutIOSTestCase inPutIOSTestCase=new InPutIOSTestCase();
	BussenesCompernat utili=new BussenesCompernat();
	private String FriendName=null;
	@BeforeClass
	public void startApplication() throws Exception
	{
		//utili.getStartServer();
		//excelDataExtractor.copyData();
		//inPutIOSTestCase.copyData();
	}
	@BeforeMethod
	public void loadLoger()
	{
		DOMConfigurator.configure(PropertyHandler.getProperty("Loger"));
	}
	@Test
	public void OneToOneDoodleTest() throws Exception
	{
		try
		{
	//	try
	//	{
	//		utili.getWaitForNamePresent("Start a New Conversation",10);	
	//		Ngagedriver.Driver.findElement(By.name("Start a New Conversation")).click();
	//		Log.info("user click on Start a New Conversation");
	//		//user click on start new conversation.
	//		Log.info("user click on Start a New Conversation");
	//	}catch(Exception e)
	//	{
	//		Log.info("All rady clicked");
	//	}
		List<OneToOneDoodleData>oneToOneDoodleDataList = excelDataExtractor.getOneToOneDoodleDataList(7);
		Log.info("getResisterDataList:"+oneToOneDoodleDataList.size());
		for(int i = 0; i < oneToOneDoodleDataList.size(); i++)
		{
			try
			{
			//	FriendName=oneToOneDoodleDataList.get(i).getFriendName();
			//	Log.info("FriendName:"+FriendName);
			//	utili.getSearchFriend(FriendName);
			//	utili.getWaitForElementPresent("//UIAButton[3]",90);
			//	String ActualFriendName=Ngagedriver.Driver.findElement(By.xpath("//UIAButton[3]")).getText();
			//	Log.info("ActualFriendName:"+ActualFriendName);
			//	excelDataExtractor.setExcelStringData(9, i+1,4,ActualFriendName);
				String str = oneToOneDoodleDataList.get(i).getNoOfDoodl();
				int num = Integer.parseInt(str);
				Log.info("str:"+str);
				String MessageType=oneToOneDoodleDataList.get(i).getTypeOFDoodl();
				Log.info("MessageType:"+MessageType);
				for(int m=1;m<=num;m++)
				{
					try{
						if(MessageType.equalsIgnoreCase("Normal"))
						{	
							try
							{
								utili.getDoodlebutton();
								Log.info("Doodle message complete");
								inPutIOSTestCase.setExcelStringData(0,11,2,"PASS");
							}catch(Exception chat)
							{
								Log.info("exception in normal chat!");
								inPutIOSTestCase.setExcelStringData(0,11,2,"FAIL");
							}
						}else if(MessageType.equalsIgnoreCase("Blink"))
						{
							try
							{
								utili.getBlinkButton();
								utili.getDoodlebutton();
								inPutIOSTestCase.setExcelStringData(0,12,2,"PASS");
							}catch(Exception blink)
							{
								Log.info("exception in blink message!");
								inPutIOSTestCase.setExcelStringData(0,12,2,"FAIL");
							}
						}

					}
					catch(Exception e)
					{
						Log.info("exception in sending image!");
						utili.getTakeScreenShot("exception Doodle Message!");


					}

				}
				//String validation= utili.getMessageValidation("Doodle  sending proper or Not?");
			//	Log.info("validation:"+validation);
			//	excelDataExtractor.setExcelStringData(9, i+1,4,validation);
		//		utili.getTakeScreenShot("DoodleS");
	//			Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[2]")).click();
			}
			catch(Exception e)
			{
				Log.info("Exception in OneToOneTextTest");
				excelDataExtractor.setExcelStringData(9, i+1,4,"Fail In SearchFriend!");
			}
		}
		}catch(Exception e)
		{
			utili.getTakeScreenShot("Fail In SearchFriend!");
			
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
