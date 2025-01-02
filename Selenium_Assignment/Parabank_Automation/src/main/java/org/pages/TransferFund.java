package org.pages;

import org.Base.BaseClass;
import org.Base.Reporting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;

public class TransferFund extends BaseClass {
	String titleTranferFunds = "//h1[normalize-space()='Transfer Funds']";
	String amount = "//input[@id='amount']";
	String fromAccountDD = "//select[@id='fromAccountId']";
	String toAccountID = "//select[@id='toAccountId']";
	String tansferFundsLink = "//a[text()='Transfer Funds']";
	String transferFundBtn = "//input[@value='Transfer']";

	String transferCompleteMsg = "(//div[@id='showResult']/child::p)[1]";

	public void clickOnTransferFund() {
		try {
			clickOnElement(driver.findElement(By.xpath(tansferFundsLink)));
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void transferFundsBetnAccount(String existingAccountNumber, String newAccountNumber) {
		try {
			WebElement fromDD = driver.findElement(By.xpath(fromAccountDD));
			WebElement toDD = driver.findElement(By.xpath(toAccountID));
			handleHtmlDropdownListWithVisibleText(fromDD, existingAccountNumber);
			handleHtmlDropdownListWithVisibleText(toDD, newAccountNumber);
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void clickOnTransferFundBtn() {
		try {
			clickOnElement(driver.findElement(By.xpath(transferFundBtn)));
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void enterAmountToBeTransfer(String amountToBeTransfer) {
		try {
			WebElement fiedlAmount = driver.findElement(By.xpath(amount));
			typeInput(fiedlAmount, amountToBeTransfer);
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void verifyTransactionCompletedMessage(String transferedAmount, String accFromMoneySend,
			String amountReceivedAcc) {
		try {
			WebElement tansferMsg = driver.findElement(By.xpath(transferCompleteMsg));
			waitForElementDisplayed(tansferMsg);
			String msg = tansferMsg.getText();
			if (msg.contains(transferedAmount) && msg.contains(amountReceivedAcc) && msg.contains(accFromMoneySend)) {
				System.out.println(transferedAmount + ".00" + " has been transferred from account #" + amountReceivedAcc
						+ " " + "to account #" + accFromMoneySend );
			} else {
				System.out.println("Transaction Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public String getTransactionIDOnSuceefullyFundTransfer() {
		String transactionId = null;
		try {
			// xpath yet to find app throwing error
			WebElement transaId = driver.findElement(By.xpath(""));
			if (transaId.isDisplayed()) {
				transactionId = transaId.getText();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
		return transactionId;
	}
}
