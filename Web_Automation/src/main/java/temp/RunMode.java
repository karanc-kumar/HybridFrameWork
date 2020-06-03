package temp;

import com.HybridFramework.Util.Xls_Reader;

public class RunMode {

	public static void main(String[] args) {
		String testName="LoginTest";
		String sheetName="TestCases";
		String path=System.getProperty("user.dir")+"\\src\\test\\resources\\TestCasesDiffLocProperties.xlsx";
		Xls_Reader xls=new Xls_Reader(path);
		int rCount=xls.getRowCount(sheetName);
		for(int rNum=2; rNum<=rCount; rNum++)
		{
			String tName=xls.getCellData(sheetName, "TCID", rNum);
			//System.out.print(tName  +" ::");
			if(tName.equalsIgnoreCase(testName))
			{
				String runMode=xls.getCellData(sheetName, "RunMode", rNum);
				/*if(runMode.equalsIgnoreCase("N"))
				{return true;}
				else
				{return false;}*/
								
			}
				
			
		}
		
	}

}
