package driverFactory;

import commonFunctions.FunctionLibrary;
import utilities.ExcelFileUtil;

public class DriverScript extends FunctionLibrary{
String inputpath ="./FileInput/dataEngine.xlsx";
String outputpath ="./FileOutput/HyridResults.xlsx";
public void startTest()throws Throwable
{
	String ModuleStatu="";
	//read excel path
	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
	//iterate all rows in MasterTestCases
	for(int i=1;i<=xl.rowCount("MasterTestCases");i++)
	{
		if(xl.getCellData("MasterTestCases", i, 2).equalsIgnoreCase("Y"))
		{
			String TCModule=xl.getCellData("MasterTestCases", i, 1);
			for(int j=1;j<=xl.rowCount(TCModule);j++)
			{
				String Description = xl.getCellData(TCModule, j, 0);
				String Object_Type =xl.getCellData(TCModule, j, 1);
				String Locator_type =xl.getCellData(TCModule, j, 2);
				String Locator_Value = xl.getCellData(TCModule, j, 3);
				String Test_Data = xl.getCellData(TCModule, j, 4);
				try {
					if(Object_Type.equalsIgnoreCase("startBrowser"))
					{
						driver = FunctionLibrary.startBrowser();
					}
					else if(Object_Type.equalsIgnoreCase("openUrl"))
					{
						FunctionLibrary.openUrl();
					}
					else if(Object_Type.equalsIgnoreCase("waitForElement"))
					{
						FunctionLibrary.waitForElement(driver, Locator_type, Locator_Value, Test_Data);
					}
					else if(Object_Type.equalsIgnoreCase("typeAction"))
					{
						FunctionLibrary.typeAction(driver, Locator_type, Locator_Value, Test_Data);
					}
					else if(Object_Type.equalsIgnoreCase("clickAction"))
					{
						FunctionLibrary.clickAction(driver, Locator_type, Locator_Value);
					}
					else if(Object_Type.equalsIgnoreCase("validateTitle"))
					{
						FunctionLibrary.validateTitle(driver, Test_Data);
					}
					else if(Object_Type.equalsIgnoreCase("closeBrowser"))
					{
						FunctionLibrary.closeBrowser(driver);
					}
					else if(Object_Type.equalsIgnoreCase("mouseClick"))
					{
						FunctionLibrary.mouseClick(driver);
					}
					else if(Object_Type.equalsIgnoreCase("categoryTable"))
					{
						FunctionLibrary.categoryTable(driver, Test_Data);
					}
					else if(Object_Type.equalsIgnoreCase("captureNumber"))
					{
						FunctionLibrary.captureNumber(driver, Locator_type, Locator_Value);
					}
					else if(Object_Type.equalsIgnoreCase("supplierTable"))
					{
						FunctionLibrary.supplierTable(driver);
					}
					else if(Object_Type.equalsIgnoreCase("dropDownAction"))
					{
						FunctionLibrary.dropDownAction(driver, Locator_type, Locator_Value, Test_Data);
					}
					else if(Object_Type.equalsIgnoreCase("captureData"))
					{
						FunctionLibrary.captureData(driver, Locator_type, Locator_Value);
					}
					else if(Object_Type.equalsIgnoreCase("stocktable"))
					{
						FunctionLibrary.stocktable(driver);
					}
					//write as pass into statuc in TCModule sheet
					xl.setCelldata(TCModule, j, 5, "Pass", outputpath);
					ModuleStatu="True";
				}catch(Exception e)
				{
					System.out.println(e.getMessage());
					xl.setCelldata(TCModule, j, 5, "Fail", outputpath);
					ModuleStatu="False";
				}
				
			}
			if(ModuleStatu.equalsIgnoreCase("True"))
			{
				xl.setCelldata("MasterTestCases", i, 3, "Pass", outputpath);
			}else
			{
				xl.setCelldata("MasterTestCases", i, 3, "Fail", outputpath);
			}
		}
		else
		{
			//write as blocked in to MasterTestCases which are flag to N
			xl.setCelldata("MasterTestCases", i, 3, "Blocked", outputpath);
		}
	}
}
}
