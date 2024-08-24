package SeleniumFrameWork.FrameWorkDesign1;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import SeleniumFrameWork.ReusableMethods.*;

public class ErrorValidation extends BaseTest {
	
	@Test (retryAnalyzer = RetryListener.class)
	public void loginErrorValidation() throws IOException, InterruptedException
	{
		
		String userEmail = "abhay1234@gmail.com";
		String pwd = "Abhay@1234";
		//accessing loginPage directly as it is globally declared in parent class.
	    String erroMsg=loginPage.errorValidation(userEmail,pwd);
		Assert.assertEquals("Incorrect email or password.", erroMsg); //entering wrong msg to fail it to get report of failure
		
	}
	
	
}
