package aclabrador.PageObjectM;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import aclabrador.AbstractObjects.AbstractComponent;

public class CartPage extends AbstractComponent
{
   WebDriver driver;
   
   
   @FindBy(css=".cartSection h3")
	List<WebElement> carProducts;
   
   @FindBy(xpath="//button[contains(text(),'Checkout')]")
   WebElement checkOutBtn;
	
	public CartPage(WebDriver driver){
		
		super(driver);
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	   
   }
	
	
	
	
	
	public List<WebElement> getListProduct(){
		
	return carProducts;
		
	}
	
	
	public Boolean elementIsOnCart(String productname) {
		//Vericar que el producto deseado este en el carrito
		Boolean match = carProducts.stream().anyMatch(prodc-> prodc.getText().equalsIgnoreCase(productname));
		return match;
		
		
	}
	
	public CheckOutPage goToCheckout() {
		
		checkOutBtn.click();
		CheckOutPage checkoutPage = new CheckOutPage(driver);
		return checkoutPage;
		
	}
	 
}
