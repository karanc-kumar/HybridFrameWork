package temp;

import com.HybridFramework.Util.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Reporting {

	public static void main(String[] args) {
		
		String repPath=System.getProperty("user.dir")+"\\Reports\\";
		ExtentReports extR=ExtentManager.getInstance(repPath);
		ExtentTest extT=extR.createTest("SampleTest");
		extT.log(Status.INFO, "Starting Test");
		extT.log(Status.INFO, "Navigating to site");
		extT.log(Status.INFO, "Logging in");
		extT.log(Status.PASS, "Login Sucess");
		extR.flush();
	}
}