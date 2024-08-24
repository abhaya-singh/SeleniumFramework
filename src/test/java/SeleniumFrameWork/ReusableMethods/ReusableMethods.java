package SeleniumFrameWork.ReusableMethods;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ReusableMethods {

	WebDriver driver;
	
	@FindBy(xpath="//button[contains(@routerlink,'cart')]")
	WebElement cartCTA;
	
	@FindBy(xpath="//button[contains(@routerlink,'myorders')]")
	WebElement ordersCTA;
	
	public ReusableMethods(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void WaitForElementToAppear(By element)
	{
		 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(4));
		 wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}
	
	public void WaitForElementToAppear(WebElement element)
	{
		 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(4));
		 wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void SleepWait() throws InterruptedException
	{
		Thread.sleep(2000); //to improve performance
	}
	
	public void gotoCartPage()
	{
		cartCTA.click();
	}
	
	public OrderHistory gotoOrderHistory()
	{
		ordersCTA.click();
	    OrderHistory orderHistory=new OrderHistory(driver);
	    return orderHistory;
	}
	
}
