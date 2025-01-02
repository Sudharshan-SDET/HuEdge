package org.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.Base.BaseClass;
import org.Base.Reporting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FindTransaction extends BaseClass {
	
	String findTransLink="//a[text()='Find Transactions']";
	// Title
	String findTransTitle = "//h1[normalize-space()='Find Transactions']";
	// select Account
	String selectAccountDD = "//select[@id='accountId']";
	// Find By Transaction ID
	String findByTransactionId = "//input[@id='transactionId']";
	String findTransctionBtnTrnasId = "//button[@id='findById']";
	// Find By Transaction Date
	String findByDate = "//input[@id='transactionDate']";
	String findTransctionBtnDate = "//button[@id='findByDate']";
	// find By date range
	String fieldFromDate = "//input[@id='fromDate']";
	String fieldToDate = "//input[@id='fromDate']";
	String findTransactionBtnFindByDateRange = "//button[@id='findByDateRange']";
	// Find By amount
	String findByAmount = "//input[@id='amount']";
	String findTransactionBtnFindByAmout = "//button[@id='findByAmount']";

	// TsanscitionDetails
	String TransactionDetailsKey = "//div[@id='rightPanel']/table/tbody/tr";

	Map<String, String> trsDetails;

	public void selectAnAccount(String accountNo) {
		try {
			WebElement account = driver.findElement(By.xpath(selectAccountDD));
				//driver.navigate().refresh();
				waitForElementDisplayed(account);
				handleHtmlDropdownListWithVisibleText(account, accountNo);
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void clickOnFindTransBtnByID() {
		try {
			WebElement findTransBtn=driver.findElement(By.xpath(findTransctionBtnTrnasId));
			waitForElementDisplayed(findTransBtn);
			clickOnElement(findTransBtn);
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void enterTransctionId(String transctionID) {
		try {
			WebElement fieldFindByTransID = driver.findElement(By.xpath(findByTransactionId));
			fieldFindByTransID.clear();
			typeInput(fieldFindByTransID, transctionID);
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public Map<String, String> verifyTransactionDetails() {
		trsDetails = new HashMap<String, String>();
		try {
			List<WebElement> listOfTransactionDetailsKey = driver.findElements(By.xpath(TransactionDetailsKey));
			for (int i = 0; i < listOfTransactionDetailsKey.size(); i++) {
				if (!listOfTransactionDetailsKey.isEmpty()) {
					System.out.println(listOfTransactionDetailsKey.get(i).getText());
					String tDetails = listOfTransactionDetailsKey.get(i).getText();
					String trnsDetailsKeyPair[] = tDetails.split(":");
					trsDetails.put(trnsDetailsKeyPair[0], trnsDetailsKeyPair[1]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
		return trsDetails;
	}

	public String getTrasactionDetailsValue(String transDetailsKey) {
		Set<Map.Entry<String, String>> transactionDetailsSet = verifyTransactionDetails().entrySet();
		// Iterate over the Set of Map.Entry
		String value = null;
		for (Map.Entry<String, String> entry : transactionDetailsSet) {
			String key = entry.getKey();
			if (transDetailsKey.equals(key)) {
				value = entry.getValue();
			}
		}
		return value;
	}
	
	 // Method to enter the current date in the transaction date field
    public void enterCurrentTransactionDate() {
        try {
            // Get the current date in the required format (e.g., yyyy-MM-dd)
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            String currentDate = dateFormat.format(new Date());

            // Find the transaction date input field and send the current date
            WebElement dateField = driver.findElement(By.xpath(findByDate));
            dateField.clear();
            dateField.sendKeys(currentDate);

            // Log the action
            Reporting.test.log(Status.INFO, "Entered current date: " + currentDate);
        } catch (Exception e) {
            e.printStackTrace();
            Reporting.test.fail(e);
            Reporting.test.log(Status.FAIL, "Failed to enter transaction date");
        }
    }

    // Method to click the "Find by Date" button
    public void clickOnFindTransBtnByDate() {
        try {
            WebElement findTransBtn = driver.findElement(By.xpath(findTransctionBtnDate));
            waitForElementDisplayed(findTransBtn);
            clickOnElement(findTransBtn);
        } catch (Exception e) {
            e.printStackTrace();
            Reporting.test.fail(e);
            Reporting.test.log(Status.FAIL, "Failed to click Find by Date button");
        }
    }

	
	 public void findTransactionByCurrentDate() {
	        clickOnFindTransactionlink(); // Navigates to the "Find Transaction" page
	        enterCurrentTransactionDate(); // Enters the current date in the date field
	        clickOnFindTransBtnByDate();   // Clicks the "Find by Date" button
	    }
	
	public void clickOnFindTransactionlink() {
		clickOnElement(driver.findElement(By.xpath(findTransLink)));
	}
	
}
