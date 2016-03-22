package utilites;
import io.appium.java_client.AppiumDriver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;








import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmentable;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dblayer.InPutExcelDataExtractor;



/*@Authore:Tapana
 *@version:1.0
 *@Descrption:in this class cover all common method or respited method.
 * 
 * */
public class BussenesCompernat extends NgageAbstract 
{
	private JavascriptExecutor js=null;
	private 	WebElement element=null;
	private static Logger Log = Logger.getLogger(BussenesCompernat.class.getName());
	private String SelectionMember=null;
	private String apkID=null;
	private List<WebElement>webList =null;
	InPutExcelDataExtractor inPutExcelDataExtractor=new InPutExcelDataExtractor();
	public void getStartServer() throws Exception 
	{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName","iOS");
		capabilities.setCapability("deviceName","iPhone 6");
		capabilities.setCapability("platform-version","9.2.1");
		capabilities.setCapability("app","com.ngageios.newios");
		capabilities.setCapability("udid","8a4499694a5ff557f8cbd34674d58141c56377c4");
		//Ngagedriver.Driver=new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
		//DesiredCapabilities capabilities = new DesiredCapabilities();
		//capabilities.setCapability("appium-version", "1.0");
		//capabilities.setCapability("platformName", "iOS");
		//capabilities.setCapability("platformVersion", "9.2.1");
		//capabilities.setCapability("deviceName", "iPhone 6");
		//capabilities.setCapability("app", "/Users/sachin/Desktop/N-gage.app");
		Ngagedriver.Driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}	
	public void getTakeScreenShot(String testName) 
	{	
		try {
			Timestamp timestamp = new Timestamp(new Date().getTime());
			String filename = "../ngageIOS/src/Execution Reports/Faield Case Screenshots/"+testName+timestamp+".jpg";
			WebDriver augmentedDriver = new Augmenter().augment(Ngagedriver.Driver);
			if (Ngagedriver.Driver.getClass().isAnnotationPresent(Augmentable.class)) 
			{
				System.out.println("Ã�ï¿½ugmentable");
				Ngagedriver.Driver = (AppiumDriver) new Augmenter().augment(Ngagedriver.Driver);
			} else
			{
				System.out.println("Non augmentable, so augmentation can be performed but does nothing");
				Ngagedriver.Driver = (AppiumDriver) new Augmenter().augment(Ngagedriver.Driver);
			}
			File scrFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(filename), true);
		} 
		catch (IOException e)
		{

		} catch (Exception e) 
		{
			Log.info("exception in taking screen shot");
		}
	}	
	public void getScreenShot(String ImageName) 
	{
		File scrFile = ((TakesScreenshot)Ngagedriver.Driver).getScreenshotAs(OutputType.FILE);
		try{
			FileUtils.copyFile(scrFile, new File("../ngageIOS/src/Execution Reports/Faield Case Screenshots/"+ImageName+".jpg"));
		} catch (Exception e) 
		{

			e.printStackTrace();
		}

	}
	public void getAlertHandel()
	{
		Alert alert = Ngagedriver.Driver.switchTo().alert();
		Log.info(alert.getText());
		alert.accept();
	}

	public void getWaitForElementPresent(String elementXpath,int time)
	{ 	
		WebDriverWait wt = new WebDriverWait(Ngagedriver.Driver,time);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath)));
	}
	public void getWaitForNamePresent(String Name,int time)
	{	
		WebDriverWait wt = new WebDriverWait(Ngagedriver.Driver, time);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.name(Name)));
	}
	public void getWaitForIdPresent(String id,int time)
	{		
		WebDriverWait wt = new WebDriverWait(Ngagedriver.Driver,time);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
	}


	public static void getClearLog(){
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(PropertyHandler.getProperty("LOGOUTPUT")));
			bw.write("");
			bw.flush();
			bw.close();
		}catch(IOException ioe){
			// You should really do something more appropriate here
			ioe.printStackTrace();
		}
	}

	public void getscroolDown(WebElement locater)
	{
		WebElement selement=locater;
		HashMap<String ,String > scrollobject=new HashMap<String ,String >();
		scrollobject.put("driection", "down");
		scrollobject.put("selement",((RemoteWebElement)selement).getId());
		js.executeScript("mobile : scroll",scrollobject);
	}

	/*@user name : Pass the user name which  is you select.
	 *@Descrption:in this method selection  
	 * 
	 * */

	/*@UserName=send parameter user name of user.
	 *@mobileNumber=send mobile number of user.
	 *@confroMobileNumber=send conform mobile number.
	 *@nation=send nation which is u selected. 
	 *@Descrption:this method use for register  of N_Gage application. 
	 * */

	/**
	 * @param UserName
	 * @param mobileNumber
	 * @param confroMobileNumber
	 * @param nation
	 */
	/**
	 * @param UserName
	 * @param mobileNumber
	 * @param confroMobileNumber
	 * @param nation
	 */
	public void getRegisation(String UserName,String mobileNumber,String nation)
	{
		//RegisterLocater registerLocater =loadObject(new RegisterLocater());
		try{
			Log.info("wait register page is display!");
			getWaitForElementPresent("//UIATextField[1]",90);
			Ngagedriver.Driver.findElement(By.xpath("//UIATextField[1]")).sendKeys(UserName);
			//registerLocater.getNameEdit().sendKeys(UserName);
			Ngagedriver.Driver.findElement(By.name("Done")).click();
			Log.info("sending name complete ");
			Ngagedriver.Driver.findElement(By.xpath("//UIAButton[1]")).click();
			Log.info("Country Selection!");
			WebElement selectionCountry=Ngagedriver.Driver.findElement(By.name(nation));
			selectionCountry.click();
			Ngagedriver.Driver.findElement(By.xpath("//UIATextField[3]")).sendKeys(mobileNumber);
			//registerLocater.getMobileNumberEddit().sendKeys(mobileNumber);
			Ngagedriver.Driver.findElement(By.name("Done")).click();
			Ngagedriver.Driver.findElement(By.name("NEXT")).click();
			getWaitForNamePresent("VERIFY",90);
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Please enter varification code : ");
			String varificationcode = null;
			try 
			{
				varificationcode = reader.readLine();
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			} 
			System.out.println("You entered : " + varificationcode);
			Ngagedriver.Driver.findElement(By.xpath("//UIATextField[1]")).click();
			Ngagedriver.Driver.findElement(By.xpath("//UIATextField[1]")).sendKeys(varificationcode);
			Ngagedriver.Driver.findElement(By.name("Done")).click();
			Ngagedriver.Driver.findElement(By.name("NEXT")).click();
			//registerLocater.getButionNext().click();
			getWaitForNamePresent("Ok", 90);
			Ngagedriver.Driver.findElement(By.name("Ok")).click();
			getWaitForNamePresent("SKIP", 90);
			Ngagedriver.Driver.findElement(By.name("SKIP")).click();
			try
			{
				getWaitForNamePresent("Ok",90);
				Ngagedriver.Driver.findElement(By.name("Ok")).click();
			}catch(Exception e)
			{
				Log.info("exception in pop pop selection!");
			}
		}
		catch(Exception data)
		{
			Log.info("--Regisation notworking properly try agine!--");
		}
	}

	public WebElement scrool(String keyword){
		HashMap scrollObject = new HashMap();
		RemoteWebElement element = (RemoteWebElement)Ngagedriver.Driver.findElement(By.xpath("//UIATableView[1]"));
		JavascriptExecutor js = (JavascriptExecutor) Ngagedriver.Driver;
		String webElementId = ((RemoteWebElement) element).getId();
		System.out.println(webElementId);
		System.out.println(element);
		scrollObject.put("text", keyword);
		scrollObject.put("element", webElementId);
		js.executeScript("mobile: scrollTo", scrollObject);
		return element;
	}

	public  void scrollToElement(AppiumDriver driver, String elementName){
		//String listID = ((RemoteWebElement) driver.findElementByAndroidUIAutomator("new UiSelector().className(\"android.widget.ListView\")")).getId();
		String listID = ((RemoteWebElement) Ngagedriver.Driver.findElement(By.className("UIATableView"))).getId();
		Log.info("listID:"+listID);
		String direction="down";

		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", direction);
		scrollObject.put("element", listID);
		scrollObject.put("text", elementName);
		driver.executeScript("mobile: scrollTo", scrollObject);
	}

	public void getScroolToText(String Scrooltext)
	{
		HashMap scrollObject = new HashMap();
		RemoteWebElement element = (RemoteWebElement)Ngagedriver.Driver.findElement(By.className("UIATableCell"));
		JavascriptExecutor js = (JavascriptExecutor)Ngagedriver.Driver;
		String webElementId = ((RemoteWebElement) element).getId();
		System.out.println(webElementId);
		System.out.println(element);
		scrollObject.put("text", Scrooltext);
		scrollObject.put("element", webElementId);
		js.executeScript("mobile: scrollTo", scrollObject);
	}
	//text message method.
	public void textMessage(String TextMessage)
	{
		getWaitForElementPresent("//UIATextView[1]",90);
		Ngagedriver.Driver.findElement(By.xpath("//UIATextView[1]")).sendKeys(TextMessage);
		Log.info("Text Message Sending to text type text!");
		Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[15]")).click();
		Ngagedriver.Driver.findElement(By.xpath("//UIAButton[6]")).click();
	}


	// camera button method
	public void cameraButtion()
	{
		try
		{
			Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[11]")).click();
			Log.info("camera button working !");
			getWaitForNamePresent("PhotoCapture",90);
			Ngagedriver.Driver.findElement(By.name("PhotoCapture")).click();
			Log.info("image sucessfuly capture!");
			getWaitForNamePresent("Use Photo",90);
			Ngagedriver.Driver.findElement(By.name("Use Photo")).click();
			getWaitForNamePresent("icon send",90);
			Ngagedriver.Driver.findElement(By.name("icon send")).click();
		}
		catch(Exception cam)
		{
			Log.info("exception in camera button");
			
		}
	}

	public void greetingButtion()
	{
		//click greeting button--
		try
		{
			getWaitForElementPresent("//UIAStaticText[12]",90);
			Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[12]")).click();
			Log.info("greeting button sucessfuly! clicked");
			getWaitForElementPresent("//UIAButton[2]",90);
			Log.info("wait still downlode button not display!");
			//click icon download,which is navigate to greeting download page.
			Ngagedriver.Driver.findElement(By.xpath("//UIAButton[2]")).click();
			Log.info("downlode button is display and user is sucess clicked!");
			try
			{
				getWaitForNamePresent("Download",30);
				//click download button.
				Ngagedriver.Driver.findElement(By.name("Download")).click();
				Log.info("greeting download sucessfully!");
				getWaitForNamePresent("Remove",90);
			}catch(Exception e)
			{
				Log.info("Allraday Downlode!");
			}
			Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[2]")).click();
			//select one of greeting.
			Ngagedriver.Driver.findElement(By.xpath("//UIAScrollView[1]/UIAScrollView[1]/UIAButton[1]")).click();
			Log.info("greeting select complete!");
			Ngagedriver.Driver.findElement(By.name("icon send")).click();
			Log.info("greeting sending working!");
		}
		catch(Exception gre)
		{
			Log.info("exception in greeting selection!");
		}
	}

	public void stickerButton()
	{
		//click sticker button-------
		try
		{
			getWaitForElementPresent("//UIAStaticText[13]",90);
			Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[13]")).click();
			getWaitForElementPresent("//UIAButton[2]",90);
			Log.info("wait still downlode button not display!");
			//click icon download,which is navigate to greeting download page.
			Ngagedriver.Driver.findElement(By.xpath("//UIAButton[2]")).click();
			Log.info("downlode button is display and user is sucess clicked!");
			try
			{
				getWaitForNamePresent("Download",30);
				//click download button.
				Ngagedriver.Driver.findElement(By.name("Download")).click();
				Log.info("greeting download sucessfully!");
				getWaitForNamePresent("Remove",90);
			}catch(Exception e)
			{
				Log.info("Allraday Downlode!");
			}
			Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[2]")).click();
			//select one of greeting.
			Ngagedriver.Driver.findElement(By.xpath("//UIAScrollView[1]/UIAScrollView[1]/UIAButton[1]")).click();
			Log.info("sticker select complete!");
			Ngagedriver.Driver.findElement(By.name("icon send")).click();
			Log.info("sticker sending working!");
		}catch(Exception e)
		{
			Log.info("exception in sticker selection!");
		}
	}

	public void getDoodlebutton()
	{
		//sending doodle+ image.
		try
		{
			getWaitForElementPresent("//UIAStaticText[15]",90);
			Log.info("wait doodle+ button!");
			Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[15]")).click();
			Log.info("doodle+ buttion clicked!");
			Ngagedriver.Driver.findElement(By.name("icon sketch")).click();
			getWaitForNamePresent("Sketch",90);
			Log.info("icon sketch button working!");
			Ngagedriver.Driver.findElement(By.xpath("//UIAButton[3]")).click();
			Log.info("color code working");
			Ngagedriver.Driver.findElement(By.name("icon ok")).click();
			Log.info("sketch button working!");
			//click text button.
			Ngagedriver.Driver.findElement(By.name("icon text")).click();
			Log.info("user click on icon text button!");
			getWaitForElementPresent("//UIATextField[1]", 90);
			Ngagedriver.Driver.findElement(By.xpath("//UIATextField[1]")).sendKeys("Nice Day!");
			Log.info("text sending complte!");
			Ngagedriver.Driver.findElement(By.xpath("//UIAButton[3]")).click();
			Log.info("click ok button!");
			Ngagedriver.Driver.findElement(By.xpath("//UIAButton[4]")).click();
			Log.info("cooler button working!");
			Ngagedriver.Driver.findElement(By.xpath("//UIAButton[3]")).click();
			Log.info("in doodle+ page Ok button working!");
			Ngagedriver.Driver.findElement(By.name("icon ok")).click();
			getWaitForNamePresent("icon send",90);
			Ngagedriver.Driver.findElement(By.name("icon send")).click();
			Log.info("Doodle image sending sucess!");
		}
		catch(Exception e)
		{
			Log.info("exception in sending doodle+");
		}
	}
	//send current location.
	public void getCurrentLocation()
	{
		try
		{
			getWaitForElementPresent("//UIAStaticText[4]",90);
			Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[4]")).click();
			Log.info("attchment button clicked!");
			//send current location.

			getWaitForElementPresent("//UIAStaticText[17]",90);	
			Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[17]")).click();	
			Log.info("user should sucessfuly click location!");
			Ngagedriver.Driver.findElement(By.xpath("//UIATableView[1]/UIATableCell[1]")).click();
		}catch(Exception e)
		{
			Log.info("exception in current location!");
		}
	}
	//send other location.
	public void getOtherLocation()
	{
		try

		{
			//send other location.
			getWaitForElementPresent("//UIAStaticText[4]",90);
			Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[4]")).click();
			Log.info("attchment button clicked!");
			getWaitForElementPresent("//UIAStaticText[18]",90);
			Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[18]")).click();
			Log.info("user is sucessfuly click other location!");
			Ngagedriver.Driver.findElement(By.xpath("//UIATableView[1]/UIATableCell[1]")).click();
			Log.info("Location sending sucess!");
		}
		catch(Exception e)
		{
			Log.info("exception in sending location!");
		}
	}
	//sending contact ...
	public void getContactButton()
	{

		getWaitForElementPresent("//UIAStaticText[4]",90);
		Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[4]")).click();
		Log.info("attchment button clicked!");

		try
		{
			getWaitForElementPresent("//UIAStaticText[19]",90);
			Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[19]")).click();
			Log.info("user should sucessfuly click contacts option!");
			Log.info("user sucessfuly search contacts!");
			Ngagedriver.Driver.findElement(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).click();


		}
		catch(Exception e)
		{
			Log.info("exception in share contact!");
		}
	}
	//share gallery image.
	public void getGalleryImage()
	{
		try
		{
			getWaitForElementPresent("//UIAStaticText[4]",90);
			Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[4]")).click();
			Log.info("attchment button clicked!");
			Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[23]")).click();
			Log.info("user should able to click gallery page!");
			Ngagedriver.Driver.findElement(By.xpath("//UIATableView[1]/UIATableCell[1]")).click();
			Log.info("Click image gallery page!");
			Ngagedriver.Driver.findElement(By.xpath("//UIACollectionView[1]/UIACollectionCell[63]")).click();
			Log.info("user is selecet image from image gallery!");
			getWaitForNamePresent("icon send",90);
			Ngagedriver.Driver.findElement(By.name("icon send")).click();
			Log.info("Image sending sucessfuly!");
		}catch(Exception e)
		{
			Log.info("exception in sending image!");
		}
	}
	//audio message sharing.
	public void getAudioMessage()
	{
		try
		{
			//audio message sharing.
			getWaitForElementPresent("//UIAStaticText[4]",90);
			Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[4]")).click();
			Log.info("attchment button clicked!");
			Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[30]")).click();
			Log.info("user should able to click audio message!");
			Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[17]")).click();
			Thread.sleep(2000);
			Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[20]")).click();
			Log.info("recording audio sucessfully!");
			Ngagedriver.Driver.findElement(By.xpath("//UIAButton[21]")).click();
			Log.info("Exception in sending audio!");
		}
		catch(Exception e)
		{
			Log.info("Audio message sending exception!");
		}
	}

	public void getVideoMessage()
	{
		try
		{
			//video sharing-----------
			getWaitForElementPresent("//UIAStaticText[4]",90);
			Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[4]")).click();
			Log.info("attchment button clicked!");
			//user should click video message.
			getWaitForElementPresent("//UIAStaticText[32]",90);
			Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[32]")).click();
			Log.info("click video button !");
			Ngagedriver.Driver.findElement(By.name("VideoCapture")).click();
			Log.info("video capture working!");
			Thread.sleep(2000);
			Ngagedriver.Driver.findElement(By.name("VideoCapture")).click();
			Ngagedriver.Driver.findElement(By.name("Use Video")).click();
			Log.info("user sucessfuly send video!");
		}
		catch(Exception e)
		{
			Log.info("Exception in video message sending!");
		}


	}
	//user should click blink button.
	public void  getBlinkButton()
	{
		//user is click button. 
		getWaitForElementPresent("//UIAButton[10]",90);
		Ngagedriver.Driver.findElement(By.xpath("//UIAButton[10]")).click();
		Log.info("sucessfuly user click below manu button");
		Ngagedriver.Driver.findElement(By.xpath("//UIAButton[9]")).click();
		Log.info("user is click blink button");
		Ngagedriver.Driver.findElement(By.xpath("//UIAButton[18]")).click();
		Log.info("user should click time of blink button!");

	}
	//user should click safe button.
	public void getSafeButton()
	{
		Ngagedriver.Driver.findElement(By.xpath("//UIAButton[10]")).click();
		Log.info("sucessfuly user click below manu button");
		Ngagedriver.Driver.findElement(By.xpath("//UIAButton[8]")).click();
	}
	public void getSafePurchingModule()
	{
		try{
			getWaitForNamePresent("Safe Chat",20);
			Ngagedriver.Driver.findElement(By.xpath("//UIAButton[18]")).click();
			Log.info("click primuam funcation pop up window!");
			Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[18]")).click();
			Log.info("click primuam funcation pop up window!");
			Ngagedriver.Driver.findElement(By.name("Safe")).click();
			Log.info("user is click safe primuam function button!");
			Ngagedriver.Driver.findElement(By.name("Purchase")).click();
			Log.info("click Purchase buttion!");
			Ngagedriver.Driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[6]/UIAAlert[1]/UIAScrollView[1]/UIACollectionView[1]/UIACollectionCell[1]")).sendKeys("Shatrupa@123");
			getWaitForNamePresent("OK",70);
			Ngagedriver.Driver.findElement(By.name("OK")).click();
			getWaitForNamePresent("Buy",70);
			Ngagedriver.Driver.findElement(By.name("Buy")).click();
			Ngagedriver.Driver.findElement(By.name("Buy")).click();
			getWaitForNamePresent("OK",70);
			Ngagedriver.Driver.findElement(By.name("OK")).click();
			Ngagedriver.Driver.findElement(By.xpath("//UIASecureTextField[1]")).sendKeys("1234");
			Ngagedriver.Driver.findElement(By.xpath("//UIASecureTextField[2]")).sendKeys("1234");
			Ngagedriver.Driver.findElement(By.xpath("//UIATextField[1]")).sendKeys("tapana.testing@outlook.com");
			Ngagedriver.Driver.findElement(By.xpath("//UIATextField[2]")).sendKeys("tapana.testing@outlook.com");
			Ngagedriver.Driver.findElement(By.name("Save")).click();
			Ngagedriver.Driver.findElement(By.name("return")).click();
			Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[11]")).click();
			Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[2]")).click();
			Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[2]")).click();
			Ngagedriver.Driver.findElement(By.xpath("//UIAStaticText[2]")).click();
			Ngagedriver.Driver.findElement(By.xpath("//UIAButton[9]")).click();
		}catch(Exception e)
		{
			Log.info("exception in primuam funcation!");
		}
	}
	//user should able to click schedule Button
	public void getScheduleButton()
	{
		try{

			//click menu button! 
			getWaitForElementPresent("//UIAApplication[1]/UIAWindow[2]/UIAButton[10]",90);
			Ngagedriver.Driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAButton[10]")).click();
			Log.info("click menu button!");
			Ngagedriver.Driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAButton[7]")).click();
			Log.info("sucessfuly click schedule button");
			//Scroll date panel.
			//UIAApplication[1]/UIAWindow[2]/UIAPicker[1]/UIAPickerWheel[1]
			//WebElement element=Ngagedriver.Driver.findElement(By.className("UIAPickerWheel"));
			//Actions action=new Actions(Ngagedriver.Driver);
			//for(int k=1;k<=10;k++)
			//{
			//action .sendKeys(Keys.UP).perform();
			//}
			Ngagedriver.Driver.findElement(By.xpath("//UIAPickerWheel[3][@value='33 minutes']")).click();
			Ngagedriver.Driver.executeScript("mobile: swipe","//UIAApplication[1]/UIAWindow[2]/UIAPicker[1]/UIAPickerWheel[2]");
			Ngagedriver.Driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAButton[19]")).click();
			Log.info("click schedule button!");
		}catch(Exception e)
		{
			e.printStackTrace();
			Log.info("exception in schedule date selection!");
		}
	}
	public void getCloseMarckting()
	{
		try
		{
			//Marketing pop up window.
			getWaitForElementPresent("//UIAImage[5]",30);
			Ngagedriver.Driver.findElement(By.xpath("//UIAImage[5]")).click();
		}catch(Exception e)
		{
			Log.info("exception in Marketing pop up!");	
		}
	}

	public void getSwipeleft()
	{
		//Swipe left-
		Ngagedriver.Driver.context("NATIVE_APP"); 
		Dimension size = Ngagedriver.Driver.manage().window().getSize(); 
		int startx = (int) (size.width * 0.8); 
		int endx = (int) (size.width * 0.20); 
		int starty = size.height / 2; 
		Ngagedriver.Driver.swipe(startx, starty, endx, starty, 1000);
	}
	public void getSwiperight()
	{
		//Swipe right-
		Ngagedriver.Driver.context("NATIVE_APP"); 
		Dimension size = Ngagedriver.Driver.manage().window().getSize(); 
		int endx = (int) (size.width * 0.8); 
		int startx = (int) (size.width * 0.20); 
		int starty = size.height / 2; 
		Ngagedriver.Driver.swipe(startx, starty, endx, starty, 1000);
	}

	public void getSearchFriend(String FriendName)
	{
		//Ngagedriver.Driver.scrollTo(FriendName);
		//String actualFriendName=Ngagedriver.Driver.findElement(By.xpath("//UIATableCell[105]")).getText();
		//Log.info("actualFriendName:"+actualFriendName);
		//Ngagedriver.Driver.findElement(By.xpath(PropertyHandler.getProperty("USERSELECTION"))).click();
		//getScroolToText(FriendName);
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		RemoteWebElement element = (RemoteWebElement)((AppiumDriver) Ngagedriver.Driver).findElement(By.xpath(FriendName));
		JavascriptExecutor js = (JavascriptExecutor) Ngagedriver.Driver;
		String webElementId = ((RemoteWebElement) element).getId();
		System.out.println(webElementId);System.out.println(element);
		scrollObject.put("text", "Load more");
		scrollObject.put("element", webElementId);
		js.executeScript("mobile: scrollTo", scrollObject);
		//element.click();
		Ngagedriver.Driver.findElement(By.xpath(FriendName)).click();
	}
	public void getSearchFriend()
	{
		getWaitForNamePresent("Start a New Conversation",10);	
		Ngagedriver.Driver.findElement(By.name("Start a New Conversation")).click();
		Log.info("user click on Start a New Conversation");
		Ngagedriver.Driver.findElement(By.xpath(PropertyHandler.getProperty("USERSELECTION"))).click();
	}
	public String getMessageValidation(String Comand)
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Please enter +"+Comand+": ");
		String Statues = null;
		try 
		{
			Statues = reader.readLine();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		System.out.println("You entered : " + Statues);
		return Statues;
	}
	public void getSelectGroup(String GroupName)
	{
		Log.info("GroupName:"+GroupName);
		getWaitForNamePresent("Group", 90);
		Ngagedriver.Driver.findElement(By.name("Group")).click();
		Log.info("group button sucessfuly clicked!");
		//user should click on create new group button.
		Ngagedriver.Driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAButton[3]")).click();
		Log.info("click search bar!");
		Ngagedriver.Driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIASearchBar[1]")).sendKeys(GroupName);
		Log.info("user is able to  search group sucessfuly!");
		Ngagedriver.Driver.findElement(By.name("Search")).click();
		
		//UIATableGroup[2]/UIAStaticText[1]
		webList=Ngagedriver.Driver.findElements(By.xpath("//UIAScrollView[2]/UIATableView[2]/UIATableGroup"));
		Log.info("webList:"+webList.size());
		for(int n=1;n<=webList.size();n++)
		{
			//UIAApplication[1]/UIAWindow[2]/UIAScrollView[2]/UIATableView[2]/UIATableGroup[1]/UIAStaticText[1]
			//UIAApplication[1]/UIAWindow[2]/UIAScrollView[2]/UIATableView[2]/UIATableGroup[2]/UIAStaticText[1]
			String ActualGroupName=	Ngagedriver.Driver.findElement(By.xpath("//UIAScrollView[2]/UIATableView[2]/UIATableGroup["+n+"]/UIAStaticText[1]")).getText();
			ActualGroupName=ActualGroupName.replaceAll("\\s+", "");
			Log.info("ActualGroupName:"+ActualGroupName);
			GroupName=GroupName.replaceAll("\\s+", "");
			Log.info("GroupName:"+GroupName);
			if(GroupName.equalsIgnoreCase(ActualGroupName))
			{

				//UIAApplication[1]/UIAWindow[2]/UIAScrollView[2]/UIATableView[2]/UIATableGroup[1]/UIAStaticText[1]
				Ngagedriver.Driver.findElement(By.xpath("//UIAScrollView[2]/UIATableView[2]/UIATableGroup["+n+"]/UIAStaticText[1]")).click();
				Log.info("click group panel!");
				//UIAApplication[1]/UIAWindow[2]/UIAScrollView[2]/UIATableView[2]/UIATableCell[2]/UIAStaticText[1]
				//UIAApplication[1]/UIAWindow[2]/UIAScrollView[2]/UIATableView[2]/UIATableCell[4]/UIAStaticText[1]
				Ngagedriver.Driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAScrollView[2]/UIATableView[2]/UIATableCell["+n+"]/UIAStaticText[2]")).click();
				Log.info("user is sucessfuly click thread of group!");
				break;
			}else
			{
				Log.info("Group Do Not Display!");
			}
		}
	}

}




