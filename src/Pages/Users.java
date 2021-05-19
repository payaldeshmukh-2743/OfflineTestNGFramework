package Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Base.TestBase;

public class Users extends TestBase {
	@FindBy(xpath = "//*[@class='table table-hover']")
	@CacheLookup
	WebElement Table;

	@FindBy(xpath = "//*[@class='content-header']/h1")
	@CacheLookup
	WebElement Users;
	@FindBy(xpath = "//*[@class='box-title']")
	@CacheLookup
	WebElement UserList;
	@FindBy(xpath = "//*[@class='btn btn-block btn-primary btn-sm pull-right']")
	@CacheLookup
	WebElement AddUser;
	@FindBy(xpath = "//*[@class='content-header']/h1")
	@CacheLookup
	WebElement AddUsersHeading;
	@FindBy(xpath = "//*[@class='box-title']")
	@CacheLookup
	WebElement HorizontalForm;

	@FindBy(xpath = "//*[@id='username']")
	@CacheLookup
	WebElement Username;
	@FindBy(xpath = "//*[@id='mobile']")
	@CacheLookup
	WebElement Mobile;
	@FindBy(xpath = "//*[@id='email']")
	@CacheLookup
	WebElement Email;
	@FindBy(xpath = "//*[@id='Female' and @name='gender']")
	@CacheLookup
	WebElement Female_Gender;
	@FindBy(xpath = "//*[@id='Male' and @name='gender']")
	@CacheLookup
	WebElement Male_Gender;

	@FindBy(xpath = "//select[ @class='form-control']")
	@CacheLookup
	WebElement State;
	@FindBy(xpath = "//*[@id='password']")
	@CacheLookup
	WebElement Password;
	@FindBy(xpath = "//*[@class='btn btn-default pull-right' and contains(text(),'Cancel')]")
	@CacheLookup
	WebElement cancel;
	@FindBy(xpath = "//*[@id='submit']")
	@CacheLookup
	WebElement Submit;

	public Users() throws Exception {
		PageFactory.initElements(driver, this);
	}

	public String UsersHeading() {
		String UsersHeading = Users.getText();
		return UsersHeading;
	}

	public String UserListHeading() {
		String UserListHeading = UserList.getText();
		return UserListHeading;
	}

	public void ReadUserListData() {
		List<WebElement> row = Table.findElements(By.tagName("tr"));
		for (int i = 0; i < row.size(); i++) {
			if (i == 0) {
				List<WebElement> column = row.get(i).findElements(By.tagName("th"));
				for (int j = 0; j < column.size(); j++) {
					String Data = column.get(j).getText();

					System.out.print(Data + "\t");

				}
				System.out.println();
			}
			if (i != 0) {
				List<WebElement> column = row.get(i).findElements(By.tagName("td"));
				for (int j = 0; j < column.size(); j++) {
					String Data = column.get(j).getText();

					System.out.print(Data + "\t");

				}
				System.out.println();
			}
		}
		
	}

	public void ClickOnAddUser() {
		AddUser.click();
	}

	public String AddUserHeading() {
		String AddUserHeading = AddUsersHeading.getText();
		return AddUserHeading;
	}

	public String UserFormHeading() {
		String UserFormHeading = HorizontalForm.getText();
		return UserFormHeading;
	}

	public List  GetStateOption() {
		Select StateOption = new Select(State);
		ArrayList<String> ActualStateValue = new ArrayList<String>();

		List<WebElement> State = StateOption.getOptions();
		for (int i = 0; i < State.size(); i++) {
			String Value = State.get(i).getText();
			ActualStateValue.add(Value);

		}
		System.out.println("actual state value==" + ActualStateValue);
		return ActualStateValue;

	}

	public String AddUser(String Username_data, String Mobile_Data, String Email_Data, String Gender_Data,
			String State_Data, String Password_data) {
		Username.sendKeys(Username_data);
		Mobile.sendKeys(Mobile_Data);
		Email.sendKeys(Email_Data);
		if (Gender_Data.equalsIgnoreCase("Male")) {
			Male_Gender.click();
		} else if (Gender_Data.equalsIgnoreCase("Female")) {
			Female_Gender.click();
		}
		Select select = new Select(State);
		select.selectByVisibleText(State_Data);
		Password.sendKeys(Password_data);
		Submit.click();
		String AlertMessage = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		return AlertMessage;

	}
	public void AddUserForDataProvider(String Username_data, String Mobile_Data, String Email_Data, String Gender_Data,
			String State_Data, String Password_data) {
		Username.sendKeys(Username_data);
		Mobile.sendKeys(Mobile_Data);
		Email.sendKeys(Email_Data);
		if (Gender_Data.equalsIgnoreCase("Male")) {
			Male_Gender.click();
		} else if (Gender_Data.equalsIgnoreCase("Female")) {
			Female_Gender.click();
		}
		Select select = new Select(State);
		select.selectByVisibleText(State_Data);
		Password.sendKeys(Password_data);
		Submit.click();
		driver.switchTo().alert().accept();
	
	}

	public String AddAlertMessage(String Username_data, String Mobile_Data, String Email_Data, String Gender_Data,
			String State_Data, String Password_data) {
		AddUser(Username_data, Mobile_Data, Email_Data, Gender_Data, State_Data, Password_data);
		Submit.click();
		driver.navigate().refresh();
		String AlertMessage = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		return AlertMessage;

	}

	public void ClickOnAddUser_cancelButton() {
		cancel.click();
	}

}
