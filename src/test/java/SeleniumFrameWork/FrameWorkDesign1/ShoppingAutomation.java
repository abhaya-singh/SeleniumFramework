package SeleniumFrameWork.FrameWorkDesign1;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import SeleniumFrameWork.ReusableMethods.BaseTest;
import SeleniumFrameWork.ReusableMethods.OrderHistory;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ShoppingAutomation extends BaseTest{
       
	@Test (dataProvider="getData",groups={"purchase"})
	public void shopping(HashMap<String,String> input) throws IOException, InterruptedException
	{
		//accessing loginPage directly as it is globally declared in parent class.
		HomePage homePage = loginPage.login(input.get("email"),input.get("password") );
		
		CartVerification cartVerification = homePage.addToCart(input.get("product"));
		homePage.gotoCartPage();

		Boolean match = cartVerification.verifyProduct(input.get("product"));
		Assert.assertTrue(match);

		CheckoutScreen checkoutScreen = cartVerification.gotoCheckout();

		checkoutScreen.verifyCheckout(input.get("country"));
		String msg = checkoutScreen.verifyOrder();
		Assert.assertEquals(msg, "THANKYOU FOR THE ORDER.");
	}

	@Test (dependsOnMethods = {"shopping"} ,dataProvider = "getData" )
	public void orderHistory(HashMap<String, String> input) throws InterruptedException
	{
		HomePage homePage = loginPage.login(input.get("email"),input.get("password"));
		OrderHistory orderHistory=homePage.gotoOrderHistory();
		Boolean boo=orderHistory.verifyOrder(input.get("product"));
		Assert.assertTrue(boo);
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		List<HashMap<String,String>> listData=getJsonDataToHashMap(System.getProperty("user.dir")+"\\src\\test\\java\\testData\\testData.json");
		  
		return new Object[][] {{listData.get(0)}};
	
	}
		 
		/*
		 * HashMap<String, String> map1=new HashMap<String,String>();
		 * map1.put("email","abhay123@gmail.com"); map1.put("password","Abhay@123");
		 * map1.put("product","ZARA COAT 3"); map1.put("country", "India");
		 * 
		 * HashMap<String, String> map2=new HashMap<String,String>();
		 * map2.put("email","abhay123@gmail.com"); map2.put("password","Abhay@123");
		 * map2.put("product","IPHONE 13 PRO"); map2.put("country", "India");
		 * 
		 * return new Object[][] {{map1},{map2}};
		 */
		
		
		  	
	
}
