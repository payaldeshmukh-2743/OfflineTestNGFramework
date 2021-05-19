package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.TestBase;

public class Dashboard  extends TestBase{
	@FindBy(xpath = "//*[@class='logo-lg']/b")
	@CacheLookup
	WebElement Logo;
	@FindBy(xpath = "//*[@class='pull-left info']/p")
	@CacheLookup
	WebElement Username;
	@FindBy(xpath = "//a[@href='dashboard.html']")
	@CacheLookup
	WebElement DashboardLink;
	@FindBy(xpath = "//a[@href='users.html']")
	@CacheLookup
	WebElement UsersLink;
	@FindBy(xpath = "//a[@href='operators.html']")
	@CacheLookup
	WebElement OperatorsLink;
	@FindBy(xpath = "//a[@href='logout.html']")
	@CacheLookup
	WebElement LogoutLink;
	@FindBy(xpath = "//*[@class='content-header']//h1")
	@CacheLookup
	WebElement DashBoardHeading;

	public Dashboard() throws Exception {
		PageFactory.initElements(driver, this);
	}

	public String LogoHeading() {
		String LogoHeading = Logo.getText();
		return LogoHeading;

	}

	public String DashboardHeading() {
		String DashboardHeading = DashBoardHeading.getText();
		return DashboardHeading;

	}

	public String UserName() {
		String UserName = Username.getText();
		return UserName;

	}

	public void ReadDashBoardData() throws Exception {
		Thread.sleep(2000);
		List<WebElement> element = driver.findElements(By.xpath("//*[@class='content']//div[@class='row']/div"));
		String beforeXpath = "//*[@class='content']//div[@class='row']/div[";
		String afterXpath1 = "]/div/div/h3";
		String afterXpath2 = "]/div/div/p";
		String afterXpath3 = "]/div/a";

		for (int i = 0; i < element.size(); i++) {
			System.out.print(driver.findElement(By.xpath(beforeXpath+i+afterXpath1)));
			System.out.print(driver.findElement(By.xpath(beforeXpath+i+afterXpath2)));
			System.out.print(driver.findElement(By.xpath(beforeXpath+i+afterXpath3)));
			
		
		}
		System.out.println();
	}

	public void DashBoardLink() {
		DashboardLink.click();
	}

	public void UsersLink() {
		UsersLink.click();
	}

	public void OperatorsLink() {
		OperatorsLink.click();
	}

	public void LogoutLink() {
		LogoutLink.click();
	}
}
