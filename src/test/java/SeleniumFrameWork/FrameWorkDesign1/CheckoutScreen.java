package SeleniumFrameWork.FrameWorkDesign1;

import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameWork.ReusableMethods.OrderHistory;
import SeleniumFrameWork.ReusableMethods.ReusableMethods;

public class CheckoutScreen extends ReusableMethods {

	WebDriver driver;
	
	public CheckoutScreen(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement countryTextField;
	
	@FindBy(xpath="//button[@class='ta-item list-group-item ng-star-inserted']//span")
	List<WebElement> countryList;
	
	@FindBy(xpath="//a[@class='btnn action__submit ng-star-inserted']")
	WebElement submitBtn;
	
	@FindBy(css=".hero-primary")
	WebElement orderMsg;
	
	public void verifyCheckout(String country) throws InterruptedException
	{
		SleepWait();
		countryTextField.sendKeys(country);
		SleepWait();

		WebElement cntry=countryList.stream().filter(s->s.getText().equalsIgnoreCase(country)).findFirst().orElse(null);
		SleepWait();
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", cntry);
		
		js.executeScript("arguments[0].click();", submitBtn);
		
		SleepWait();
		
	}
	
	public String verifyOrder()
	{
		String msg=orderMsg.getText();
		return msg;
		
	}
	
	public OrderHistory orderHistory()
	{
		gotoOrderHistory();
		
		OrderHistory orderHistory=new OrderHistory(driver);
		return orderHistory;
	}
	
	
	
}
