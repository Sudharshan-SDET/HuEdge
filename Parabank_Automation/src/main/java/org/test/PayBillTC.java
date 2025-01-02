package org.test;

import org.Base.ReadExcel;
import org.pages.BillPay;
import org.pages.HomePage;
import org.pages.RegisterPage;
import org.testng.annotations.Test;

public class PayBillTC extends BillPay {
	RegisterPage registerPage = new RegisterPage();
	ReadExcel readExcel = new ReadExcel();

// 8 .Pay the bill amount by providing the necessary details in the Bill Payment Service page and 
	// verify whether the bill payment is successful. (Note: Payee Information
	// Details should not be hard coded in the system.)
	@Test
	public void payBillAndVerifySucessfullMsg() {
		clickOnBillpaybtn();
		enterPayeeName(readExcel.retrieve("TC_06", "Bill Payee Name"));
		enterPayeeAddress(readExcel.retrieve("TC_06", "Bill Payee Address"));
		enterPayeeCity(readExcel.retrieve("TC_06", "Bill Payee City"));
		enterPayeeState(readExcel.retrieve("TC_06", "Bill Payee State"));
		enterPayeeZipCode(readExcel.retrieve("TC_06", "Bill Payee Zip Code"));
		enterPayeePhoneNumber(readExcel.retrieve("TC_06", "Bill Payee Phone Number"));
		enterPayeeAccountNumber(readExcel.retrieve("TC_06", "Bill Payee Account Number"));
		enterPayeeAccountNumberInConfimation(readExcel.retrieve("TC_06", "Bill Payee Account Number"));
		amountToBePaid(readExcel.retrieve("TC_06", "Amount to be Paid"));
		selectFromAccount(readExcel.retrieve("TC_06", "Select account"));
		clickOnSendPaymentBtn();

		//if (registerPage.isFieldRequiredValidationMsgPresent()) {
		//	System.out.println("Enter the empty field or check field name");
		//} else {
		//	clickOnSendPaymentBtn();
		//}
	}
}
