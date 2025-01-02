package org.test;

import org.Base.ReadExcel;
import org.pages.UpdateProfile;
import org.testng.annotations.Test;

public class UpdateInfoTC extends UpdateProfile {

	ReadExcel readExcel = new ReadExcel();
	
	

	// 3. Update your profile info and validate the success message.
	@Test
	public void verifyAbleToUpdateProfileInfo() {
		String updateAddress = readExcel.retrieve("TC_03", "Update Address");
		String updatePhoneNumber = readExcel.retrieve("TC_03", "Update Phone Number");
		clickOnUpdateContactInfo();
		if (!isInternalErorDisplayed()) {
			updateField("Address", updateAddress);
			updateField("Phone Number", updatePhoneNumber);
		}
		clickOnUpdateProfileBtn();
		if (!isInternalErorDisplayed()) {
			verifyUpdateFieldsMessage("address", "phone number");
		}

	}
}
