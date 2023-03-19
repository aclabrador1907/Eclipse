package aclabrador.PageObjectM;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import aclabrador.AbstractObjects.AbstractComponent;

public class CheckOutPage extends AbstractComponent
{
   WebDriver driver;
   
  @FindBy(xpath="//a[contains(text(), 'Place Order ')]")
  WebElement checkoutBtn;
  
     
  //Listbox con los elementos
   By lista = By.cssSelector(".ta-results");
   By india = By.cssSelector(".ta-item:nth-of-type(2)");
   
	
	public CheckOutPage(WebDriver driver){
		
		super(driver);
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	   
   }
	
	
	public void introduceData(String country) {
		
		Actions act = new Actions(driver);
		//Busca el elemento y envia el dato
		act.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), country).build().perform();
		
		//Esperar hasta que el listbox cargue los elementos
		waitForElementAppear(lista);
		
		
		//Seleccionar el elemento
		driver.findElement(india).click();
	}
	
	
	public ConfirmationPage submitOrder() {
		checkoutBtn.click();
		ConfirmationPage confirmPage = new ConfirmationPage(driver);
		return confirmPage;
	}
	
	
	
	
	
}
