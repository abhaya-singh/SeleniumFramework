package SeleniumFrameWork.FrameWorkDesign1;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameWork.ReusableMethods.ReusableMethods;

public class CartVerification extends ReusableMethods{
	
	WebDriver driver;
	
	public CartVerification(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='cartSection']//h3")
	List<WebElement> cartItems;
	
	@FindBy(xpath="//div[@class='cartSection']")
	WebElement cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutBtn;
	
	public List<WebElement> verifyCart() {
		
		WaitForElementToAppear(cartProducts);
		return cartItems;
	}
	
	public Boolean verifyProduct(String pName)
	{
		Boolean match=verifyCart().stream().anyMatch(s->s.getText().equalsIgnoreCase(pName));
		return match;
		
	}
	
	public CheckoutScreen gotoCheckout()
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", checkoutBtn);
		CheckoutScreen checkoutScreen=new CheckoutScreen(driver);
		return checkoutScreen;
	}
	
	

}
