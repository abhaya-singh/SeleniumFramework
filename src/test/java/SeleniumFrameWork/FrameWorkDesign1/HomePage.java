package SeleniumFrameWork.FrameWorkDesign1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import SeleniumFrameWork.ReusableMethods.ReusableMethods;

public class HomePage extends ReusableMethods{
	
	WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		super(driver);
		this.driver=driver; 
	    PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='card-body']")
	List<WebElement> allProduct; //list of all products
	
	@FindBy(css =".ng-animating")
	WebElement spinner;
	
	By products=By.id("products"); //main division of all products
	By productElement=By.xpath("//h5//b"); //product name tag
	By productName=By.xpath("//div[@class='card-body']//button[@class='btn w-10 rounded' and @style='float: right;']"); //add to cart CTA
	By cartMsgAppear=By.cssSelector("#toast-container"); //added to cart message
	
    public List<WebElement> allProductInCart()
    {
    	WaitForElementToAppear(products);
	    return allProduct;
    }
    
    public WebElement getProductName(String productName)
    {
    	
    	WebElement product=allProductInCart().stream().filter(element->element.findElement(productElement).getText().equals(productName)).findFirst().orElse(null);
    	return product;
    }
    
    public CartVerification addToCart(String name) throws InterruptedException
    {
    	WebElement productEle = getProductName(name);
    	productEle.findElement(productName).click();
    	WaitForElementToAppear(cartMsgAppear);
        SleepWait();
        
        CartVerification cartVerification=new CartVerification(driver);
        return cartVerification;
    }
    
    
    

}
