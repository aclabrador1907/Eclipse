package aclabrador.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import aclabrador.PageObjectM.ProductCatalogue;
import aclabrador.TestComponents.BaseTest;
import aclabrador.TestComponents.Retry;

public class ErrorValidationsTest extends BaseTest{

	
	@Test(groups= {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public 	void validationLogin() throws InterruptedException, IOException {
	    
		String prodName = "ADIDAS ORIGINAL";
		ProductCatalogue prodCatalogue = landingP.loginAplication("aclabrador1907@gmail.com", "Cecy1907");
		Assert.assertEquals("Incorrect email or password no.", landingP.getErrorMsg());
		System.out.println(landingP.getErrorMsg());
			
			
	}


}
