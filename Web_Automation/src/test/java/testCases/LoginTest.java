package testCases;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
//import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.HybridFramework.Drivers.DriverScript;
import com.HybridFramework.Util.ExtentManager;
import com.HybridFramework.Util.GetTestDataUtil;
import com.HybridFramework.Util.Xls_Reader;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class LoginTest {
	
	String repPath;
	ExtentReports extR;
	ExtentTest extT;
	String testCase="LoginTest";
	DriverScript dsc;
	String path=System.getProperty("user.dir")+"\\src\\test\\resources\\TestCasesDiffLocProperties.xlsx";
	Xls_Reader xls=new Xls_Reader(path);
	
	
	//@BeforeTest
	@BeforeMethod
	public void init()
	{
		repPath=System.getProperty("user.dir")+"\\Reports\\";
		extR=ExtentManager.getInstance(repPath);
		extT=extR.createTest(testCase);
	}
	
	//@AfterTest
	@AfterMethod
	public void quit()
	{
		extR.flush();
		dsc.quit();
		
		
	}
	@Test(dataProvider="getData")
	public void loginTest(Hashtable<String, String> data)
	{
		
	if(  GetTestDataUtil.isSkippable(xls, testCase)||data.get("RunMode").equalsIgnoreCase("N"))  //if any one mode(ie testcase or dataset) is N
	{
		extT.log(Status.SKIP, "Skip from exeution based on RunMode");
		throw new SkipException("Skip from exeution based on RunMode");
	}
	extT.log(Status.INFO, "Starting Test");
	dsc=new DriverScript(extT);
	String datat=data.get("ExpectedResults");
	dsc.executeKeywords(testCase, xls, data, datat);
	//extT.log(Status.INFO, "Navigating to site");
	//extT.log(Status.INFO, "Logging in");
	//extT.log(Status.PASS, "Login Sucess");
	
	extT.log(Status.PASS, "Login Test Pass");
	}
	
	@DataProvider
	public Object[][] getData()
	{
		return GetTestDataUtil.getTestData(xls,testCase);
	}
	
     
	

}
