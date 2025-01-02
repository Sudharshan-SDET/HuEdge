package org.test;

import org.pages.AccountActivity;
import org.pages.AccountOverview;
import org.pages.FindTransaction;
import org.pages.OpenAccount;
import org.pages.TransferFund;
import org.testng.annotations.Test;

public class FindTransactionTC extends FindTransaction {

    OpenAccount openAccount = new OpenAccount();
    TransferFund transferFund = new TransferFund();
    AccountOverview accountOverview = new AccountOverview();
    AccountActivity accountActivity = new AccountActivity();

    // 11. Go to "Find Transactions" and find the transaction details by providing
    // different options available on the page and
    // validate any of the Transaction results & details (ID, Date, Desc, Type &
    // Amount)
    @Test
    public void verifyAbleToFindTransactionByDate() {
        String existingAccountNumber = OpenAccount.accountNumb.get(0);
        
        // Navigate to the account overview page
        accountOverview.clickOnAccountsOverview();
        System.out.println("Clicked on account overview");

        // Select the account from the overview using the existing account number
        accountOverview.selectAccountFromAccOveriew(existingAccountNumber);
        System.out.println("Selected existing account");

        // Click on the Find Transactions link
        clickOnFindTransactionlink();
        System.out.println("Clicked on 'Find Transactions' link");

        // Select the account from the dropdown in the Find Transactions page
        selectAnAccount(existingAccountNumber);
        System.out.println("Selected account in 'Find Transactions' page");

        // Enter the current date in the transaction date field and search
        enterCurrentTransactionDate();
        clickOnFindTransBtnByDate();
        System.out.println("Searched for transactions using the current date");

        // Verify the transaction details
        verifyTransactionDetails();
        System.out.println("verified Transaction details");
        // Optional: Validate specific transaction details if needed
      //  String transactionDate = getTrasactionDetailsValue("Date");
        //System.out.println("Transaction Date: " + transactionDate);
    }
}
