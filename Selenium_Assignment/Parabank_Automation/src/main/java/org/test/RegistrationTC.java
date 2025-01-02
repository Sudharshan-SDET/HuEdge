package org.test;

import java.util.Random;

import org.Base.ReadExcel;
import org.apache.logging.log4j.core.appender.RandomAccessFileManager;
import org.pages.CustomerCreatedPage;
import org.pages.HomePage;
import org.pages.RegisterPage;
import org.testng.annotations.Test;

public class RegistrationTC extends RegisterPage {
	ReadExcel readExcel = new ReadExcel();
	RegisterPage regPage = new RegisterPage();
	HomePage homePage = new HomePage();
	CustomerCreatedPage custCreated = new CustomerCreatedPage();

	// 1. Register a user account by providing the necessary details in the
	// sign-up page and verify whether the registration is successful.
	@Test(priority = 1)
	public void verifyUserAbleToRegister() {
		
		homePage.clickOnRegisterBtnFromWelocmePage();
		enterFirstName(readExcel.retrieve("TC_01", "First Name"));
		enterLastName(readExcel.retrieve("TC_01", "Last Name"));
		enterAddress(readExcel.retrieve("TC_01", "Address"));
		enterCityName(readExcel.retrieve("TC_01", "City"));
		enterStateName(readExcel.retrieve("TC_01", "State"));
		enterZipCode(readExcel.retrieve("TC_01", "Zip Code"));
		enterPhoneNumber(readExcel.retrieve("TC_01", "Phone Number"));
		enterSSNNumber(readExcel.retrieve("TC_01", "SSN"));
		enterUserName(readExcel.retrieve("TC_01", "User Name")
				);
		enterPassword(readExcel.retrieve("TC_01", "Password"));
		enterFieldConfirmPassword(readExcel.retrieve("TC_01", "Confirm Password"));
		clickOnRegisterBtn();
		if (isFieldRequiredValidationMsgPresent()) {
			System.out.println("User not registred kindly enter empty fields");
		} else {
			custCreated.verifyAccountCreatedSucessfyllyDisplyed("Your account was created successfully. You are now logged in.");
			custCreated.clickOnLogOut();
		}
	}

}
