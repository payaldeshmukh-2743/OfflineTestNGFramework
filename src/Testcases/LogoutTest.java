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
import Pages.Logout;

public class LogoutTest extends TestBase {

	LoginPage login;
	Dashboard dashboard;
	Logout Logout;

	public LogoutTest() throws Exception {
		super();

	}

	@BeforeMethod
	public void setup() throws Exception {
		Initialization();
		login = new LoginPage();
		login.ValidLogin();
		dashboard = new Dashboard();
		dashboard.LogoutLink();
		Logout = new Logout();

	}

	@Test(enabled = true, groups = { "smoke", "sanity", "regression" })
	public void logoutmessagetest() {
		logger = extent.startTest("logoutmessagetest");
		logger.log(LogStatus.INFO, " logoutmessagetest");
		String actualmessage = Logout.LogoutMessage();
		String Expectedmessage = "Logout successfully";
		Assert.assertEquals(actualmessage, Expectedmessage);
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
