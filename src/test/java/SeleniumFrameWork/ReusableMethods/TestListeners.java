package SeleniumFrameWork.ReusableMethods;

import java.awt.event.ItemListener;
import java.io.IOException;

import org.checkerframework.checker.units.qual.t;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import SeleniumFrameWork.GlobalData.TestCaseReports;

public class TestListeners extends BaseTest implements ITestListener {
    WebDriver driver;
	ExtentTest test;
	ExtentReports extent =TestCaseReports.extentReportObject();
	
	ThreadLocal<ExtentTest> threadLocal=new ThreadLocal<ExtentTest>(); // to solve concurrency issue in parallel execution
	
	
	@Override
	public void onTestStart(ITestResult result) {

		test=extent.createTest(result.getMethod().getMethodName());
		threadLocal.set(test); //stores maps, of key value "thread Id of that particular test"
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		test.pass(result.getMethod().getMethodName() +" This Test is Passed.");
	}

	@Override
	public void onTestFailure(ITestResult result) {

		String filePath = null;
		threadLocal.get().fail(result.getThrowable()); //shows the error as well 
		
		try {
		
		driver= (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		//sending driver instance of particular test
		} 
		catch (Exception e) {
			
			e.printStackTrace();
		}
		
		try {
			
			 filePath=getScreenShot(result.getMethod().getMethodName(),driver); //sending TC name
		} 
		catch (IOException e) {
			
			e.printStackTrace();
		}
		threadLocal.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName()); //adding SS captured from local to the report
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

	@Override
	protected void finalize() throws Throwable {
	}

}
