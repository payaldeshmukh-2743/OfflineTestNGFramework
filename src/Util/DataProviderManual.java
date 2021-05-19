package Util;

import java.lang.reflect.Method;

import Base.TestBase;

public class DataProviderManual extends TestBase{

	public DataProviderManual() throws Exception {
		super();
		
	}

	@org.testng.annotations.DataProvider(name = "Data")
	public static Object[][] Data(Method MethodName) {
		Object[][] Data = null;
		if (MethodName.getName().equalsIgnoreCase("LoginWithDataProviderManualTest")) {
			Data = new Object[][] { { "", "" }, { "kiran123", "12345" }, { "kiran@gmail.com", "123456" } };
		} else if (MethodName.getName().equalsIgnoreCase("AddUserWithDataProviderManualTest")) {
			Data = new Object[][] { { "", "", "", "", "", "" },
					{ "Payal", "9834567856", "payal@gmail.com", "Female", "Delhi", "123456" },
					{ "Test", "9834567856", "Test@gmail.com", "Male", "Maharashtra", "123456" },
					{ "Test11", "9834567856", "Test@gmail.com", "Male", "HP", "123456" },
					{ "Test22", "9834567856", "Test@gmail.com", "Male", "Punjab", "123456" } };
		} else if (MethodName.getName().equalsIgnoreCase("RegisterNewMemberWithDataProviderManualTest")) {
			Data = new Object[][] { { "", "", "", "" }, { "Payal", "9834567856", "payal@gmail.com", "123456" },
					{ "Test", "9834567856", "Test@gmail.com", "123456" },
					{ "Test11", "9834567856", "Test@gmail.com", "123456" },
					{ "Test22", "9834567856", "Test@gmail.com", "123456" } };
		}
		return Data;
	}

}
