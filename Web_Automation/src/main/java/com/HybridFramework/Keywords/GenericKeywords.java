package com.HybridFramework.Keywords;

import java.io.File;

import java.lang.Object;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.HybridFramework.Util.ExtentManager;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.utils.FileUtil;
import java.net.*;



public class GenericKeywords {

	ExtentTest extT;
	WebDriver driver;
	Properties prop;
	public GenericKeywords(ExtentTest extT)
	{
		this.extT=extT;
	}
	
	public void openBrowser(String data)
	{
	
		extT.log(Status.INFO, "Navigating to URL"+data);
		if(prop.getProperty("gridRun").equals("N"))
		{
			if(data.equalsIgnoreCase("Firefox"))
			{
				driver=new FirefoxDriver();
			}
			else if(data.equalsIgnoreCase("Chrome"))
			{
				driver=new ChromeDriver();
			}
			else if(data.equalsIgnoreCase("IE"))
			{
				driver=new InternetExplorerDriver();
			}
		}
		else
		{
			//code run on grid
			DesiredCapabilities cap = null;
			if(data.equals("Mozilla"))
			{
				cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				cap.setJavascriptEnabled(true);
				cap.setPlatform(Platform.WINDOWS);
			}
			else if(data.equals("Chrome"))
			{
				cap = DesiredCapabilities.chrome();
				cap.setBrowserName("chrome");
				cap.setJavascriptEnabled(true);
				cap.setPlatform(Platform.WINDOWS);
			}
			else if(data.equals("IE"))
			{
				cap = DesiredCapabilities.internetExplorer();
				cap.setBrowserName("iexplore");
				cap.setJavascriptEnabled(true);
				cap.setPlatform(Platform.WINDOWS);
			}
			
			try
			{
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
			} 
			catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	}
	
	public void navigate(String object)
	{
		//driver.get(object);
		//String propp=prop.getProperty(object);
		//System.out.println(propp);
		extT.log(Status.INFO, "URL Navigation"+object);
		driver.get(prop.getProperty(object));
		
	}
		
	public void type(String object, String data)
	{
		//getObject(object).sendKeys(data);
		extT.log(Status.INFO, "Enter the value"+object+"   "+data);
		getObject(object).sendKeys(data);
		
	}
	
	public void click(String object)
	{
		//getObject(object).click();
		extT.log(Status.INFO, "Clicking on button "+object);
		getObject(object).click();
		
	}
	public void quit()
	{
		if(driver!=null)
			driver.quit();
			
	}
	
	
	public WebElement getObject(String object)
	{
		WebElement e=null;
		
		try
		{
			//e=driver.findElement(By.xpath(object));
			if(object.endsWith("CSS"))
			{
				e=driver.findElement(By.cssSelector(prop.getProperty(object)));
			}
			else if(object.endsWith("id"))
			{
				e=driver.findElement(By.id(prop.getProperty(object)));
			}
			else if(object.endsWith("Xpath"))
			{
				e=driver.findElement(By.xpath(prop.getProperty(object)));
			}
		}
		catch(Exception ecp)
		{
			//Report the error
			reportFailure("COuldnot extract the Object "+object+"Failure -"+ecp.getMessage());
			ecp.printStackTrace();
		}
		return e;
		
	}
	
	//for a failure message
	public void reportFailure(String FailureMsg) {
		
		//Fail in the Extent Report
		extT.log(Status.FAIL,FailureMsg );
		
		//Add a screen shot
		takeScreenShot();
		
		//Fail in TestNG
		Assert.fail("TestNG fail");
	}
	
	public void clear(String object)
	{
		extT.log(Status.INFO, "Clear text box "+object);
		getObject(object).clear();
	}
	//To take a screenshot
	public void takeScreenShot()
	{
		//File Name of the screenshot
		Date d = new Date();
		String screenShotFile=d.toString().replace(":", "_")+".png";
		
		//Take screenshot
		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try
		{
			//get the dynamic folder name
			//FileUtils.
			FileUtils.copyFile(srcFile, new File(ExtentManager.screenshotFolderPath+screenShotFile));
			//put the screenshot file in reports
			extT.log(Status.INFO, "ScreenShot--->"+extT.addScreenCaptureFromPath(ExtentManager.screenshotFolderPath+screenShotFile));
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	
}
