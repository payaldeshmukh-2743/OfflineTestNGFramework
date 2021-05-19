package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



import Base.TestBase;

public class LoginPage extends TestBase {

	
	@FindBy(xpath = "//*[@id='email_error']")
	@CacheLookup
	WebElement email_Error;
	@FindBy(xpath = "//*[@id='password_error']")
	@CacheLookup
	WebElement Pasword_Error;
	@FindBy(xpath = "//*[@id='email']")
	@CacheLookup
	WebElement Email;
	@FindBy(xpath = "//*[@id='password']")
	@CacheLookup
	WebElement Password;
	@FindBy(xpath = "//*[@type='submit']")
	@CacheLookup
	WebElement SignIn;
	@FindBy(xpath = "//*[contains(text(),'Register a new membership')]")
	@CacheLookup
	WebElement RegisterNewMember;
	@FindBy(xpath = "//*[@id='name']")
	@CacheLookup
	WebElement Name;
	@FindBy(xpath = "//*[@id='mobile']")
	@CacheLookup
	WebElement Mobile;
	@FindBy(xpath = "//*[@id='email']")
	@CacheLookup
	WebElement RegisterEmail;
	@FindBy(xpath = "//*[@id='password']")
	@CacheLookup
	WebElement RegisterPassword;
	@FindBy(xpath = "//*[@class='btn btn-primary btn-block btn-flat']")
	@CacheLookup
	WebElement RegisterSignIn;

	public LoginPage() throws Exception {
		PageFactory.initElements(driver, this);
	}

	public String EmailfieldVerification(String emaildata) {
		Email.sendKeys(emaildata);
		SignIn.click();
		String error = email_Error.getText();
		return error;
	}

	public String PasswordFieldVerification(String PasswordData) {
		Password.sendKeys(PasswordData);
		SignIn.click();
		String error = Pasword_Error.getText();
		return error;
	}

	public void ClickSignInButton() {
		SignIn.click();
	}

	public boolean SignInButtonIsDisplay() {
		return SignIn.isDisplayed();
	}

	public boolean SignInButtonIsEnable() {
		return SignIn.isEnabled();
	}

	public Dashboard Login(String EmailData, String PasswordData) throws Exception {
		Email.clear();
		Email.sendKeys(EmailData);
		Password.clear();
		Password.sendKeys(PasswordData);
		SignIn.click();
		return new Dashboard();
	}
	public void LoginWithDataProvider(String EmailData, String PasswordData) throws Exception {
		Email.clear();
		Email.sendKeys(EmailData);
		Password.clear();
		Password.sendKeys(PasswordData);
		SignIn.click();
		
	}
	
	
	public Dashboard ValidLogin() throws Exception {
		Email.clear();
		Email.sendKeys(config.getProperty("Email"));
		Password.clear();
		Password.sendKeys(config.getProperty("Password"));
		SignIn.click();
		return new Dashboard();
	}

	public void RegisterNewMember(String name, String mobile, String email, String password) throws Exception {
		RegisterNewMember.click();
		Name.sendKeys(name);
		Mobile.sendKeys(mobile);
		RegisterEmail.sendKeys(email);
		RegisterPassword.sendKeys(password);
		RegisterSignIn.click();
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
		Thread.sleep(1000);
	}


}
