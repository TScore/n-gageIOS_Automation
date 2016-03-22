package automationScript;
import java.util.List;
import java.util.logging.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import bean.RegisterData;
import dblayer.InPutExcelDataExtractor;
import dblayer.InPutIOSTestCase;
import utilites.BussenesCompernat;
import utilites.NgageAbstract;
import utilites.Ngagedriver;
import utilites.PropertyHandler;

/*@Authore:Tapana
 *@Date:22Fab 2016
 *@Description:User should register application and validate register user is correct or not.
 *
 *
 ***/
public class RegisterPage extends NgageAbstract
{
	private static Logger Log = Logger.getLogger(RegisterPage.class.getName());
	InPutExcelDataExtractor excelDataExtractor=new InPutExcelDataExtractor();
	InPutIOSTestCase inPutIOSTestCase=new InPutIOSTestCase();
	BussenesCompernat utili=new BussenesCompernat();
	
	@BeforeClass
	public void openApplication() throws Exception
	{

		utili.getStartServer();
		excelDataExtractor.copyData();
		inPutIOSTestCase.copyData();
	}
	@BeforeMethod
	public void loadLocater()
	{
		//Load locater of register.

		//registerLocater =loadObject(new RegisterLocater());
		DOMConfigurator.configure(PropertyHandler.getProperty("Loger"));
	}
	@Test
	public void startregisation()
	{
		//click continue button.
		
		try
		{
			utili.getWaitForNamePresent("I agree and continue",30);
			//utili.getWaitForElementPresent("//UIAApplication[1]/UIAWindow[2]/UIAButton[5]", 90);
			//registerLocater.getAgreeAndContinueButtion().click();
			Ngagedriver.Driver.findElement(By.name("I agree and continue")).click();
		}catch(Exception  e)
		{
			
			Log.info("exception in agree and continue buttion!");
		}
		Log.info("Click Continue Buttion!");
		List<RegisterData>getResisterDataList = excelDataExtractor.getResisterDataList(0);
		Log.info("getResisterDataList:"+getResisterDataList.size());
		for(int i = 0; i < getResisterDataList.size(); i++)
		{
			try
			{
				String UserName=getResisterDataList.get(i).getUserName();
				Log.info("UserName:"+UserName);
				String mobileNumber=getResisterDataList.get(i).getMobileNumber();
				Log.info("mobileNumber:"+mobileNumber);
				String nation=getResisterDataList.get(i).getCountryName();
				Log.info("nation:"+nation);
				utili.getRegisation(UserName, mobileNumber, nation);
				try
				{
				utili.getWaitForNamePresent("My Friends", 90);
				inPutIOSTestCase.setExcelStringData(0,1,2,"PASS");
				//inPutIOSTestCase.setExcelStringData(0,8,2,"PASS");
				}
				catch(Exception e)
				{
				Log.info("My Friends list do not display");
				inPutIOSTestCase.setErrorMessage(0,1,2,"FAIL");
				//inPutIOSTestCase.setErrorMessage(0,8,2,"FAIL");
				}
			}
			catch(Exception e)
			{
				Log.info("exception in regisation page !");
			}
		}
	}
	@AfterMethod
	public void validateApplication()
	{

	}
	@AfterClass
	public void closeApplication()
	{
		Ngagedriver.Driver.quit();
	}


}
