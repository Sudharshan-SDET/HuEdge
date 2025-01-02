package org.test;

import org.Base.ReadExcel;
import org.pages.AccountOverview;
import org.pages.CustomerCreatedPage;
import org.pages.HomePage;
import org.testng.annotations.Test;

public class HomePageTC extends HomePage {
	ReadExcel readExcel = new ReadExcel();
	CustomerCreatedPage custCreated = new CustomerCreatedPage();
	AccountOverview accontOverview = new AccountOverview();

	// 2. Verify that whether the newly registered user can login successfully.
	@Test()
	public void verifyUserAbleToLoginWithRegistredUserCredentials() {
		String userName = readExcel.retrieve("TC_02", "User Name");
		String pwd = readExcel.retrieve("TC_02", "Password");
		enterUsrName(userName);
		enterPwd(pwd);
		clickOnLoginBtn();
		accontOverview.verifyAccountOverviewTitlePresent();
	}
}
