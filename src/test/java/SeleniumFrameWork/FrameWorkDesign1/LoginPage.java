package SeleniumFrameWork.FrameWorkDesign1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameWork.ReusableMethods.ReusableMethods;

public class LoginPage extends ReusableMethods{
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPwd;
	
	@FindBy(id="login")
	WebElement loginBtn;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMsg;
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public HomePage login(String email,String pwd) throws InterruptedException
	{
		
		userEmail.sendKeys(email);
		userPwd.sendKeys(pwd);
		loginBtn.click();
		SleepWait();
		HomePage homePage=new HomePage(driver);
		return homePage;
	}
	
	public String errorValidation(String email,String pwd)
	{
		userEmail.sendKeys(email);
		userPwd.sendKeys(pwd);
		loginBtn.click();
		WaitForElementToAppear(errorMsg);
		WaitForElementToAppear(errorMsg);
		
		return errorMsg.getText();
	}
	
	
	
	
}
