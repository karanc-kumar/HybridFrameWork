package temp;

import java.util.Hashtable;

import com.HybridFramework.Util.Xls_Reader;

public class ReadingData {

	public static void main(String[] args) {
		String sheetName="LoginTest";
		String path=System.getProperty("user.dir")+"\\src\\test\\resources\\TestCasesDiffLocProperties.xlsx";
		Xls_Reader xls=new Xls_Reader(path);
		int rCount=xls.getRowCount(sheetName);
		int cCount=xls.getColumnCount(sheetName);
		System.out.println("rCount  "+rCount+"   cCount  "+cCount);
		Object[][] data=new Object[rCount-1][1];
		Hashtable<String, String> dataTable=null;
		for(int rNum=2; rNum<=rCount; rNum++)
		{
			dataTable=new Hashtable<String,String>();
						
			for(int cNum=0; cNum<cCount; cNum++)
			{
				
				String colName=xls.getCellData(sheetName, cNum, 1);
				String cellData=xls.getCellData("LoginTest", cNum, rNum);
				dataTable.put(colName, cellData);
				System.out.println(dataTable);
				//data[rNum-2][cNum]=cellData;
				//System.out.println(data[rNum-2][cNum]);
			}
			data[rNum-2][0]=dataTable;
			//System.out.println("The data is::  "+data[rNum-2][0]);
			
		}
		

	}

}
