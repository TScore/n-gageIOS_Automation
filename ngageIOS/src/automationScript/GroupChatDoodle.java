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
import bean.GroupChatDoodleData;
import bean.GroupGreetingData;
import dblayer.InPutExcelDataExtractor;
import dblayer.InPutIOSTestCase;

public class GroupChatDoodle {
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
		List<GroupChatDoodleData>groupChatDoodleDataList = excelDataExtractor.getGroupChatDoodleDataList(19);
		Log.info("getResisterDataList:"+groupChatDoodleDataList.size());
		for(int i = 0; i < groupChatDoodleDataList.size(); i++)
		{
			try
			{
				//GroupName=groupTextDataList.get(i).getGroupName();
				String str = groupChatDoodleDataList.get(i).getNoOfDoodle();
				int num = Integer.parseInt(str);
				Log.info("str:"+str);
				String MessageType=groupChatDoodleDataList.get(i).getTypeOFDoodle();
				Log.info("MessageType:"+MessageType);
				for(int m=1;m<=num;m++)
				{
					try{
						if(MessageType.equalsIgnoreCase("Normal"))
						{	
							try
							{
							utili.getDoodlebutton();
							inPutIOSTestCase.setExcelStringData(0,31,2,"PASS");
							excelDataExtractor.setErrorMessage(19, i+1, 4, "PASS");
							}catch(Exception chat)
							{
								Log.info("exception in normal chat!");
								inPutIOSTestCase.setExcelStringData(0,31,2,"FAIL");
								excelDataExtractor.setErrorMessage(19, i+1, 4, "FAIL");
							}
						}else if(MessageType.equalsIgnoreCase("Blink"))
						{
							try
							{
							utili.getBlinkButton();
							utili.getDoodlebutton();
							inPutIOSTestCase.setExcelStringData(0,32,2,"PASS");
							excelDataExtractor.setErrorMessage(19, i+1, 4, "PASS");
							}catch(Exception blink)
							{
							Log.info("exception in blink message!");
							inPutIOSTestCase.setExcelStringData(0,32,2,"FAIL");
							excelDataExtractor.setErrorMessage(19, i+1, 4, "FAIL");
							}
						}
						
					}
					catch(Exception e)
					{
						Log.info("exception in sending doodle message!");
						utili.getTakeScreenShot("exception sending doodle message!");
					}
					
				}
				//utili.getTakeScreenShot("group greeting message!");
			}catch(Exception e)
			{
				Log.info("Exception in group doodle message");
				excelDataExtractor.setErrorMessage(19, i+1, 4, "FAIL");
				//inPutIOSTestCase.setExcelStringData(0,24,2,"FAIL");
				
			}
		}
		}catch(Exception e)
		{
			utili.getTakeScreenShot("Group Doodle");
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
