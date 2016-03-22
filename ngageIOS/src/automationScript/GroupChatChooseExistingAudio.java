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
import bean.GroupChatChooseExistingAudioData;
import bean.GroupChatVideoRecordingData;
import dblayer.InPutExcelDataExtractor;
import dblayer.InPutIOSTestCase;

public class GroupChatChooseExistingAudio {




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
	public void groupChatChooseExistingAudioTest() throws Exception
	{
		try
		{
		List<GroupChatChooseExistingAudioData>groupChatChooseExistingAudioDataList= excelDataExtractor.getGroupChatChooseExistingAudioDataList(24);
		Log.info("getResisterDataList:"+groupChatChooseExistingAudioDataList.size());
		for(int i = 0; i < groupChatChooseExistingAudioDataList.size(); i++)
		{
			try
			{
				//GroupName=groupTextDataList.get(i).getGroupName();
				String str = groupChatChooseExistingAudioDataList.get(i).getNoOFAudioRecording();
				int num = Integer.parseInt(str);
				Log.info("str:"+str);
				String MessageType=groupChatChooseExistingAudioDataList.get(i).getTypeOFAudioRecording();
				Log.info("MessageType:"+MessageType);
				for(int m=1;m<=num;m++)
				{
					try{
						if(MessageType.equalsIgnoreCase("Normal"))
						{	
							try
							{
								utili.getAudioMessage();
								inPutIOSTestCase.setExcelStringData(0,41,2,"PASS");
								excelDataExtractor.setErrorMessage(24, i+1, 4, "PASS");
							}catch(Exception chat)
							{
								Log.info("exception in normal chat!");
								inPutIOSTestCase.setExcelStringData(0,41,2,"FAIL");
								excelDataExtractor.setErrorMessage(24, i+1, 4, "FAIL");
							}
						}else if(MessageType.equalsIgnoreCase("Blink"))
						{
							try
							{
								utili.getBlinkButton();
								utili.getAudioMessage();
								inPutIOSTestCase.setExcelStringData(0,42,2,"PASS");
								excelDataExtractor.setErrorMessage(24, i+1, 4, "PASS");
							}catch(Exception blink)
							{
								Log.info("exception in blink message!");
								inPutIOSTestCase.setExcelStringData(0,42,2,"FAIL");
								excelDataExtractor.setErrorMessage(24, i+1, 4, "FAIL");
							}
						}

					}
					catch(Exception e)
					{
						Log.info("exception in sending video recording message!");
						//utili.getTakeScreenShot("exception sending video recording message!");
					}

				}
				utili.getTakeScreenShot("group video recording  message!");
			}catch(Exception e)
			{
				Log.info("exception sending vedio recording");
				excelDataExtractor.setErrorMessage(24, i+1, 4, "FAIL");
				
			}
		}
		}catch(Exception e)
		{
			//inPutIOSTestCase.setExcelStringData(0,24,2,"FAIL");
			utili.getTakeScreenShot("video Recording");
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
