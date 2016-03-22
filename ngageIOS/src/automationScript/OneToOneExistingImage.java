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
import bean.OneToOneExistingImageData;
import bean.OneToOneOtherLocationData;
import dblayer.InPutExcelDataExtractor;
import dblayer.InPutIOSTestCase;

public class OneToOneExistingImage {
	private static Logger Log = Logger.getLogger(OneToOneCurrentLocation.class.getName());
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
		//utili.getSearchFriend();
	}
	@BeforeMethod
	public void loadLoger()
	{
		DOMConfigurator.configure(PropertyHandler.getProperty("Loger"));
	}
	@Test
	public void oneToOneExistingImageTest() throws Exception
	{
		try{
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
			List<OneToOneExistingImageData>oneToOneExistingImageDataList = excelDataExtractor.getOneToOneExistingImageDataList(12);
			Log.info("getResisterDataList:"+oneToOneExistingImageDataList.size());
			for(int i = 0; i < oneToOneExistingImageDataList.size(); i++)
			{
				try
				{
					//		FriendName=PropertyHandler.getProperty("FRIENDSELECTION");
					//		Log.info("FriendName:"+FriendName);
					//		utili.getSearchFriend(FriendName);
					//		utili.getWaitForElementPresent("//UIAButton[3]",90);
					//		String ActualFriendName=Ngagedriver.Driver.findElement(By.xpath("//UIAButton[3]")).getText();
					//		Log.info("ActualFriendName:"+ActualFriendName);
					//		excelDataExtractor.setExcelStringData(11, i+1,4,ActualFriendName);
					String str = oneToOneExistingImageDataList.get(i).getNoOfExistingImage();
					int num = Integer.parseInt(str);
					Log.info("str:"+str);
					String MessageType=oneToOneExistingImageDataList.get(i).getTypeOfExistingImage();
					Log.info("MessageType:"+MessageType);
					for(int m=1;m<=num;m++)
					{
						try{
							if(MessageType.equalsIgnoreCase("Normal"))
							{	
								try
								{
									utili.getGalleryImage();
									Log.info("GalleryImage message complete");
									inPutIOSTestCase.setExcelStringData(0,17,2,"PASS");
								}catch(Exception chat)
								{
									Log.info("exception in normal chat!");
									inPutIOSTestCase.setExcelStringData(0,17,2,"FAIL");
								}
							}else if(MessageType.equalsIgnoreCase("Blink"))
							{
								try
								{
									utili.getBlinkButton();
									utili.getGalleryImage();
									inPutIOSTestCase.setExcelStringData(0,18,2,"PASS");
								}catch(Exception blink)
								{
									Log.info("exception in blink message!");
									inPutIOSTestCase.setExcelStringData(0,18,2,"FAIL");
								}
							}

						}
						catch(Exception e)
						{
							Log.info("exception in sending galleryImage!");
							utili.getTakeScreenShot("exception sending galleryImage!");


						}

					}
					//String validation= utili.getMessageValidation("galleryImage  sending proper or Not?");
					//Log.info("validation:"+validation);
					//excelDataExtractor.setExcelStringData(12, i+1,4,validation);
					//	utili.getTakeScreenShot("galleryImage sending!");
					//		Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[2]")).click();
				}
				catch(Exception e)
				{
					Log.info("Exception in OneToOneTextTest");
					utili.getTakeScreenShot("Fail In SearchFriend!");
					excelDataExtractor.setExcelStringData(12, i+1,4,"Fail In SearchFriend!");

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
