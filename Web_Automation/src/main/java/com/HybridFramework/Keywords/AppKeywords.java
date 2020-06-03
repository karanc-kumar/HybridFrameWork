package com.HybridFramework.Keywords;

import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.Hashtable;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

//import org.openqa.selenium.By;

public class AppKeywords extends ValidationKeywords{
	//All application related keywords here
	ExtentTest extT;
	//public AppKeywords(ExtentTest extT)
	//{
		
		public AppKeywords(ExtentTest extT) {
			super(extT);
			// TODO Auto-generated constructor stub
		
		this.extT=extT;
		prop=new Properties();
		//String propath=System.getProperty("user.dir")+"\\src\\test\\resources\\project.properties";// for Xpath locator
		String propath=System.getProperty("user.dir")+"\\src\\test\\resources\\project_DiffLocaters.properties";// for different locators 
		
		//System.out.println(propath);
		try 
		{
			FileInputStream fis=new FileInputStream(propath);
			prop.load(fis);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
	}
	
	public void validateLogin(String object, String data, String datat)
	{
		
		extT.log(Status.INFO, "Validating status");
		String title=getObject(object).getText();
		String actResult="Login_Pass";
		System.out.println("The datavalue"+data);
		String expResult=datat;
		System.out.println("Expected Result :"+expResult);
		if(title.equalsIgnoreCase(data) )
		{
			
			if(actResult.equalsIgnoreCase(expResult))
			{
			System.out.println("Matched : found the text  "+expResult);
			extT.log(Status.PASS, "Sucess Result: "+ expResult);
			}
			else
			{
				actResult=expResult;
				System.out.println("UnMatched : Expected result not matched"+actResult);
				extT.log(Status.FAIL, "Fail Result: "+ expResult);
				//reportFailure("Invalid Result :  "+ expResult);
				
			}
		}
		else
		{
			//System.out.println("Unmacthed : Text not found  "+data);
			actResult=expResult;
			reportFailure("Invalid Result :  "+ expResult);
		}
	}
	
	public void defaultLogin()
	{
		extT.log(Status.INFO, "Default Login");
		type("Username_Xpath",prop.getProperty("default_login_username"));
		//verifyElementPresent	UsernameSubmit_id
		//click("UsernameSubmit_id");//this is marked as comment as the usernme _id button is not there now .
		type("Password_Xpath",prop.getProperty("default_login_password"));
		click("PasswordSubmit_CSS");

		
	}
	
	public void selectDate(String purchaseDate )
	{
		extT.log(Status.INFO, "Select Date"+purchaseDate);
		
		Date currentDate=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		try
		{
		Date dateToBeSelected=sdf.parse(purchaseDate);
		System.out.println("Parsed date : "+dateToBeSelected.toString());
		
		sdf=new SimpleDateFormat("d");
		String day=sdf.format(dateToBeSelected);
		System.out.println("Day::"+day);
		
		sdf=new SimpleDateFormat("MMM");
		String month=sdf.format(dateToBeSelected);
		System.out.println("Month::"+month);
		
		sdf=new SimpleDateFormat("yyyy");
		String year=sdf.format(dateToBeSelected);
		System.out.println("Year"+year);
		
		String monthYearDisplayed=getObject("Month_Year_Xpath").getText();
		String monthYearSelected=month+" "+year;
		
		if(dateToBeSelected.compareTo(currentDate)==0)
		{
			//getObject("Month_Year_Xpath").click();
			getObject("ForwardIcon_Xpath").click();
			getObject("BackwardIcon_Xpath").click();
			driver.findElement(By.xpath("//td[text()='"+day+"']")).click();
		}
		else
		{
			while(!monthYearDisplayed.equals(monthYearSelected))
			{
				if(dateToBeSelected.compareTo(currentDate)==1)
				{
					getObject("ForwardIcon_Xpath").click();
				} 
				else if(dateToBeSelected.compareTo(currentDate)==-1)
				{
					getObject("BackwardIcon_Xpath").click();
				}
					
				monthYearDisplayed=getObject("Month_Year_Xpath").getText();
			}
		}
		
		
		driver.findElement(By.xpath("//td[text()='"+day+"']")).click();
		
		
		}
		catch(Exception e)
		{
			reportFailure(e.getMessage());
		}
		
		
		System.out.println("In date method");
	}
	public void verifyCheckBoxUnchecked(String object)
	{
		
		boolean selected =getObject(object).isSelected();
		if(selected)
		{
			getObject(object).click();
		}
		
		
	}
	public void selectCompany(String data)
	{
		String company;
		int len=data.length();
		company=data.substring(0, len-1);
		type("AddStockName_id",company);
		
		try
		{
			Thread.sleep(1000);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		/*try
		{
		WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("AddStockName_id")));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}*/
		
		getObject("AddStockName_id").sendKeys(Keys.ENTER);
	}

}
