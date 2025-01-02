package org.pages;

import java.util.List;

import org.Base.BaseClass;
import org.Base.Reporting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;

public class RegisterPage extends BaseClass {
	// Customer Details
	String firstName = "//input[@id='customer.firstName']";
	String lastName = "//input[@id='customer.lastName']";
	String address = "//input[@id='customer.address.street']";
	String city = "//input[@id='customer.address.city']";
	String state = "//input[@id='customer.address.state']";
	String zipCode = "//input[@id='customer.address.zipCode']";
	String phoneNumber = "//input[@id='customer.phoneNumber']";
	String ssn = "//input[@id='customer.ssn']";
	// Login
	String userName = "//input[@id='customer.username']";
	String passWord = "//input[@id='customer.password']";
	String confirmPassword = "//input[@id='repeatedPassword']";

	String registerButton = "//input[@value='Register']";

	String fieldRequiredMsg = "//form[@id='customerForm']/table/tbody/tr/td[3]/span";

	public void enterFirstName(String userFirstName) {
		try {
			WebElement fName = driver.findElement(By.xpath(firstName));
			if (fName.isDisplayed()) {
				typeInput(fName, userFirstName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void enterLastName(String userLastName) {
		try {
			WebElement lName = driver.findElement(By.xpath(lastName));
			if (lName.isDisplayed()) {
				typeInput(lName, userLastName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void enterStateName(String userStatename) {
		try {
			WebElement userCity = driver.findElement(By.xpath(city));
			if (userCity.isDisplayed()) {
				typeInput(userCity, userStatename);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void enterAddress(String userAdd) {
		try {
			WebElement userAddress = driver.findElement(By.xpath(address));
			if (userAddress.isDisplayed()) {
				typeInput(userAddress, userAdd);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void enterCityName(String cityName) {
		try {
			WebElement userCityName = driver.findElement(By.xpath(state));
			if (userCityName.isDisplayed()) {
				typeInput(userCityName, cityName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void enterZipCode(String userCityZipCode) {
		try {
			WebElement fieldZipCode = driver.findElement(By.xpath(zipCode));
			if (fieldZipCode.isDisplayed()) {
				typeInput(fieldZipCode, userCityZipCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void enterPhoneNumber(String userPhoneNumber) {
		try {
			WebElement fieldPhoneNumber = driver.findElement(By.xpath(phoneNumber));
			if (fieldPhoneNumber.isDisplayed()) {
				typeInput(fieldPhoneNumber, userPhoneNumber);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void enterSSNNumber(String ssnNumber) {
		try {
			WebElement fieldSSN = driver.findElement(By.xpath(ssn));
			if (fieldSSN.isDisplayed()) {
				typeInput(fieldSSN, ssnNumber);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void enterUserName(String usrName) {
		//String uName = "John";
		try {
			WebElement fieldUserName = driver.findElement(By.xpath(userName));
			if (fieldUserName.isDisplayed()) {
				typeInput(fieldUserName, usrName);
				// return uName;
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void enterPassword(String pwd) {
		try {
			WebElement fieldPassword = driver.findElement(By.xpath(passWord));
			if (fieldPassword.isDisplayed()) {
				typeInput(fieldPassword, pwd);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void enterFieldConfirmPassword(String confPwd) {
		try {
			WebElement fieldConfirmPassword = driver.findElement(By.xpath(confirmPassword));
			if (fieldConfirmPassword.isDisplayed()) {
				typeInput(fieldConfirmPassword, confPwd);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void clickOnRegisterBtn() {
		try {
			clickOnElement(driver.findElement(By.xpath(registerButton)));
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public boolean isFieldRequiredValidationMsgPresent() {
		boolean flag = false;
		try {
			List<WebElement> fieldReq = driver.findElements(By.xpath(fieldRequiredMsg));
			if (!fieldReq.isEmpty()) {
				for (int i = 0; i < fieldReq.size(); i++) {
					System.out.println("Missing field : " + fieldReq.get(i).getText() + " Kindly enter field Name or check Key Name");
					flag = true;
				}
			} else {
				System.out.println("All Registration fields are entered");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
		return flag;
	}


}
