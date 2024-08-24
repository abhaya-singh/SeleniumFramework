package SeleniumFrameWork.GlobalData;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestCaseReports {

	public static ExtentReports extentReportObject()
	{
		String path=System.getProperty("user.dir")+"\\reports\\index.html";
		
		ExtentSparkReporter reporter=new ExtentSparkReporter(path); //helps to create html file for report format
		reporter.config().setDocumentTitle("My First Report");
		reporter.config().setReportName("Web Automation Results");
        
		ExtentReports extent=new ExtentReports();//builds reports in html file
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Abhay");
		return extent;

	}


}
