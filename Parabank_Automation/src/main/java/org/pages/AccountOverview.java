package org.pages;

import java.util.List;

import org.Base.BaseClass;
import org.Base.Reporting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

public class AccountOverview extends BaseClass {

	String accountOverview = "//a[text()='Accounts Overview']";

	String billPay = "//a[text()='Bill Pay']";
	String findTransaction = "//a[text()='Find Transactions']";
	String logOut = "//a[text()='Log Out']";
	String updateContactIfno = "//a[text()='Update Contact Info']";
	String requestLoan = "//a[text()='Request Loan']";

	// Accounts overview
	String accountOverviewTitle = "//div[@id='showOverview']/h1";  //find this x-path again //h1[normalize-space()='Accounts Overview']
	String accountDetiails = "//table[@id='accountTable']/tbody/tr/td/a[text()='19782']";

	// Accounts
	String accountNo = "//table[@id='accountTable']/tbody/tr/td/a[text()]";

	public void verifyAccountOverviewTitlePresent() {
		try {
			WebElement accountOver = driver.findElement(By.xpath(accountOverviewTitle));
			waitForElementDisplayed(accountOver);
			if (accountOver.isDisplayed()) {
				System.out.println("User sucessfully logged in");
				Assert.assertEquals(accountOver.getText(), "Accounts Overview");
			} else {
				System.out.println("falied to logged in");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void clickOnAccountsOverview() {
		try {
			WebElement accOverview = driver.findElement(By.xpath(accountOverview));
			waitForElementDisplayed(accOverview);
			clickOnElement(accOverview);
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void selectAccountFromAccOveriew(String accountNumber) {
		try {
			List<WebElement> accounts = driver.findElements(By.xpath(accountNo));
			if (!accounts.isEmpty()) {
				for (WebElement account : accounts) {
					waitForElementDisplayed(driver.findElement(By.xpath("//table[@id='accountTable']")));
					scrollToElement(driver.findElement(By.xpath("//table[@id='accountTable']")));
					waitForElementDisplayed(account);
					if (account.getText().equals(accountNumber)) {
						clickOnElement(account);
						break;
					} else {
						System.out.println("Enter valid account no : " + accountNumber);
					}
				}
			} else {
				System.out.println("Account numbers not found ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}

	}
}
