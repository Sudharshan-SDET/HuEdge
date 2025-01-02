package org.test;

import org.Base.ReadExcel;
import org.pages.AccountActivity;
import org.pages.OpenAccount;
import org.testng.annotations.Test;

public class OpenAccountTC extends OpenAccount {
	ReadExcel readExcel = new ReadExcel();
	AccountActivity accountActivity = new AccountActivity();

//4. Open a new savings account and verify whether the account has been created successfully.
	@Test
	public void openSavingAccAndVerifyCreated() {
		clickOnOpenNewAccount();
		selectAccountType(readExcel.retrieve("TC_04", "Account Type"));
		selectExistingAccountFromWhichMinAmountTransfferd();
		clickOnOpenNewAccountBtn();
		verifyCongratulationMessage(readExcel.retrieve("TC_04", "Congratulation Msg"));
		String accountNumber = getCreatedAccountNumber();
		if (accountNumber == null) {
			System.out.println("Account not created");
		} else {
			System.out.println("Saving account created. Account number : " + accountNumber);
		}
	}

// 5. Verify & validate the newly created account details (Acc. No, Account type, Balance and Available).
	@Test(dependsOnMethods = "openSavingAccAndVerifyCreated")
	public void verifyNewlyCreatedAccountDetails() {
		clickOnNewlyCreatedAccountNumber();
		accountActivity.verifyCreatedAccountDetails();
	}
}
