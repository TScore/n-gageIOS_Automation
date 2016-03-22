package utilites;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class NgageUtiliti 
{
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
}
