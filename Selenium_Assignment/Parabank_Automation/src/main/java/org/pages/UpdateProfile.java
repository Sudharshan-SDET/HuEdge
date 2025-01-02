package org.pages;

import org.Base.BaseClass;
import org.Base.Reporting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;

public class UpdateProfile extends BaseClass {
	String updateContactinfo = "//a[text()='Update Contact Info']";
	String updateProfileWidget = "//h1[text() = 'Update Profile']/parent::div";

	String updateFieldfName = "//input[@id = 'customer.firstName']";
	String updateFieldLName = "//input[@id = 'customer.lastName']";
	String updateFieldAddress = "//input[@id = 'customer.address.street']";
	String updateFieldCity = "//input[@id = 'customer.address.city']";
	String updateFieldState = "//input[@id = 'customer.address.state']";
	String updateFieldZipCode = "//input[@id = 'customer.address.zipCode']";
	String updateFieldPhoneNo = "//input[@id = 'customer.phoneNumber']";

	String updateProfileBtn = "//input[@type= 'button']";

	// Successfully updated message locator
	String profileInfoUpdateMessage = "//p[text()='Your updated address and phone number have been added to the system. ']";
	String profileUpdateTitle = "//h1[text()='Profile Updated']";
	
	public void verifyUpdateFieldsMessage(String... fieldsUpdate) {
		try {
			WebElement fieldsUpdatedMsg = driver.findElement(By.xpath(profileInfoUpdateMessage));
			if (fieldsUpdatedMsg.isDisplayed()) {
				String actualMsg = fieldsUpdatedMsg.getText();
				// String updatedFields[] = fieldsUpdatedMsg.getText().split(" ");
				for (String fieldUpdate : fieldsUpdate) {
					if(actualMsg.contains(fieldUpdate))
					{
						System.out.println(fieldUpdate+ " : field Updated sucessfully");
					}else {
						System.out.println(fieldUpdate+ " : failed to update");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void updateField(String updatefieldName, String value) {
		try {
			switch (updatefieldName) {
			case "First Name": {
				WebElement firstname = driver.findElement(By.xpath(updateFieldfName));
				firstname.clear();
				typeInput(firstname, value);
				break;
			}
			case "Last Name": {
				WebElement lastName = driver.findElement(By.xpath(updateFieldLName));
				lastName.clear();
				typeInput(lastName, value);
				break;
			}
			case "Address": {
				WebElement address = driver.findElement(By.xpath(updateFieldAddress));
				waitForElementDisplayed(address);
				address.clear();
				typeInput(address, value);
				break;
			}
			case "City": {
				WebElement city = driver.findElement(By.xpath(updateFieldCity));
				city.clear();
				typeInput(city, value);
				break;
			}
			case "State": {
				WebElement state = driver.findElement(By.xpath(updateFieldState));
				state.clear();
				typeInput(state, value);
				break;
			}
			case "Zip Code": {
				WebElement zcode = driver.findElement(By.xpath(updateFieldZipCode));
				zcode.clear();
				typeInput(zcode, value);
				break;
			}
			case "Phone Number": {
				WebElement phoneNo = driver.findElement(By.xpath(updateFieldPhoneNo));
				waitForElementDisplayed(phoneNo);
				phoneNo.clear();
				typeInput(phoneNo, value);
				break;
			}
			default: {
				System.err.println("Entered field name not valid " + updatefieldName + ". Enter valid field name.");
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void clickOnUpdateProfileBtn() {
		try {
			clickOnElement(driver.findElement(By.xpath(updateProfileBtn)));
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void clickOnUpdateContactInfo() {
		try {
			clickOnElement(driver.findElement(By.xpath(updateContactinfo)));
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}
}