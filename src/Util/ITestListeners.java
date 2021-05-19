package Util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import Base.TestBase;

public class ITestListeners extends TestBase implements ITestListener{

	public ITestListeners() throws Exception {
		super();
		
	}

	@Override
	public void onFinish(ITestContext result) {
		System.out.println("On Test Finish:" + result.getName());
	}

	@Override
	public void onStart(ITestContext result) {
		System.out.println("On Test Start:" + result.getName());	
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("On Test onTestFailedButWithinSuccessPercentage:" + result.getName());	
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("On Test Failure:" + result.getName());
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		try {
			FileUtils.copyFile(srcFile,
					new File(currentDir + "/ScreenShot/" + result.getName() + System.currentTimeMillis() + ".png"));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("On Test onTestSkipped:" + result.getName());
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("On Test onTestStart:" + result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("On Test onTestSuccess:" + result.getName());	
	}

}
