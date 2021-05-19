package Testcases;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import Base.TestBase;
import Pages.Dashboard;
import Pages.LoginPage;
import Pages.Users;

public class UsersTest extends TestBase {

	LoginPage Login;
	Dashboard Dashboard;
	Users User;

	public UsersTest() throws Exception {
		super();

	}

	@BeforeMethod
	public void SetUp() throws Exception {
		Initialization();
		Login = new LoginPage();
		Login.ValidLogin();
		Dashboard = new Dashboard();
		Dashboard.UsersLink();
		User = new Users();

	}

	@Test(enabled = true, groups = { "smoke", "sanity" })
	public void UserHeadingTest() {
		logger = extent.startTest("UserHeadingTest");
		logger.log(LogStatus.INFO, " UserHeadingTest");
		String ExpectedUserHeading = User.UsersHeading();
		String ActualUserHeading = "Users";
		Assert.assertEquals(ActualUserHeading, ExpectedUserHeading);
	}

	@Test(enabled = true, groups = { "smoke", "sanity" })
	public void UserListHeadingTest() {
		logger = extent.startTest("UserListHeadingTest");
		logger.log(LogStatus.INFO, " UserListHeadingTest");
		String ExpectedUserListHeading = User.UsersHeading();
		String ActualUserListHeading = "Users";
		Assert.assertEquals(ExpectedUserListHeading, ActualUserListHeading);

	}

	@Test(enabled = true, groups = { "smoke", "sanity" })
	public void ReadUserListTableDataTest() {
		logger = extent.startTest("UserHeadingTest");
		logger.log(LogStatus.INFO, " UserHeadingTest");
		User.ReadUserListData();
	}

	@Test(enabled = true, groups = { "sanity" })
	public void StateOptionTest() {
		logger = extent.startTest("StateOptionTest");
		logger.log(LogStatus.INFO, " StateOptionTest");
		User.ClickOnAddUser();
		ArrayList<String> expectedstateOption = new ArrayList<String>();
		expectedstateOption.add("--Select State--");
		expectedstateOption.add("Maharashtra");
		expectedstateOption.add("Delhi");
		expectedstateOption.add("HP");
		expectedstateOption.add("Punjab");

		List<String> ActualStateOption = User.GetStateOption();
		Assert.assertEquals(ActualStateOption, expectedstateOption);

	}

	@Test(enabled = true, groups = { "smoke", "sanity", "regression" })
	public void AddUserButtonTest() {
		logger = extent.startTest("AddUserButtonTest");
		logger.log(LogStatus.INFO, " AddUserButtonTest");
		User.ClickOnAddUser();

	}

	@Test(enabled = true, groups = { "smoke", "sanity" })
	public void AddUserHeadingTest() {
		logger = extent.startTest("AddUserHeadingTest");
		logger.log(LogStatus.INFO, " AddUserHeadingTest");
		User.ClickOnAddUser();
		String ActualHeading = User.AddUserHeading();
		String ExpectedHeading = "Add Userv";
		Assert.assertEquals(ActualHeading, ExpectedHeading);
	}

	@Test(enabled = true, groups = { "smoke", "sanity", "regression" })
	public void AddUserFormTest() {
		logger = extent.startTest("AddUserFormTest");
		logger.log(LogStatus.INFO, " AddUserFormTest");
		User.ClickOnAddUser();
		String ActualHeading = User.UserFormHeading();
		String ExpectedHeading = "Horizontal Form";
		Assert.assertEquals(ActualHeading, ExpectedHeading);
	}

	@Test(dataProvider = "Data", dataProviderClass = Util.DataProviderManual.class, enabled = true, groups = { "smoke",
			"sanity", "regression" })
	public void AddUserWithDataProviderManualTest(String Name, String Mobile, String Email, String Gender, String state,
			String Password) throws Exception {
		logger = extent.startTest("AddUserWithDataProviderManualTest");
		logger.log(LogStatus.INFO, " AddUserWithDataProviderManualTest");
		User.ClickOnAddUser();
		Thread.sleep(2000);
		String Expected = User.AddUser(Name, Mobile, Email, Gender, state, Password);
		String Actual = "User Added Successfully. You can not see added user.";
		Assert.assertEquals(Actual, Expected);
	}

	@Test(dataProvider = "ExcelData", dataProviderClass = Util.DataProviderExcelRead.class, enabled = true, groups = {
			"smoke", "sanity", "regression" })
	public void AddUserWithDataProviderExcelTest(String Name, String Mobile, String Email, String Gender, String state,
			String Password) throws Exception {
		logger = extent.startTest("AddUserWithDataProviderExcelTest");
		logger.log(LogStatus.INFO, " AddUserWithDataProviderExcelTest");
		User.ClickOnAddUser();
		Thread.sleep(2000);
		String Expected = User.AddUser(Name, Mobile, Email, Gender, state, Password);
		String Actual = "User Added Successfully. You can not see added user.";
		Assert.assertEquals(Actual, Expected);
	}

	@Test(enabled = true, groups = { "smoke", "sanity", "regression" })
	public void AddValidUserTest() throws Exception {
		logger = extent.startTest("AddValidUserTest");
		logger.log(LogStatus.INFO, " AddValidUserTest");
		User.ClickOnAddUser();
		Thread.sleep(2000);
		String Expected = User.AddUser("Payal", "9823873382", "payal@gmail.com", "Female", "Delhi", "123455");
		String Actual = "User Added Successfully. You can not see added user.";
		Assert.assertEquals(Actual, Expected);
	}

	@Test(enabled = true, groups = { "smoke", "sanity", "regression" })
	public void AddUserCancelButtonTest() throws Exception {
		logger = extent.startTest("AddUserCancelButtonTest");
		logger.log(LogStatus.INFO, " AddUserCancelButtonTest");
		User.ClickOnAddUser();
		Thread.sleep(2000);
		User.ClickOnAddUser_cancelButton();
		String ExpectedUserHeading = User.UsersHeading();
		String ActualUserHeading = "Users";
		Assert.assertEquals(ActualUserHeading, ExpectedUserHeading);
	}

	// org.openqa.selenium.StaleElementReferenceException: stale element reference:
	// element is not attached to the page document

	@Test(enabled = true, groups = { "smoke", "sanity", "regression" })
	public void AddUserAlertMessageTest() {
		logger = extent.startTest("AddUserAlertMessageTest");
		logger.log(LogStatus.INFO, " AddUserAlertMessageTest");
		User.ClickOnAddUser();

		String Actual = User.AddAlertMessage("Payal", "9823873382", "payal@gmail.com", "Female", "Delhi", "123455");
		String Expected = "User Added Successfully. You can not see added user.";
		Assert.assertEquals(Actual, Expected);
	}

	@AfterSuite
	public void closeReport() {
		extent.flush();
		extent.close();
	}

	@AfterMethod
	public void TearDown(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
		}
		// ending test
		// endTest(logger) : It ends the current test and prepares to create HTML report
		extent.endTest(logger);

		driver.quit();
	}

	@BeforeSuite
	public void name() {
		extent = new ExtentReports(System.getProperty("user.dir") + "/Reports/ExtentReport/"+System.currentTimeMillis()+".html", true);
		extent.addSystemInfo("Host Name", "Payal_local").addSystemInfo("Environment", "Automation Testing")
				.addSystemInfo("User Name", "Payal");
		extent.loadConfig(new File(System.getProperty("user.dir") + "/test-output/extent-config.xml"));

	}

}
