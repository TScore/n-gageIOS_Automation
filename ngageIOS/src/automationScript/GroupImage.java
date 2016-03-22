package automationScript;

import java.util.List;

import org.apache.http.conn.HttpHostConnectException;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utilites.BussenesCompernat;
import utilites.Ngagedriver;
import utilites.PropertyHandler;
import bean.GroupImageData;
import bean.GroupStickerData;
import dblayer.InPutExcelDataExtractor;
import dblayer.InPutIOSTestCase;

public class GroupImage 
{

	private static Logger Log = Logger.getLogger(RegisterPage.class.getName());
	InPutExcelDataExtractor excelDataExtractor=new InPutExcelDataExtractor();
	InPutIOSTestCase inPutIOSTestCase=new InPutIOSTestCase();
	BussenesCompernat utili=new BussenesCompernat();
	private String GroupName=PropertyHandler.getProperty("GROUPNAME");
	@BeforeClass
	public void startApplication() throws Exception
	{
		DOMConfigurator.configure(PropertyHandler.getProperty("Loger"));
		//utili.getStartServer();
		//excelDataExtractor.copyData();
		//inPutIOSTestCase.copyData();
		//GroupName=PropertyHandler.getProperty("GROUPNAME");
		//Log.info("GroupName:"+GroupName);
		//utili.getSelectGroup(GroupName);
	}@BeforeMethod
	public void loadLoger()
	{
		
	}
	@Test
	public void groupImageTest() throws Exception
	{
		List<GroupImageData>groupImageDatalist = excelDataExtractor.getGroupImageDataList(17);
		Log.info("getResisterDataList:"+groupImageDatalist.size());
		for(int i = 0; i < groupImageDatalist.size(); i++)
		{
			try
			{


				//GroupName=groupTextDataList.get(i).getGroupName();
				String str = groupImageDatalist.get(i).getNoOfImage();
				int num = Integer.parseInt(str);
				Log.info("str:"+str);
				String MessageType=groupImageDatalist.get(i).getTypeOFImage();
				Log.info("MessageType:"+MessageType);
				for(int m=1;m<=num;m++)
				{
					try{
						if(MessageType.equalsIgnoreCase("Normal"))
						{	
							try
							{
							utili.cameraButtion();
							inPutIOSTestCase.setExcelStringData(0,27,2,"PASS");
							excelDataExtractor.setErrorMessage(17, i+1, 4, "PASS");
							}catch(Exception chat)
							{
								Log.info("exception in normal chat!");
								inPutIOSTestCase.setExcelStringData(0,27,2,"FAIL");
								excelDataExtractor.setErrorMessage(17, i+1, 4, "FAIL");
							}
						}else if(MessageType.equalsIgnoreCase("Blink"))
						{
							try
							{
							utili.getBlinkButton();
							utili.cameraButtion();
							inPutIOSTestCase.setExcelStringData(0,28,2,"PASS");
							excelDataExtractor.setErrorMessage(17, i+1, 4, "PASS");
							}catch(Exception blink)
							{
							Log.info("exception in blink message!");
							inPutIOSTestCase.setExcelStringData(0,28,2,"FAIL");
							excelDataExtractor.setErrorMessage(17, i+1, 4, "FAIL");
							}
						}
						
					}
					catch(Exception e)
					{
						Log.info("exception in sending image message!");
						utili.getTakeScreenShot("exception sending image!");
					}
					
				}
				//utili.getTakeScreenShot("group image message!");
			}catch(UnreachableBrowserException b)
			{
				utili.getTakeScreenShot("ExceptionGroupImage");
				utili.getStartServer();
				utili.getSelectGroup(GroupName);
				
			}
			catch(Exception e)
			{
				Log.info("Exception in group text data");
				excelDataExtractor.setErrorMessage(17, i+1, 4, "FAIL");
				//inPutIOSTestCase.setExcelStringData(0,24,2,"FAIL");
				utili.getTakeScreenShot("Exception Group Image");
				Ngagedriver.Driver.quit();
				utili.getStartServer();
				utili.getSelectGroup(GroupName);
			}
		}
	}
	@BeforeMethod
	public void validateApplication()
	{
		
	}
	@AfterMethod
	public void closeApplication()
	{
		
	}
}
