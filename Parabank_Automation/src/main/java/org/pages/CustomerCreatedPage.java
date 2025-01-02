package org.pages;

import org.Base.BaseClass;
import org.Base.Reporting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

public class CustomerCreatedPage extends BaseClass {

	String accountCreatedMsg = "//p[text()='Your account was created successfully. You are now logged in.']";

	String errorMsg = "//p[text()='An internal error has occurred and has been logged.']";

	String welcome = "//p[@class='smallText']/b[text()='Welcome']";
	String usrFNameUsrLastName = "//p[@class='smallText']/b/following-sibling::*[1]";

	String logOutLink = "//a[text()='Log Out']";
	String loginBtn = "//input[@type='submit']";

	public void verifyAccountCreatedSucessfyllyDisplyed(String expectedSuccefullyAccCreatedMsg) {
		try {
			WebElement accountCreatedMessage = driver.findElement(By.xpath(accountCreatedMsg));
			wait.until(ExpectedConditions.visibilityOf(accountCreatedMessage));
			if (accountCreatedMessage.isDisplayed()) {
				String actualSuccefullyAccCreatedMsg = accountCreatedMessage.getText();
				Assert.assertEquals(actualSuccefullyAccCreatedMsg,expectedSuccefullyAccCreatedMsg );
				System.out.println(actualSuccefullyAccCreatedMsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void verifyWelcomeMsgText(String fname, String lName) {
		try {
			WebElement wordWelcome = driver.findElement(By.xpath(welcome));
			WebElement fNameLName = driver.findElement(By.xpath(usrFNameUsrLastName));
			wait.until(ExpectedConditions.visibilityOf(wordWelcome));
			if (wordWelcome.isDisplayed()) {
				String arr[] = fNameLName.getText().split(" ");
				String actualfNameLName = arr[0] + " " + arr[1];
				System.out.println(actualfNameLName);
				Assert.assertEquals("Welcome " + fname + " " + lName, wordWelcome.getText() + " " + actualfNameLName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void clickOnLogOut() {
		try {
			clickOnElement(driver.findElement(By.xpath(logOutLink)));
			WebElement looginBtn = driver.findElement(By.xpath(loginBtn));
			if (looginBtn.isDisplayed()) {
				System.out.println("Logged out sucessfully");
			} else {
				System.err.println("Failed to log out");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

}
