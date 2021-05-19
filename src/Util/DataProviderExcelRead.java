package Util;

import java.io.FileInputStream;
import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import Base.TestBase;

public class DataProviderExcelRead extends TestBase{

	public DataProviderExcelRead() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}



	static FileInputStream file = null;
	static Workbook book = null;
	static Sheet sheet = null;

	

	@DataProvider(name = "ExcelData")
	public static Object[][] ExcelData(Method methodName) throws Exception {

		file = new FileInputStream(config.getProperty("ExcelDataPath"));

		book = WorkbookFactory.create(file);

		if (methodName.getName().equalsIgnoreCase("RegisterNewMemberWithDataProviderExcelTest")) {

			sheet = book.getSheet("RegisterNewMember");
		} else if (methodName.getName().equalsIgnoreCase("LoginWithDataProviderExcelTest")) {

			sheet = book.getSheet("Login");
		} else if (methodName.getName().equalsIgnoreCase("AddUserWithDataProviderExcelTest")) {

			sheet = book.getSheet("AddUser");
		}
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				String datavalue = sheet.getRow(i + 1).getCell(k).toString();

				data[i][k] = datavalue;

			}
		}
		return data;
	}

}
