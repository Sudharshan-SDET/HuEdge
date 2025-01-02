package org.pages;

import org.Base.BaseClass;
import org.Base.Reporting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;

public class BillPay extends BaseClass {
	String billPaymentServiceTitle = "//h1[text()='Bill Payment Service']";
	String billpaypage =  "//a[text()='Bill Pay']";
	String payeeName = "//input[@name='payee.name']";  ////input[@name='payee.name']
	String payeeAddress = "//input[@name='payee.address.street']";
	String payeeCity = "//input[@name='payee.address.city']";
	String payeeState = "//input[@name='payee.address.state']";
	String payeeCityZipCode = "//input[@name='payee.address.zipCode']";
	String payeePhoneNumber = "//input[@name='payee.phoneNumber']";

	String payeeAccountNumber = "//input[@name='payee.accountNumber']";
	String verifyAccount = "//input[@name='verifyAccount']";

	String fieldamount = "//input[@name='amount']";
	String fromAccount = "//select[@name='fromAccountId']";
	String sendPaymentBtn = "//input[@value='Send Payment']";

	// Bill Complete page locator
	String billPaymentCompleteTitle = "//h1[text()='Bill Payment Complete']";
	String billPaymentTransffredMessage = "(//div[@id='billpayResult']/child::p)[1]";

	public void enterPayeeName(String billPayeeName) {
		try {
			WebElement fieldPayeeName = driver.findElement(By.xpath(payeeName));
			fieldPayeeName.clear();
			typeInput(fieldPayeeName, billPayeeName);
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void enterPayeeAddress(String billPayeeAddress) {
		try {
			WebElement fieldPayeeAdd = driver.findElement(By.xpath(payeeAddress));
			fieldPayeeAdd.clear();
			typeInput(fieldPayeeAdd, billPayeeAddress);
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void enterPayeeCity(String billPayeeCity) {
		try {
			WebElement fieldPayeeCity = driver.findElement(By.xpath(payeeCity));
			fieldPayeeCity.clear();
			typeInput(fieldPayeeCity, billPayeeCity);
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void enterPayeeState(String billPayeeCity) {
		try {
			WebElement fieldPayeeState = driver.findElement(By.xpath(payeeState));
			fieldPayeeState.clear();
			typeInput(fieldPayeeState, billPayeeCity);
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void enterPayeeZipCode(String billPayeeCityZipCode) {
		try {
			WebElement fieldPayeeZipCode = driver.findElement(By.xpath(payeeCityZipCode));
			fieldPayeeZipCode.clear();
			typeInput(fieldPayeeZipCode, billPayeeCityZipCode);
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void enterPayeePhoneNumber(String billPayeePhoneNumber) {
		try {
			WebElement fieldPayeePhoneNumber = driver.findElement(By.xpath(payeePhoneNumber));
			fieldPayeePhoneNumber.clear();
			typeInput(fieldPayeePhoneNumber, billPayeePhoneNumber);
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void enterPayeeAccountNumber(String billPayeeAccountNumber) {
		try {
			WebElement fieldPayeeAccountNumber = driver.findElement(By.xpath(payeeAccountNumber));
			fieldPayeeAccountNumber.clear();
			typeInput(fieldPayeeAccountNumber, billPayeeAccountNumber);
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void enterPayeeAccountNumberInConfimation(String billPayeeAccountNumber) {
		try {
			WebElement fieldPayeeConfirmAccountNumber = driver.findElement(By.xpath(verifyAccount));
			fieldPayeeConfirmAccountNumber.clear();
			typeInput(fieldPayeeConfirmAccountNumber, billPayeeAccountNumber);
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void amountToBePaid(String amount) {
		try {
			WebElement fieldAmount = driver.findElement(By.xpath(fieldamount));
			fieldAmount.clear();
			typeInput(fieldAmount, amount);
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void selectFromAccount(String accountNumber) {
		try {
			WebElement accountDD = driver.findElement(By.xpath(fromAccount));
			handleHtmlDropdownListWithVisibleText(accountDD, accountNumber);
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void clickOnSendPaymentBtn() {
		try {
			clickOnElement(driver.findElement(By.xpath(sendPaymentBtn)));
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void verifyBillSucessfullyTransferredMsg() {
		try {
			WebElement billSuccTranMsg = driver.findElement(By.xpath(billPaymentTransffredMessage));
			if (billSuccTranMsg.isDisplayed()) {
				String billTranmsg = billSuccTranMsg.getText();
				System.out.println("Bill transfer sucessfully : " + billTranmsg);
			} else {
				System.out.println("Transcition failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}
	
	public void clickOnBillpaybtn() {
		try {
			clickOnElement(driver.findElement(By.xpath(billpaypage)));
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}
}
