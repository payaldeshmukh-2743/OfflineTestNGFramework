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

public class LoginTest extends TestBase {
	LoginPage Login;
	Dashboard dashboard;

	public LoginTest() throws Exception {
		super();

	}

	@BeforeSuite
	public void name() {
		extent = new ExtentReports(System.getProperty("user.dir") + "/Reports/ExtentReport/"+System.currentTimeMillis()+".html", true);
		extent.addSystemInfo("Host Name", "Payal_local").addSystemInfo("Environment", "Automation Testing")
				.addSystemInfo("User Name", "Payal");
		extent.loadConfig(new File(System.getProperty("user.dir") + "/test-output/extent-config.xml"));

	}

	@BeforeMethod
	public void SetUp() throws Exception {

		Initialization();
		Login = new LoginPage();

	}

	@Test
	public void loginTest() throws Exception {

		logger = extent.startTest("loginTest");
		logger.log(LogStatus.INFO, "Login Testcase");
		Login.Login("kiran@gmail.com", "123456");

		logger.log(LogStatus.PASS, "Login Sucessfully");

	}

	@Test(dataProvider = "Data", dataProviderClass = Util.DataProviderManual.class, enabled = true, groups = {
			"regression" })
	public void LoginWithDataProviderManualTest(String EmailData, String PasswordData) throws Exception {
		logger = extent.startTest("LoginWithDataProviderManualTest");
		logger.log(LogStatus.INFO, " LoginWithDataProviderManualTest");
		dashboard = Login.Login(EmailData, PasswordData);
		String ActualTitle = dashboard.LogoHeading();
		String ExpectedTitle = "Admin";
		Assert.assertEquals(ActualTitle, ExpectedTitle);

	}

	@Test(dataProvider = "Data", dataProviderClass = Util.DataProviderManual.class, enabled = true, groups = {
			"regression" })
	public void RegisterNewMemberWithDataProviderManualTest(String Name, String Mobile, String Email, String Password)
			throws Exception {
		logger = extent.startTest("RegisterNewMemberWithDataProviderManualTest");
		logger.log(LogStatus.INFO, " RegisterNewMemberWithDataProviderManualTest");
		Login.RegisterNewMember(Name, Mobile, Email, Password);

	}

	@Test(dataProvider = "ExcelData", dataProviderClass = Util.DataProviderExcelRead.class, enabled = true, groups = {
			"regression" })
	public void LoginWithDataProviderExcelTest(String EmailData, String PasswordData) throws Exception {
		logger = extent.startTest("LoginWithDataProviderExcelTest");
		logger.log(LogStatus.INFO, " LoginWithDataProviderExcelTest");
		dashboard = Login.Login(EmailData, PasswordData);
		// Login.LoginWithDataProvider(EmailData, PasswordData);
		// Thread.sleep(1000);
	}

	@Test(dataProvider = "ExcelData", dataProviderClass = Util.DataProviderExcelRead.class, enabled = true, groups = {
			"regression" })
	public void RegisterNewMemberWithDataProviderExcelTest(String Name, String Mobile, String Email, String Password)
			throws Exception {
		logger = extent.startTest("RegisterNewMemberWithDataProviderExcelTest");
		logger.log(LogStatus.INFO, " RegisterNewMemberWithDataProviderExcelTest");
		Login.RegisterNewMember(Name, Mobile, Email, Password);

	}

	@Test(enabled = true, groups = { "smoke", "sanity" })
	public void ValidLoginTest() throws Exception {
		logger = extent.startTest("ValidLoginTest");
		logger.log(LogStatus.INFO, " ValidLoginTest");
		Login.ValidLogin();
		Thread.sleep(2000);
	}

	@Test(enabled = true, groups = { "smoke", "sanity" })
	public void EmailfieldTest() {
		logger = extent.startTest("EmailfieldTest");
		logger.log(LogStatus.INFO, " EmailfieldTest");

		String actualEmailerror = Login.EmailfieldVerification("test@gmail.com");
		String expectedEmailError = "Please enter email as kiran@gmail.com";
		Assert.assertEquals(actualEmailerror, expectedEmailError);
	}

	@Test(enabled = true, groups = { "smoke", "sanity" })
	public void PasswordfieldTest() {
		logger = extent.startTest("PasswordfieldTest");
		logger.log(LogStatus.INFO, " PasswordfieldTest");
		String actualPassworderror = Login.PasswordFieldVerification("test");
		String expectedPasswordError = "Please enter password 123456";
		Assert.assertEquals(actualPassworderror, expectedPasswordError);
	}

	@Test(enabled = true, groups = { "sanity" })
	public void FailureTest11() {
		logger = extent.startTest("FailureTest11");
		logger.log(LogStatus.INFO, " FailureTest11");
		Assert.assertTrue(false);
	}

	@Test(enabled = true, groups = { "sanity" })
	public void FailureTest22() {
		logger = extent.startTest("FailureTest22");
		logger.log(LogStatus.INFO, " FailureTest22");
		Assert.assertFalse(true);
	}

	@Test(enabled = true, groups = { "smoke", "sanity" })
	public void SignInButtonDisplayTest() {
		logger = extent.startTest("SignInButtonDisplayTest");
		logger.log(LogStatus.INFO, " SignInButtonDisplayTest");

		boolean status = Login.SignInButtonIsDisplay();
		Assert.assertEquals(status, true);
	}

	@Test(enabled = true, groups = { "smoke", "sanity" })
	public void SignButtonEnableTest() {
		logger = extent.startTest("SignButtonEnableTest");
		logger.log(LogStatus.INFO, " SignButtonEnableTest");

		boolean status = Login.SignInButtonIsEnable();
		Assert.assertEquals(status, true);
	}

	@Test(enabled = true, groups = { "smoke", "sanity", "regression" })
	public void registerNewmemeberTest() throws Exception {
		logger = extent.startTest("registerNewmemeberTest");
		logger.log(LogStatus.INFO, " registerNewmemeberTest");
		Login.RegisterNewMember("Payal", "9823873382", "Payal@gmail.co", "Payal@1234");
	}

	@Test(enabled = true, groups = { "smoke", "sanity", "regression" })
	public Dashboard SignInTest() throws Exception {
		logger = extent.startTest("SignInTest");
		logger.log(LogStatus.INFO, " SignInTest");
		String ActualEmailFieldError = Login.EmailfieldVerification("kiran@gmail.com");
		String expectedEmailfieldError = "";
		Assert.assertEquals(ActualEmailFieldError, expectedEmailfieldError);
		String actualPasswordFieldError = Login.PasswordFieldVerification("123456");
		String expectedpasswordfieldError = "";
		Assert.assertEquals(actualPasswordFieldError, expectedpasswordfieldError);
		Login.ClickSignInButton();
		// System.out.println("In dashboard");
		return new Dashboard();
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

	@AfterSuite
	public void closeReport() {
		extent.flush();
		extent.close();
	}

}
