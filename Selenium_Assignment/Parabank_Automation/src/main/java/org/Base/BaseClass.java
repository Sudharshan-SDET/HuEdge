package org.Base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.Status;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Select sel;
	public static Actions act;
	public static JavascriptExecutor js;
	ReadPropertFile readProp = new ReadPropertFile();
	String bName = readProp.getBrowser();
	String testUrl = readProp.getUrl();
	ReadExcel read = new ReadExcel();
	Reporting report = new Reporting();

	@BeforeTest
	public void launchBrowser() {
		try {
			read.readExcel();
			report.onStart(null);
			if (bName.equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions opt = new ChromeOptions();
				opt.addArguments("--remote-allow-origins=*");
				opt.addArguments("--start-maximized");
				driver = new ChromeDriver(opt);
			} else if (bName.equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			} else if (bName.equals("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
			wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			openUrl();
		} catch (Exception e) {
			// Assert.fail();
			if (Reporting.test != null) {
				e.printStackTrace();
				Reporting.test.fail(e);
				Reporting.test.log(Status.FAIL, "Test Failed");
			}
		}
	}

	// Launch URL
	public void openUrl() {
		try {
			driver.get(testUrl);
			String actualTitle = driver.getTitle();
			System.out.println("Title of Page : " + actualTitle);
			// Assert.assertEquals(testUrl, bName);
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void typeInput(WebElement element, String input) {
		try {
			waitForElementDisplayed(element);
			element.clear();
			if (input instanceof String) {
				element.sendKeys(input);
			} else {
				element.sendKeys(String.valueOf(input));
			}
			System.out.println(input + " Sucessfully entered in field");
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void clickOnElement(WebElement element) {
		try {
			if (element.isDisplayed()) {
				js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", element);
				System.out.println("Element clicked sucessfully " + element);
			} else {
				System.out.println("Failed to click on element : " + element);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	// Scroll till visibility of Element
	public void scrollToElement(WebElement element) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			System.out.println("scroll done sucessfully " + element);
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}

	}

	public void takeScreenShotOfThePage() {
		String fileWithPath = System.getProperty("User.dir") + "./Screenshots\\";
		// downcast the driver to access TakesScreenshot method
		TakesScreenshot srcShot = (TakesScreenshot) driver;
		// capture screenshot as output type FILE
		File srcfile = srcShot.getScreenshotAs(OutputType.FILE);
		try {
			// Move image file to new Disc
			File destFile = new File(fileWithPath);
			// save the screenshot taken in destination path
			FileUtils.copyFile(srcfile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public String getRequiredAttributeValue(WebElement element, String attribute) {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		waitForElementDisplayed(element);
		return element.getAttribute(attribute);
	}

	public void handleHtmlDropdownListWithVisibleText(WebElement element, String visibileText) {
		try {
			waitForElementDisplayed(element);
			sel = new Select(element);
			sel.selectByVisibleText(visibileText);
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public boolean waitForElementDisplayed(WebElement ele) {
		try {
			wait.until(ExpectedConditions.visibilityOf(ele));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
			return false;
		}
	}

	// @AfterTest
	public void closeBrowser() {
		try {
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public void isErrorMessagePresent(WebElement element) {
		try {
			if (element.isDisplayed()) {
				String error = element.getText();
				System.out.println(error);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
	}

	public boolean isInternalErorDisplayed() {
		boolean flag = false;
		try {
			WebElement error = driver
					.findElement(By.xpath("//p[text()='An internal error has occurred and has been logged.']"));
			if (error.isDisplayed()) {
				System.out.println("Internal error has been loged");
				flag = true;
			}
			return flag;
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.test.fail(e);
			Reporting.test.log(Status.FAIL, "Test Failed");
		}
		return flag;
	}

}
