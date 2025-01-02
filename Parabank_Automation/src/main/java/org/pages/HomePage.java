package org.pages;

import org.Base.BaseClass;
import org.Base.Reporting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.Status;

public class HomePage extends BaseClass {
	
	//login Details
	String userName = "//input[@type='text']";
	String passWord = "//input[@type='password']";

	String register = "//a[text()='Register']";
	String textCustomerLogin = "//*[text()='Customer Logi']";

	String btnLogin = "//input[@type='submit']";
	RegisterPage registerPage = new RegisterPage();

	public void verifyLandingPage() {
		try {
			String custLoginTxt = driver.findElement(By.xpath(textCustomerLogin)).getText();
			if (custLoginTxt.equals("Customer Login")) {
				System.out.print("User sucessfully landed on Welcome page");
			} else {
				System.out.println("User landed on another page");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void enterUsrName(String uName) {
		try {
			WebElement fieldUName = driver.findElement(By.xpath(userName));
			waitForElementDisplayed(fieldUName);
			typeInput(fieldUName, uName);
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void enterPwd(String pwd) {
		try {
			WebElement fieldPwd = driver.findElement(By.xpath(passWord));
			waitForElementDisplayed(fieldPwd);
			typeInput(fieldPwd, pwd);
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void clickOnLoginBtn() {
		try {
			clickOnElement(driver.findElement(By.xpath(btnLogin)));
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void clickOnRegisterBtnFromWelocmePage() {
		try {
			clickOnElement(driver.findElement(By.xpath(register)));
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}
}
