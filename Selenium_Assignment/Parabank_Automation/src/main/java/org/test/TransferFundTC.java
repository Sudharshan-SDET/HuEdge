package org.test;

import org.Base.ReadExcel;
import org.pages.OpenAccount;
import org.pages.TransferFund;
import org.testng.annotations.Test;

public class TransferFundTC extends TransferFund {
	ReadExcel readExcel = new ReadExcel();
	OpenAccount openAccount = new OpenAccount();

	// 6. Transfer funds to the newly created account and
	// validate the success message once the amount transfer is successful. (By
	// using -Transfer Funds option)
	@Test
	public void trnasferFundAndValidateSucessMessage() {
		String existingAcccountNumber = OpenAccount.accountNumb.get(0);
		String generatedAccountNumber = OpenAccount.createdAccNumber;
	
		String amount = readExcel.retrieve("TC_05", "Amount To Transfer");
		String amountToTransfer = amount.substring(0, (amount.length() - 3));
		
		clickOnTransferFund();
		enterAmountToBeTransfer(amountToTransfer);
		transferFundsBetnAccount(existingAcccountNumber, generatedAccountNumber);
		clickOnTransferFundBtn();
		verifyTransactionCompletedMessage(amountToTransfer, generatedAccountNumber, existingAcccountNumber);
	}

}
