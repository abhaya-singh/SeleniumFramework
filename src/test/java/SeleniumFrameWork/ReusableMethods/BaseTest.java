package SeleniumFrameWork.ReusableMethods;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumFrameWork.FrameWorkDesign1.LoginPage;

public class BaseTest {

	public WebDriver driver;
	public LoginPage loginPage;

	public WebDriver launchBrowser() throws IOException {

		//reading type of browser from GlobalData file
		
		//jenkins pwd - hahalol*07 OR c6b9c0ff5d7c44f88a2d564b5cd26063
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//SeleniumFrameWork//GlobalData//GlobalData.properties");

		Properties prop = new Properties();
		prop.load(fis);

		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		
		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}

		else if (browserName.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
		} 
		else
		{
			driver = new ChromeDriver();
		}
		
		driver.manage().window().maximize();

		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public LoginPage launchApplication() throws IOException {
		
		driver = launchBrowser();

		loginPage = new LoginPage(driver);
		loginPage.goTo();
		/*
		 * declaring login page object variable as global so that all the child classes
		 * can access this // object variable directly
		 */	

		return loginPage;
	}

	
	  public List<HashMap<String, String>> getJsonDataToHashMap(String FilePath) throws IOException 
	  {
	  
	  //reading data from JSON file
	  
	  //file path is coming from test (Shopping Automation) and //converting JSON
	  //data to string
	  
	  String jsonContent = FileUtils.readFileToString(new
	  File(FilePath),StandardCharsets.UTF_8);
	  
	  ObjectMapper mapper = new ObjectMapper(); //coming from JacksonDataBind dependency
	  
	  List<HashMap<String, String>> jsonData = mapper.readValue(jsonContent,new
	  TypeReference<List<HashMap<String, String>>>(){}); 
	  
	  return jsonData; 
	  
	  }
	  
	  public String getScreenShot(String testCaseName, WebDriver driver) throws IOException
	  {
		 TakesScreenshot ts= (TakesScreenshot)driver;
		 File fl= ts.getScreenshotAs(OutputType.FILE);
		 File destination= new File(System.getProperty("user.dir")+"\\reports\\"+ testCaseName + ".png");
		 FileUtils.copyFile(fl, destination);
		 
		 String filePath= System.getProperty("user.dir")+"\\reports\\"+ testCaseName + ".png";
		 return filePath;
		 
		  
	  }
	 	
	@AfterMethod
	public void closeDriver() {
		driver.quit();
	}

}
