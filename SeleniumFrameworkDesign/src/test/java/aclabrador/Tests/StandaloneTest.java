package aclabrador.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));   
		driver.manage().window().maximize();
		
		
		
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("anshika@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Iamking@000");
		driver.findElement(By.id("login")).click();
		String productname = "ADIDAS ORIGINAL";
		
		//Se implementa un mecanismo de espera
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		//Espera hasta que cargue los productos en la pagina
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		//Selecciona todos los productos
		List<WebElement> productos = driver.findElements(By.cssSelector(".mb-3"));
		//ADIDAS ORIGINAL adidas original
		
		//filtra por la lista de productos dada una condicion determinada hasta encontrar el producto, busca el elemento que coincida 
		//con el nombre deseado, dentro de la lista de elementos buscados anteriormente		
		WebElement prod = productos.stream().filter(product-> 
		product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
		
		//Cuando se encuentra el producto deseado, se manda a dar clic en el boton correspondiente
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
				
		//Se espera hasta que se localice el elemento que contiene el mensaje de validacion
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		//animacion de validacion
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		
		//mejorar performance del metodo anterior
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//Ir al carrito para verificar que se adiciono el prod deseado
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		//Obtener la lista de productos en el carrito
		List<WebElement> prodCart  = driver.findElements(By.cssSelector(".cartSection h3"));
		
		//Vericar que el producto deseado este en el carrito
		Boolean match = prodCart.stream().anyMatch(prodc-> prodc.getText().equals(productname));
		//Validar que la verfificacions ser verdadera
		Assert.assertTrue(match);
		
		//Dar click en checkout para hacer la compra
		driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();
		
		//introducir los datos para el checkout
		Actions act = new Actions(driver);
		//Busca el elemento y envia el dato
		act.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		
		//Esperar hasta que el listbox cargue los elementos
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		//Seleccionar el elemento
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		
		//Seleccionar el boton para finalizar la orden
		driver.findElement(By.xpath("//a[contains(text(), 'Place Order ')]")).click();
		
		//Verificar el mensaje de confirmacion
		String msg = driver.findElement(By.xpath("//h1[contains(text(), ' Thankyou for the order. ')]")).getText();
		Assert.assertTrue(msg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println(msg);

	}

}
