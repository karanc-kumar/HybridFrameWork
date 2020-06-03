package com.HybridFramework.Drivers;

import java.util.Hashtable;
import java.util.Properties;



import com.HybridFramework.Keywords.AppKeywords;
import com.HybridFramework.Keywords.GenericKeywords;
import com.HybridFramework.Util.Xls_Reader;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class DriverScript {
	
	ExtentTest extT;
	Properties prop;
	AppKeywords appKey;
	
	public DriverScript(ExtentTest extT)
	{
		this.extT=extT;
	
	}
	//public static void main(String[] args) 
	public void executeKeywords(String testCase, Xls_Reader xls, Hashtable<String, String> hashData, String datat)
	{
		//String testCase="LoginTest";
		//String path=System.getProperty("user.dir")+"\\src\\test\\resources\\TestCases.xlsx";
		//String path=System.getProperty("user.dir")+"\\src\\test\\resources\\TestCasesProperties.xlsx";
		//String path=System.getProperty("user.dir")+"\\src\\test\\resources\\TestCasesDiffLocProperties.xlsx";
		//System.out.println(path);
	//	Xls_Reader xls=new Xls_Reader(path);
		//GenericKeywords gk=new GenericKeywords(extT);
		
		appKey=new AppKeywords(extT);
		int rows=xls.getRowCount("Keywords");
		//System.out.println(rows);
		//System.out.println("Check");
		for(int rNum=2; rNum<=rows; rNum++)
		{	
				//String execute=xls.getCellData("Keywords", "Execute", rNum);
				String tcName=xls.getCellData("Keywords","TCName", rNum);
				//if(tcName.equalsIgnoreCase(testCase) && execute.equalsIgnoreCase("Y"))
				if(tcName.equalsIgnoreCase(testCase))
				{
					String Tcid=xls.getCellData("Keywords","TCID", rNum);
					String keyword=xls.getCellData("Keywords","Keyword", rNum);
					//String object=xls.getCellData("Keywords","Object",rNum);  // to read the value directly from xpath excel sheet
					String objectKey=xls.getCellData("Keywords","Object",rNum); // to read the value from xpath properties key value pair
					//String expResult=xls.getCellData("Keywords","ExpectedResults",rNum);
					String keydata=xls.getCellData("Keywords","Data",rNum);
					//String expResult=xls.getCellData(sheetName, "", rNum)
					String data=hashData.get(keydata);
					
					
					//System.out.println("TCID: "+Tcid+"-----"+"Keyword: "+keyword+"------"+"Object: "+objectKey+"-----"+"Data: "+data);
					//extT.log(Status.INFO,Tcid+"-----"+keyword+"------"+objectKey+"-----"+data);
					
					switch(keyword)
					{
						case "openBrowser":
						{
							System.out.println("OpenBrowser keyword called");
							appKey.openBrowser(data);	
							
						}break;
						
						case "navigate":
						{
							System.out.println("Navigate keyword called");
							//appKey.navigate(object);
							appKey.navigate(objectKey);
						}break;
						
						case "type":
						{
							System.out.println("Type keyword called");
							//appKey.type(object, data);
							appKey.type(objectKey, data);	
						}break;
						
						case "click":
						{
							System.out.println("click keyword called");
							//appKey.click(object);
							appKey.click(objectKey);
						}break;
						
						case "verifyElementPresent":
						{
							System.out.println("verifyElementPresent called");
							//appKey.verifyElementPresent(object);
							appKey.verifyElementPresent(objectKey);
						}break;
						
						case "validateLogin":
						{
							System.out.println("Login validation");
					       	appKey.validateLogin(objectKey, data, datat );
						}break;
						
						case "verifyTitle":
						{
							System.out.println("Title Verification");
							//appKey.verifyTitle(object, data);
							appKey.verifyTitle(objectKey, data);
						}break;
						
						case "verifyCheckBoxUnchecked":
						{
							System.out.println("Check Checkbox");
							//appKey.verifyCheckBoxUnchecked(object);
							appKey.verifyCheckBoxUnchecked(objectKey);
						}break;
						
						case "selectCompany":
						{
							System.out.println("Select the company");
							appKey.selectCompany(data);
							
						}break;
						
						case "defaultLogin":
						{
							System.out.println("Deault login");
							//appKey.verifyCheckBoxUnchecked(object);
							appKey.defaultLogin();
						}break;
						
						case "clear":
						{
							System.out.println("Clear text");
							appKey.clear(objectKey);
							
						}break;
						
						case "selectDate":
						{
							System.out.println("Select Date from date picker");
							appKey.selectDate(data);
						}break;
						
						default:
						{
							System.out.println("No keyword present");
						}break;
					}
					extT.log(Status.INFO,tcName+"s-----"+keyword+"------"+objectKey+"-----"+data);	
					//System.out.println(prop.getProperty(objectKey));
			}
						
		}
		
	}
	
	public void quit()
	{
		if(appKey!=null)
			appKey.quit();
	}
}

