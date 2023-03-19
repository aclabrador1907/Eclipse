package aclabrador.PageObjectM;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import aclabrador.AbstractObjects.AbstractComponent;

public class ProductCatalogue extends AbstractComponent
{
   WebDriver driver;
	
	public ProductCatalogue(WebDriver driver){
		
		super(driver);
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	   
   }
	//List<WebElement> productos = driver.findElements(By.cssSelector(".mb-3"));
	//Implement PageFactory
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement pageAnimation;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	//By pageAnimation = By.cssSelector(".ng-animating");
	
	
	
	
	public List<WebElement> getProductList() {
		
		waitForElementAppear(productsBy);
		return products;
		
	}
	
	 /*
	     
		
		WebElement prod = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	 */
	
	public WebElement getProductByName(String productname) {
		
		WebElement prod = getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
		
		
		return prod;
	}
	
	
	public void addProductToCart(String productname) throws InterruptedException {
		
		WebElement prod= getProductByName(productname);
		prod.findElement(addToCart).click();
		waitForElementAppear(toastMessage);
		waitForElementDisappear(pageAnimation);
		
	}
	
	
	 
}
