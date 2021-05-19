package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.TestBase;

public class Operators extends TestBase {
	@FindBy(xpath = "//*[@class='table table-hover']")
	@CacheLookup
	WebElement Table;
	@FindBy(xpath = "//*[@class='content-header']/h1")
	@CacheLookup
	WebElement operators;
	@FindBy(xpath = "//*[@class='box-header']/h3")
	@CacheLookup
	WebElement operatorslist;

	public Operators() throws Exception {
		PageFactory.initElements(driver, this);
	}

	public String operatorsHeading() {
		String operatorsHeading = operators.getText();
		return operatorsHeading;
	}

	public String operator_ListHeading() {
		String operators_ListHeading = operatorslist.getText();
		return operators_ListHeading;
	}

	public void ReadOperatorListData() {
		List<WebElement> row = Table.findElements(By.tagName("tr"));
		for (int i = 0; i < row.size(); i++) {
			List<WebElement> column;
			if (i == 0) {
				column = row.get(i).findElements(By.tagName("th"));

			} else {
				column = row.get(i).findElements(By.tagName("td"));

			}
			for (int j = 0; j < column.size(); j++) {
				System.out.print(column.get(j).getText() + "\t");

			}
			System.out.println();

		}

	}

}
