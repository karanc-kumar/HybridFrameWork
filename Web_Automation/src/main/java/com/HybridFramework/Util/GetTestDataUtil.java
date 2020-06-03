package com.HybridFramework.Util;

import java.util.Hashtable;

public class GetTestDataUtil {
	
	public static Object[][] getTestData(Xls_Reader xls, String testCase)
	{
	int rCount=xls.getRowCount(testCase);
	int cCount=xls.getColumnCount(testCase);
	System.out.println("rCount  "+rCount+"   cCount  "+cCount);
	Object[][] data=new Object[rCount-1][1];
	Hashtable<String, String> dataTable=null;
	for(int rNum=2; rNum<=rCount; rNum++)
	{
		dataTable=new Hashtable<String,String>();
					
		for(int cNum=0; cNum<cCount; cNum++)
		{
			
			String colName=xls.getCellData(testCase, cNum, 1);
			String cellData=xls.getCellData(testCase, cNum, rNum);  //changed "LoginTest"  to testCase
			dataTable.put(colName, cellData);
			System.out.println(dataTable);
			//data[rNum-2][cNum]=cellData;
			//System.out.println(data[rNum-2][cNum]);
		}
		data[rNum-2][0]=dataTable;
		//System.out.println("The data is::  "+data[rNum-2][0]);
		
	}
	return data;
	}

	public static boolean isSkippable(Xls_Reader xls, String testName)
	{
		String sheetName="TestCases";
		int rCount=xls.getRowCount(sheetName);
		for(int rNum=2; rNum<=rCount; rNum++)
		{
			String tName=xls.getCellData(sheetName, "TCID", rNum);
			//System.out.print(tName  +" ::");
			if(tName.equalsIgnoreCase(testName))
			{
				String runMode=xls.getCellData(sheetName, "RunMode", rNum);
				if(runMode.equalsIgnoreCase("N"))
				{return true;}
				else
				{return false;}
			}
				
		}
		
		return false;
	}
}
