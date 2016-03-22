package automationScript;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utilites.BussenesCompernat;
import utilites.Ngagedriver;
import utilites.PropertyHandler;
import bean.GroupGreetingData;
import bean.GroupImageData;
import dblayer.InPutExcelDataExtractor;
import dblayer.InPutIOSTestCase;

public class GroupGreeting {
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
		try{
		List<GroupGreetingData>groupGreetingDataList = excelDataExtractor.getGroupGreetingDataList(18);
		Log.info("getResisterDataList:"+groupGreetingDataList.size());
		for(int i = 0; i <= groupGreetingDataList.size(); i++)
		{
			try
			{


				//GroupName=groupTextDataList.get(i).getGroupName();
				String str = groupGreetingDataList.get(i).getNoOfGreeting();
				int num = Integer.parseInt(str);
				Log.info("str:"+str);
				String MessageType=groupGreetingDataList.get(i).getTypeOFGreeting();
				Log.info("MessageType:"+MessageType);
				for(int m=1;m<=num;m++)
				{
					try{
						if(MessageType.equalsIgnoreCase("Normal"))
						{	
							try
							{
							utili.greetingButtion();
							inPutIOSTestCase.setExcelStringData(0,29,2,"PASS");
							excelDataExtractor.setErrorMessage(18, i+1, 4, "PASS");
							}catch(Exception chat)
							{
								Log.info("exception in normal chat!");
								inPutIOSTestCase.setExcelStringData(0,29,2,"FAIL");
								excelDataExtractor.setErrorMessage(18, i+1, 4, "FAIL");
							}
						}else if(MessageType.equalsIgnoreCase("Blink"))
						{
							try
							{
							utili.getBlinkButton();
							utili.greetingButtion();
							inPutIOSTestCase.setExcelStringData(0,30,2,"PASS");
							excelDataExtractor.setErrorMessage(18, i+1, 4, "PASS");
							}catch(Exception blink)
							{
							Log.info("exception in blink message!");
							inPutIOSTestCase.setExcelStringData(0,30,2,"FAIL");
							excelDataExtractor.setErrorMessage(18, i+1, 4, "FAIL");
							}
						}
						
					}
					catch(Exception e)
					{
						Log.info("exception in sending image message!");
						utili.getTakeScreenShot("exception sending image!");
					}
					
				}
				//utili.getTakeScreenShot("group greeting message!");
			}catch(Exception e)
			{
				Log.info("Exception in group text data");
				excelDataExtractor.setErrorMessage(18, i+1, 4, "FAIL");
				//inPutIOSTestCase.setExcelStringData(0,24,2,"FAIL");
			}
		}
		}catch(Exception e)
		{
			
			utili.getTakeScreenShot("Group greeting");
			Ngagedriver.Driver.quit();
			utili.getStartServer();
			utili.getSelectGroup(GroupName);
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
