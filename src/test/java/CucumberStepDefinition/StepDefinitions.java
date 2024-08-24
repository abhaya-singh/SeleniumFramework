package CucumberStepDefinition;

import java.io.IOException;

import org.testng.Assert;

import SeleniumFrameWork.FrameWorkDesign1.CartVerification;
import SeleniumFrameWork.FrameWorkDesign1.CheckoutScreen;
import SeleniumFrameWork.FrameWorkDesign1.HomePage;
import SeleniumFrameWork.FrameWorkDesign1.LoginPage;
import SeleniumFrameWork.ReusableMethods.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions extends BaseTest{
	
	public LoginPage loginPage;
	public HomePage homePage;
	public CartVerification cartVerification;
	public CheckoutScreen checkoutScreen;
	public String invalidCredMsg;
	
	@Given("I am on website Login page")
	public void I_am_on_website_Login_page() throws IOException
	{
	  loginPage=launchApplication();
	}

	@Given("^Login with the given (.+) and password (.+)$")
	public void Login_with_the_given_username_and_password(String username, String password) throws InterruptedException 
	{
	  homePage = loginPage.login(username,password);	
	}
	
	@When("^I add a given product (.+) into the cart$")
	public void I_add_a_given_product_into_the_cart(String product) throws InterruptedException
	{
		cartVerification = homePage.addToCart(product);
		homePage.gotoCartPage();
	}
	
	@And("^checkout for the product (.+) added into the cart for the given (.+)$")
	public void checkout_for_the_product_added_into_the_cart_for_the_given_country(String product,String country) throws InterruptedException
	{
		Boolean match = cartVerification.verifyProduct(product);
		Assert.assertTrue(match);
		checkoutScreen = cartVerification.gotoCheckout();
		checkoutScreen.verifyCheckout(country);
	}
	
	@Then("^display (.+) confirmation message on the checkout screen$")
	public void display_confirmation_message_on_the_checkout_screen(String cucumsg) 
	{
		String msg = checkoutScreen.verifyOrder();
		Assert.assertEquals(msg,cucumsg);
		driver.quit();

	}
	
	@Given("^Login with the incorrect (.+) and password (.+)$")
	public void Login_with_the_incorrect_username_and_password(String username,String password)
	{
		invalidCredMsg=loginPage.errorValidation(username,password);
	}
	
	@Then ("^Display invalid creds message (.+) on login screen$")
	public void Display_invalid_creds_message_on_login_screen(String errorMsg)
	{	
		Assert.assertEquals(errorMsg,invalidCredMsg);
		driver.quit();
	}
}
