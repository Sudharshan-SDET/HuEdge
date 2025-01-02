package org.Base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Reporting implements ITestListener {
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extentReport;
	public static ExtentTest test;
	ReadPropertFile readProp = new ReadPropertFile();

	@Override
	public void onStart(ITestContext testContext) {
		// Log.info("Started");
		String timeStamp = new SimpleDateFormat("yyyyMMdd-HH.mm.ss").format(new Date());
		String repName = "Test-Report-" + timeStamp + ".html";
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\reports\\" + repName);
		htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");
		extentReport = new ExtentReports();
		extentReport.attachReporter(htmlReporter);
		extentReport.setSystemInfo("Host name", "localhost");
		extentReport.setSystemInfo("Environment", "QA");
		extentReport.setSystemInfo("user", "Sudharshan");
		extentReport.setSystemInfo("OS", System.getProperty("OS.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
		extentReport.setSystemInfo("Browser Name", readProp.getBrowser());
		htmlReporter.config().setDocumentTitle("ParaBankAPP");
		htmlReporter.config().setReportName("Functional Test Report");
		htmlReporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.DARK);
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		test = extentReport.createTest(tr.getName());
		test.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
		try {
			String screenshotPath = captureScreenshot(tr.getName());
			test.pass("Screenshot is below:" + test.addScreenCaptureFromPath(screenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String captureScreenshot(String testName) throws IOException {
		String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + testName + ".png";
		TakesScreenshot screenshot = (TakesScreenshot) BaseClass.driver;
		FileUtils.copyFile(screenshot.getScreenshotAs(OutputType.FILE), new File(screenshotPath));
		return screenshotPath;
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		test = extentReport.createTest(tr.getName());
		test.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		try {
			String screenshotPath = captureScreenshot(tr.getName());
			test.fail("Screenshot is below:" + test.addScreenCaptureFromPath(screenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		test = extentReport.createTest(tr.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}

	@Override
	public void onFinish(ITestContext testContext) {
		extentReport.flush();
	}

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

}