package Testcases;

import java.io.File;

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
import Pages.Operators;

public class OperatorTest extends TestBase {

	LoginPage Login;
	Dashboard dashboard;
	Operators operator;

	public OperatorTest() throws Exception {
		super();

	}

	@BeforeMethod
	public void Setup() throws Exception {
		Initialization();
		Login = new LoginPage();
		Login.ValidLogin();
		dashboard = new Dashboard();
		dashboard.OperatorsLink();
		operator = new Operators();

	}

	@Test(enabled = true, groups = { "smoke", "sanity" })
	public void OperatorsHeadingTest() {
		logger = extent.startTest("OperatorsHeadingTest");
		logger.log(LogStatus.INFO, " OperatorsHeadingTest");
		String actual_Heading = operator.operatorsHeading();
		String Expected_Heading = "Operators";
		Assert.assertEquals(actual_Heading, Expected_Heading);
	}

	@Test(enabled = true, groups = { "sanity" })
	public void Operators_ListTest() {
		logger = extent.startTest("Operators_ListTest");
		logger.log(LogStatus.INFO, " Operators_ListTest");
		String actual_Heading = operator.operator_ListHeading();
		String Expected_Heading = "Operator List";
		Assert.assertEquals(actual_Heading, Expected_Heading);

	}

	@Test(enabled = true, groups = { "sanity" })
	public void ReadOperatorListTableTest() {
		logger = extent.startTest("ReadOperatorListTableTest");
		logger.log(LogStatus.INFO, " ReadOperatorListTableTest");
		operator.ReadOperatorListData();
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
