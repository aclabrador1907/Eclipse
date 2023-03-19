package aclabrador.PageObjectM;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import aclabrador.AbstractObjects.AbstractComponent;

public class ConfirmationPage extends AbstractComponent
{
   WebDriver driver;
   
   @FindBy(xpath="//h1[contains(text(), ' Thankyou for the order. ')]")
   WebElement confirmationMsg;
   
	
	public ConfirmationPage(WebDriver driver){
		
		super(driver);
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	   
   }
	
	
	public String getConfirmationMsg() {
		
		System.out.println(confirmationMsg.getText());
		return confirmationMsg.getText();
	}
	
	
	
	 
}
