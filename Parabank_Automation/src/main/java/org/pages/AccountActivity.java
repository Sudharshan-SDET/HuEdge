 package org.pages;

import java.util.List;

import org.Base.BaseClass;
import org.Base.Reporting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.Status;

public class AccountActivity extends BaseClass {

	String accountDetails = "//div[@id='accountDetails']/table/tbody/tr";
	String fundTrasferReceived = "//tbody/tr/td/a[normalize-space()='Funds Transfer Received']";

	// Transaction Details Tables
	String dateList = "//table[@id='transactionTable']/tbody/tr/td[1]";
	String transaction = "//table[@id='transactionTable']/tbody/tr/td[2]";

	public void verifyCreatedAccountDetails() {
		try {
			List<WebElement> listAccountDetailsKey = driver.findElements(By.xpath(accountDetails));
			wait.until(ExpectedConditions.visibilityOfAllElements(listAccountDetailsKey));
			List<WebElement> listAccountDetailsValue = driver
					.findElements(By.xpath("//div[@id='accountDetails']/table/tbody/tr/td[2]"));
			wait.until(ExpectedConditions.visibilityOfAllElements(listAccountDetailsValue));
			for (int i = 0; i < listAccountDetailsKey.size(); i++) {
				if (!listAccountDetailsKey.isEmpty()) {
					System.out.println(listAccountDetailsKey.get(i).getText());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void clickOnFundTrasferReceived() {
		try {
			WebElement fundTransferLink = driver.findElement(By.xpath(fundTrasferReceived));
			clickOnElement(fundTransferLink);
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void clickOnFundTransferSentLink(String date) {
		try {
			scrollToElement(driver.findElement(By.xpath("//table[@id='transactionTable']")));
			List<WebElement> transctionDates = driver.findElements(By.xpath(dateList));
			// List<WebElement> transctionLinks = driver.findElements(By.xpath(dateList));
			if (!transctionDates.isEmpty()) {
				for (int i = 0; i < transctionDates.size(); i++) {
					WebElement actdate=transctionDates.get(i);
					String transactionDate = actdate.getText();
					System.out.println("Transaction Date : " + transactionDate);
					if (transactionDate.equals(date)) {
						WebElement link = driver
								.findElement(By.xpath("(//table[@id='transactionTable']/tbody/tr/td[2])[1]"));
						scrollToElement(link);
						waitForElementDisplayed(link);
						link.click();
						break;
					} else {
						System.out.println("Not a valid transaction date." + date);
					}
				}
			} else {
				System.out.println("Not transaction found");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}

	}
}
