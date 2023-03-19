package aclabrador.Tests;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import aclabrador.PageObjectM.CartPage;
import aclabrador.PageObjectM.CheckOutPage;
import aclabrador.PageObjectM.ConfirmationPage;
import aclabrador.PageObjectM.OrderPage;
import aclabrador.PageObjectM.ProductCatalogue;
import aclabrador.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest{
	//String prodName = "ADIDAS ORIGINAL";
	
	@Test(dataProvider = "getData", groups = "Purchase")
	public 	void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {
	    
		ProductCatalogue prodCatalogue = landingP.loginAplication(input.get("email"), input.get("pass"));
		//Llamando a la clase ProductCatalogue para cargar la lista de productos del catalogo
		//Y seleccionar el producto deseado
		prodCatalogue.addProductToCart(input.get("prodName"));
		CartPage cartPage = prodCatalogue.goToCartPage();
		//Clase donde estan todos los producto en el carro
		//Se verifica si esta el producto y se da click para hacer el checkout
		Boolean match = cartPage.elementIsOnCart(input.get("prodName"));
		Assert.assertTrue(match);
		CheckOutPage checkoutPage = cartPage.goToCheckout();
		//clase donde se intruducen los datos para el checkout
		checkoutPage.introduceData("india");
		ConfirmationPage confirmPage = checkoutPage.submitOrder();
		//Clase para la confirmacion de la compra
		//Verificar el mensaje de confirmacion
		String msg = confirmPage.getConfirmationMsg();
		Assert.assertTrue(msg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
	}
	
	//To verify if ADIDAS ORIGINAL is displaying in orders page
	//Depende del metodo submitOrder
	@Test(dependsOnMethods = {"submitOrder"})
	public void verifyProdOrder() {
		
		ProductCatalogue prodCatalogue = landingP.loginAplication("aclabrador1907@gmail.com", "Cecy19*7");
		OrderPage orderPage = prodCatalogue.goToOrderPage();
		Boolean match = orderPage.verifyOrderDisplay("adidas original");
		Assert.assertTrue(match);
		//Imprimiendo nombre del producto
		System.out.println("adidas original");  
		
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		/*HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "aclabrador1907@gmail.com");
		map.put("pass", "Cecy19*7");
		map.put("prodName", "ADIDAS ORIGINAL");
		
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "aclabrador1907@gmail.com");
		map1.put("pass", "Cecy19*7");
		map1.put("prodName", "ZARA COAT 3");
				
		Object[][] data = new Object[][] {{map},{map1}};*/
		
		List<HashMap<String, String>> datafile = getJasonData(System.getProperty("user.dir")+ "//src//test//java//aclabrador//data//PurchaseOrder.json");
		Object[][] data = new Object[][] {{datafile.get(0)},{datafile.get(1)}};
		return data;
		
	}


}
