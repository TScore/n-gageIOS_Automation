package automationScript;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import bean.GroupTextData;
import bean.OneToOneData;
import utilites.BussenesCompernat;
import utilites.Ngagedriver;
import utilites.PropertyHandler;
import dblayer.InPutExcelDataExtractor;
import dblayer.InPutIOSTestCase;

public class GroupChating {
	private static Logger Log = Logger.getLogger(RegisterPage.class.getName());
	InPutExcelDataExtractor excelDataExtractor=new InPutExcelDataExtractor();
	InPutIOSTestCase inPutIOSTestCase=new InPutIOSTestCase();
	BussenesCompernat utili=new BussenesCompernat();
	private String GroupName=PropertyHandler.getProperty("GROUPNAME");
	@BeforeClass
	public void startApplication() throws Exception
	{
		DOMConfigurator.configure(PropertyHandler.getProperty("Loger"));
		utili.getStartServer();
		//excelDataExtractor.copyData();
		//inPutIOSTestCase.copyData();
		GroupName=PropertyHandler.getProperty("GROUPNAME");
		Log.info("GroupName:"+GroupName);
		utili.getSelectGroup(GroupName);
	}@BeforeMethod
	public void loadLoger()
	{
		
	}
	@Test
	public void GroupChatingTextTest() throws Exception
	{
		try{
		List<GroupTextData>groupTextDataList = excelDataExtractor.getGroupTextDataList(15);
		Log.info("getResisterDataList:"+groupTextDataList.size());
		for(int i = 0; i < groupTextDataList.size(); i++)
		{
			try
			{


				//GroupName=groupTextDataList.get(i).getGroupName();
				String str = groupTextDataList.get(i).getNoOfTime();
				int num = Integer.parseInt(str);
				Log.info("str:"+str);
				String MessageType=groupTextDataList.get(i).getTypeOf();
				Log.info("MessageType:"+MessageType);
				String textMessage=groupTextDataList.get(i).getTextMessage();
				Log.info("textMessage:"+textMessage);
				for(int m=1;m<=num;m++)
				{
					try{
						if(MessageType.equalsIgnoreCase("Normal"))
						{	
							try
							{
							utili.textMessage(m+"."+textMessage);
							inPutIOSTestCase.setExcelStringData(0,24,2,"PASS");
							excelDataExtractor.setErrorMessage(15, i+1, 4, "PASS");
							}catch(Exception chat)
							{
								Log.info("exception in normal chat!");
								inPutIOSTestCase.setExcelStringData(0,24,2,"FAIL");
								excelDataExtractor.setErrorMessage(15, i+1, 4, "FAIL");
							}
						}else if(MessageType.equalsIgnoreCase("Blink"))
						{
							try
							{
							utili.getBlinkButton();
							utili.textMessage(m+"."+textMessage);
							inPutIOSTestCase.setExcelStringData(0,25,2,"PASS");
							excelDataExtractor.setErrorMessage(15, i+1, 4, "PASS");
							}catch(Exception blink)
							{
							Log.info("exception in blink message!");
							inPutIOSTestCase.setExcelStringData(0,25,2,"FAIL");
							excelDataExtractor.setErrorMessage(15, i+1, 4, "FAIL");
							}
						}
						
					}
					catch(Exception e)
					{
						Log.info("exception in sending message!");
						utili.getTakeScreenShot("exception sending message!");
					}
					
				}
				//utili.getTakeScreenShot("groupTextmessage!");
			}catch(Exception e)
			{
				Log.info("Exception in group text data");
				excelDataExtractor.setErrorMessage(15, i+1, 4, "FAIL");
				//inPutIOSTestCase.setExcelStringData(0,24,2,"FAIL");
				
			}
		}
		}catch(Exception e)
		{
			utili.getTakeScreenShot("GroupText");
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
