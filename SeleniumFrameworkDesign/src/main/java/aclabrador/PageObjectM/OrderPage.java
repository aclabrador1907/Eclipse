package aclabrador.PageObjectM;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import aclabrador.AbstractObjects.AbstractComponent;

public class OrderPage extends AbstractComponent
{
   WebDriver driver;
   
   //tr td:nth-child(3)
   @FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderProducts;

	
	public OrderPage(WebDriver driver){
		
		super(driver);
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	   
   }
	
	public Boolean verifyOrderDisplay(String productname) {
		//Vericar que el producto deseado este en el carrito
		Boolean match = orderProducts.stream().anyMatch(prodc-> prodc.getText().equalsIgnoreCase(productname));
		return match;
		
		
	}
	
	
	
	
	
	 
}
