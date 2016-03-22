package automationScript;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utilites.BussenesCompernat;
import utilites.Ngagedriver;
import utilites.PropertyHandler;
import dblayer.InPutExcelDataExtractor;
import dblayer.InPutIOSTestCase;

public class Option {
	private static Logger Log = Logger.getLogger(RegisterPage.class.getName());
	InPutExcelDataExtractor excelDataExtractor=new InPutExcelDataExtractor();
	InPutIOSTestCase inPutIOSTestCase=new InPutIOSTestCase();
	BussenesCompernat utili=new BussenesCompernat();
	private List<WebElement>FaqList=null;
	@BeforeClass
	public void startServer() throws Exception
	{
		DOMConfigurator.configure(PropertyHandler.getProperty("Loger"));
		utili.getStartServer();

	}
	@BeforeMethod
	public void loadLocater()
	{

	}
	@Test(enabled=false)
	public void aboutTest()
	{
		//click option button.
		Ngagedriver.Driver.findElement(By.xpath("//UIAButton[17]")).click();
		Log.info("click on option button!");
		utili.getWaitForNamePresent("About", 90);
		Ngagedriver.Driver.findElement(By.name("About")).click();
		utili.getWaitForElementPresent("//UIATableView[2]/UIATableCell[1]/UIAStaticText[2]", 90);
		String NgageVersion=Ngagedriver.Driver.findElement(By.xpath("//UIATableView[2]/UIATableCell[1]/UIAStaticText[2]")).getText();
		Log.info("NgageVersion:"+NgageVersion);
		//Ngagedriver.Driver.findElement(By.xpath("//UIATableView[2]/UIATableCell[2]/UIAStaticText[1]")).getText();
		//Log.info("Last Update!");
		Ngagedriver.Driver.findElement(By.xpath("//UIATableView[2]/UIATableCell[3]/UIAStaticText[1]")).click();
		Log.info("1.click terms of service!");
		Ngagedriver.Driver.findElement(By.name("OK")).click();
		Log.info("2.click ok buttion!");
		Ngagedriver.Driver.findElement(By.xpath("//UIATableView[2]/UIATableCell[4]")).click();
		Log.info("3.click Privacy Policy!");
		Ngagedriver.Driver.findElement(By.xpath("//UIAButton[3]")).click();
		Log.info("4.click ok buttion!");
		//Ngagedriver.Driver.findElement(By.xpath("//UIATableView[2]/UIATableCell[5]")).click();
		//Log.info("5.Third Party Attribution notices!");
		//utili.getWaitForNamePresent("OK", 90);
		//Ngagedriver.Driver.findElement(By.name("OK")).click();
		//Log.info("4.click ok buttion!");
		//Ngagedriver.Driver.findElement(By.xpath("//UIATableView[2]/UIATableCell[6]")).click();
		//Log.info("Acceptable and fair use policy");
		//Ngagedriver.Driver.findElement(By.name("OK")).click();
		//Log.info("4.click ok buttion!");
		//Ngagedriver.Driver.findElement(By.xpath("//UIATableView[2]/UIATableCell[7]")).click();
		//Log.info("click cookies policy");
		//Ngagedriver.Driver.findElement(By.name("OK")).click();
		Ngagedriver.Driver.findElement(By.xpath("//UIATableView[2]/UIATableCell[8]")).click();
		Log.info("click contact us option!");
		Ngagedriver.Driver.findElement(By.xpath("//UIATextView[1]")).sendKeys("Nice application!");
		Log.info("sending application ");
		Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[8]")).click();
		Log.info("Click save bution");
		//Ngagedriver.Driver.findElement(By.name("OK")).click();
		//Log.info("click Ok Buttion!");
		Ngagedriver.Driver.findElement(By.xpath("//UIATableView[2]/UIATableCell[9]")).click();
		FaqList=Ngagedriver.Driver.findElements(By.xpath("//UIATableView[1]/UIATableGroup"));
		for(int m=1;m<=FaqList.size();m++)
		{
			try
			{
				Ngagedriver.Driver.findElement(By.xpath("//UIATableView[1]/UIATableGroup["+m+"]/UIAStaticText[1]")).click();
				Log.info("click FAQ list!");
			}catch(Exception e)
			{
				Log.info("exception click faq buttion");
			}
		}
		Ngagedriver.Driver.findElement(By.xpath("//UIAButton[1]")).click();
		Log.info("click beck buttion!");
		//Ngagedriver.Driver.findElement(By.xpath("//UIATableView[2]/UIATableCell[10]")).click();
		//Log.info("click videos link!");
		//Ngagedriver.Driver.findElement(By.xpath("//UIAScrollView[1]/UIAWebView[1]/UIAStaticText[4]")).click();
		Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[4]")).click();
	}
	@Test
	public void myProfileTest()
	{
		//click option button.
		Ngagedriver.Driver.findElement(By.xpath("//UIAButton[17]")).click();
		//click My profile button
		Ngagedriver.Driver.findElement(By.xpath("//UIATableView[1]/UIATableCell[2]/UIAStaticText[1]")).click();
		Ngagedriver.Driver.findElement(By.xpath("//UIAScrollView[1]/UIAImage[2]/UIAStaticText[1]")).click();
		Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[7]")).click();
		//Log.info("Click profile eddit button sucessfuly!");
		//Ngagedriver.Driver.findElement(By.xpath("//UIATableView[1]/UIATableCell[3]")).click();
		//Log.info("click fav image!");
		//Ngagedriver.Driver.findElement(By.xpath("//UIACollectionView[1]/UIACollectionCell[1]")).click();
		//Log.info("click choose buttion!");
		//Ngagedriver.Driver.findElement(By.name("Choose")).click();
		Ngagedriver.Driver.findElement(By.xpath("//UIAButton[1]")).click();
		Log.info("click image roted button!");
		Ngagedriver.Driver.findElement(By.name("PhotoCapture")).click();
		Log.info("capture photo");
		Ngagedriver.Driver.findElement(By.name("Use Photo")).click();
		Log.info("user click on use photo");
		Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[5]")).click();
		String nameOfUser=Ngagedriver.Driver.findElement(By.xpath("//UIAScrollView[1]/UIATextField[1]")).getText();
		Log.info("nameOfUser:"+nameOfUser);		
		Ngagedriver.Driver.findElement(By.xpath("//UIAScrollView[1]/UIAStaticText[3]")).click();
		Log.info("click on eddit button!");
		Ngagedriver.Driver.findElement(By.xpath("//UIAScrollView[1]/UIATextField[1]")).clear();
		Log.info("clear main name!");
		Ngagedriver.Driver.findElement(By.xpath("//UIAScrollView[1]/UIATextField[1]")).sendKeys(nameOfUser);
		Log.info("sending text message complete!");
		Ngagedriver.Driver.findElement(By.xpath("//UIAScrollView[1]/UIAStaticText[3]")).click();
		Log.info("click save button!");
		Assert.assertEquals(nameOfUser,nameOfUser);
		Ngagedriver.Driver.findElement(By.xpath("//UIAScrollView[1]/UIAStaticText[6]")).click();
		Ngagedriver.Driver.findElement(By.xpath("//UIATextField[1]")).sendKeys("Bussy!");
		Log.info("display bussy!");
		Ngagedriver.Driver.findElement(By.name("Return")).click();
		Log.info("click save buttion!");
		Ngagedriver.Driver.findElement(By.name("header save icon")).click();
		Log.info("click save icon buttion!");
		Ngagedriver.Driver.findElement(By.xpath("//UIAScrollView[1]/UIAStaticText[6]")).click();
		String exceptedValue=Ngagedriver.Driver.findElement(By.xpath("//UIAScrollView[1]/UIATextField[2]")).getText();
		Assert.assertEquals(exceptedValue,"Bussy!");
		Ngagedriver.Driver.findElement(By.xpath("//UIAScrollView[1]/UIAStaticText[9]")).click();
		Ngagedriver.Driver.findElement(By.xpath("//UIAScrollView[1]/UIATextField[3]")).clear();
		Ngagedriver.Driver.findElement(By.xpath("//UIAScrollView[1]/UIATextField[3]")).sendKeys("tapana.testing@outlook.com");
		Ngagedriver.Driver.findElement(By.xpath("//UIAScrollView[1]/UIATextView[1]")).sendKeys("14b/51,mumbie anderi(e)");
		Ngagedriver.Driver.findElement(By.xpath("//UIAScrollView[1]/UIATextField[4]")).click();
		Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[8]")).click();
		Ngagedriver.Driver.findElement(By.xpath("//UIAScrollView[1]/UIAStaticText[9]")).click();
		String userData=Ngagedriver.Driver.findElement(By.xpath("//UIAScrollView[1]/UIATextField[3]")).getText();
		Log.info("userData:"+userData);
		Assert.assertEquals(userData,"Tapana.testing@outlook.com");
		Ngagedriver.Driver.findElement(By.xpath("//UIAButton[1]")).click();
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
