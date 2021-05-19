package Base;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class TestBase {
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static Properties config;
	public static WebDriver driver;

	public static Logger Log4jlogger;

	public TestBase() throws Exception {
		Log4jlogger = Logger.getLogger("TestBase");
		PropertyConfigurator.configure("log4j.properties");

		config = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\HP\\eclipse-workspace\\OfflineTestNGFramework\\src\\config\\config.properties");
		config.load(fis);
		Log4jlogger.info(" Property File loaded Sucessfully");

	}

	public static void Initialization() {
		String Browser_Name = config.getProperty("Browser");
		if (Browser_Name.equalsIgnoreCase("FireFox")) {
			System.setProperty("webdriver.gecko.driver", "");
			driver = new FirefoxDriver();

			Log4jlogger.info(" Fire Fox Driver Initialize");
		}

		else if (Browser_Name.equalsIgnoreCase("Chrome")) {
			// --to handle ssl certificate in case of https
			System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Driver\\chromedriver.exe");
			/*
			 * ChromeOptions handlingSSL = new ChromeOptions();
			 * handlingSSL.setAcceptInsecureCerts(true); driver = new
			 * ChromeDriver(handlingSSL);
			 */
			driver = new ChromeDriver();
			Log4jlogger.info(" Google chroome Driver Initialize");

		}

		driver.manage().window().maximize();
		Log4jlogger.info("window maximize");
		driver.manage().deleteAllCookies();
		Log4jlogger.info("Delete all cookies");
		driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(config.getProperty("PageLoadTime")),
				TimeUnit.SECONDS);
		
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("ImplicitWait")),
				TimeUnit.SECONDS);
		// driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// driver.get(config.getProperty("URL"));
		driver.get("C:\\Selenium Software\\Offline Website\\index.html");
		Log4jlogger.info(" URL get launched Sucessfully");
	}

}
