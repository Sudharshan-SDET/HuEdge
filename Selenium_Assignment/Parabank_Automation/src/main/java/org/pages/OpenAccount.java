package org.pages;

import java.util.ArrayList;
import java.util.List;

import org.Base.BaseClass;
import org.Base.Reporting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

public class OpenAccount extends BaseClass {

	String accountTypesDD = "//select[@id='type']";
	String fromAccountID = "//select[@id='fromAccountId']";
	String openNewAccountBtn = "//input[@value='Open New Account']";
	String openNewAccount = "//a[text()='Open New Account']";

	String accountOpenedTitle = "//h1[text()='Account Opened!']";
	String congratulationMessage = "//p[text()='Congratulations, your account is now open.']";

	String exeAccNo = "//select[@id='fromAccountId']/option";
	String accountNumber = "//p/a[@id='newAccountId']";

//variables
	public static String createdAccNumber = null;
	public static List<String> accountNumb = null;

	public void clickOnOpenNewAccount() {
		try {
			clickOnElement(driver.findElement(By.xpath(openNewAccount)));
			/*
			 * WebElement accOpenTitle = driver.findElement(By.xpath(accountOpenedTitle));
			 * Assert.assertEquals(accOpenTitle.getText(), "Open New Account");
			 */
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void clickOnOpenNewAccountBtn() {
		try {
			clickOnElement(driver.findElement(By.xpath(openNewAccountBtn)));
			/*
			 * WebElement accOpenedTitle = driver.findElement(By.xpath(accountOpenedTitle));
			 * Assert.assertEquals(accOpenedTitle.getText(), "Account Opened!");
			 */
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void selectAccountType(String accountType) {
		try {
			WebElement accTypes = driver.findElement(By.xpath(accountTypesDD));
			if (accountType.equals("CHECKING")) {
				handleHtmlDropdownListWithVisibleText(accTypes, accountType);
			} else if (accountType.equals("SAVINGS")) {
				handleHtmlDropdownListWithVisibleText(accTypes, accountType);
			} else {
				System.out.println("Invalid account type : " + accountType);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void selectExistingAccountFromWhichMinAmountTransfferd() {
		try {
			WebElement existingAccountDD = driver.findElement(By.xpath(fromAccountID));
			// String accNo = getCreatedAccountNumber();
			String accNo = getListOfExistingAccountNumbers().get(0);
			System.out.println("Existing  Account number :" + Integer.parseInt(accNo));
			handleHtmlDropdownListWithVisibleText(existingAccountDD, accNo);
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public List<String> getListOfExistingAccountNumbers() {
		accountNumb = new ArrayList<String>();
		List<WebElement> acccountNumbers = driver.findElements(By.xpath(exeAccNo));
		if (!acccountNumbers.isEmpty()) {
			for (WebElement accountNumber : acccountNumbers) {
				String accNo = accountNumber.getText();
				accountNumb.add(accNo);
				System.out.println(accNo + " present");
			}
		} else {
			System.out.println("No Account number found");
		}
		return accountNumb;
	}

	public void verifyCongratulationMessage(String expectedCongMessage) {
		try {
			WebElement actualCongratulationMsg = driver.findElement(By.xpath(congratulationMessage));
			wait.until(ExpectedConditions.visibilityOfAllElements(actualCongratulationMsg));
			Assert.assertEquals(actualCongratulationMsg.getText(), expectedCongMessage);
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public String getCreatedAccountNumber() {
		// userAccoNumber = null;
		try {
			//Thread.sleep(2000);
			WebElement accountNo = driver.findElement(By.xpath(accountNumber));
			waitForElementDisplayed(accountNo);
			createdAccNumber = accountNo.getText();
			System.out.println("Extracted created acccount no : " + createdAccNumber);
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
		return createdAccNumber;
	}

	public void clickOnNewlyCreatedAccountNumber() {
		try {

			WebElement createdAccNum = driver.findElement(By.xpath(accountNumber));
			wait.until(ExpectedConditions.visibilityOf(createdAccNum));
			clickOnElement(createdAccNum);
			/*
			 * WebElement accDetailsTitle =
			 * driver.findElement(By.xpath("//h1[text()='Account Details']"));
			 * Assert.assertEquals(accDetailsTitle, "Account Details");
			 */
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}
}
