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

public class DashboardTest extends TestBase {

	LoginPage login;
	Dashboard dashboard;

	public DashboardTest() throws Exception {
		super();

	}

	@BeforeMethod
	public void SetUp() throws Exception {
		Initialization();
		login = new LoginPage();
		login.ValidLogin();
		dashboard = new Dashboard();

	}

	@Test(enabled = true, groups = { "smoke", "regression" })
	public void LogoHeadingTest() {
		logger = extent.startTest("LogoHeadingTest");
		logger.log(LogStatus.INFO, " LogoHeadingTest");
		String Heading = dashboard.LogoHeading();
		String ExpectedHeading = "Admin";
		Assert.assertEquals(Heading, ExpectedHeading);
	}

	@Test(enabled = true, groups = { "smoke", "regression" })
	public void UsernameTest() {
		logger = extent.startTest("UsernameTest");
		logger.log(LogStatus.INFO, " UsernameTest");
		String ActualUserName = dashboard.UserName();
		String ExpectedUserName = "Kiran";
		Assert.assertEquals(ActualUserName, ExpectedUserName);
	}

	@Test(enabled = true, groups = { "smoke", "regression" })
	public void DashboardHeadingTest() {
		logger = extent.startTest("DashboardHeadingTest");
		logger.log(LogStatus.INFO, " DashboardHeadingTest");
		String ActualDashboardHeading = dashboard.DashboardHeading();
		String ExpectedDashboardHeading = "Dashboard Control panel";
		Assert.assertEquals(ActualDashboardHeading, ExpectedDashboardHeading);
	}

	@Test(enabled = true, groups = { "smoke", "sanity" })
	public void dashboardLinkTest() {
		logger = extent.startTest("dashboardLinkTest");
		logger.log(LogStatus.INFO, " dashboardLinkTest");
		dashboard.DashBoardLink();
		String ActualDashboardHeading = dashboard.DashboardHeading();
		String ExpectedDashboardHeading = "Dashboard Control panel";
		Assert.assertEquals(ActualDashboardHeading, ExpectedDashboardHeading);

	}

	// not able to read this data with framework
	@Test(enabled = true, groups = { "sanity", "regression" })
	public void ReadDashboardDataTest() throws Exception {
		logger = extent.startTest("ReadDashboardDataTest");
		logger.log(LogStatus.INFO, " ReadDashboardDataTest");
		dashboard.DashBoardLink();
		dashboard.ReadDashBoardData();
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
		extent = new ExtentReports(System.getProperty("user.dir") + "/Reports/ExtentReport"+System.currentTimeMillis()+".html", true);
		extent.addSystemInfo("Host Name", "Payal_local").addSystemInfo("Environment", "Automation Testing")
				.addSystemInfo("User Name", "Payal");
		extent.loadConfig(new File(System.getProperty("user.dir") + "/test-output/extent-config.xml"));

	}

}
