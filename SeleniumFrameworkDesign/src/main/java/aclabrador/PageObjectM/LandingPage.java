package aclabrador.PageObjectM;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import aclabrador.AbstractObjects.AbstractComponent;

public class LandingPage extends AbstractComponent
{
   WebDriver driver;
	
	public LandingPage(WebDriver driver){
		
		super(driver);
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	   
   }
	
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	//Implent lo de arriba usando PageFactory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPass;
	
	@FindBy(id="login")
	WebElement btnlogin;
	
	//div[@class='ng-tns-c4-2 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error']
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMsg;
	
	
	
	public ProductCatalogue loginAplication(String email, String pass) {
		userEmail.sendKeys(email);
		userPass.sendKeys(pass);
		btnlogin.click();
		ProductCatalogue prodCatalogue = new ProductCatalogue(driver);
		return prodCatalogue;
	}
	
	public String getErrorMsg() {
		waitForWebElementAppear(errorMsg);
		return errorMsg.getText();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	
	 
}
