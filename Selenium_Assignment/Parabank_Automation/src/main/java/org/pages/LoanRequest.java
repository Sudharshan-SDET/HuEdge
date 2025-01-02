package org.pages;

import org.Base.BaseClass;
import org.Base.Reporting;
import org.openqa.selenium.By;
import com.aventstack.extentreports.Status;

public class LoanRequest extends BaseClass {
	String applyForLoanTitle="//h1[normalize-space()='Apply for a Loan']";
	String loanAmount="//input[@id='amount']";
	String downPayment="//input[@id='downPayment']";
	
	String fromAccount="//select[@id='fromAccountId']";
	String applyNowBtn="//input[@value='Apply Now']";
	
	public void clickOnApplyNowBtn() {
		try {
			clickOnElement(driver.findElement(By.xpath(applyNowBtn)));
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}
}
