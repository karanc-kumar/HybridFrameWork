package com.HybridFramework.Keywords;

//import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
//import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class ValidationKeywords extends GenericKeywords {
	
	//ValidationKeywords vdk=new ValidationKeywords();
	
	public ValidationKeywords(ExtentTest extT) {
		super(extT);
		this.extT=extT;
		// TODO Auto-generated constructor stub
	}

	public void verifyTitle(String object, String data)
	{
		//String title=driver.findElement(By.xpath(object)).getText();
		//String title=getObject(object).getText();
		String title=getObject(object).getText();
		System.out.println(title);
		if(title.equalsIgnoreCase(data))
		{
			System.out.println("Matched : found the text"+data);
		}
		else
		{
			System.out.println("Unmacthed : Text not found"+data);
		}
		
	}
	
	public void verifyElementPresent(String object)
	{
		System.out.println(prop.getProperty(object));
		//driver.findElement(By.xpath(object));
		/*if(IsElementPresent(object)==true)
		{
			System.out.println("Element present"+object);
		}
		else
		{
			System.out.println("Element not present"+object);
		}*/
		if(IsElementPresent(object)==true)
		{
			System.out.println("Element present"+prop.getProperty(object));
		}
		else
		{
			System.out.println("Element not present"+prop.getProperty(object));
		}
	}
	public boolean IsElementPresent(String object)
	{
	    try
	    {
	    	//getObject(object);
	    	getObject(object);
	    	//driver.findElement(By.xpath("//input[@class='gNO89b' and @value='Google Search' and @ type='submit']"));
	        return true;
	    }
	    catch (NoSuchElementException  e)
	    {
	        return false;
	    }
	}
	

}
