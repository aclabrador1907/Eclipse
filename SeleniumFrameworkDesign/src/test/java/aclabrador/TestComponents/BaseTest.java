package aclabrador.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import aclabrador.PageObjectM.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingP;
	
	//inicializando el driver
	public WebDriver inicializeDriver() throws IOException {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//aclabrador//resources//GlobalData.properties");
		prop.load(file);
		
		String browser = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			//System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
			driver = new ChromeDriver(options);
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		driver.manage().window().maximize();
		return driver;
	}
	
	
	public List<HashMap<String, String>> getJasonData(String filepath) throws IOException {
		//read json to string
		String jsonContent  = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
		
		//String to hashmap Jackson Databin dependency
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
		});
		
		return data;
		  
	}
	
   public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File fileOrig = ts.getScreenshotAs(OutputType.FILE);
		File fileDest = new File(System.getProperty("user.dir") + "//reports//" + testCaseName+ ".png");
		FileUtils.copyFile(fileOrig, fileDest);
		return System.getProperty("user.dir") + "//reports//" + testCaseName+ ".png";
	}
	
	//Para levantar la app, cargar el driver y la pagina inicial
	//este metodo se va a ejecutar antes de todos los test
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplications() throws IOException {
		
		driver = inicializeDriver();
		landingP = new LandingPage(driver);
		landingP.goTo();
		return landingP;
		
	}
	
	//@AfterMethod(alwaysRun = true)
	public void tearsDown() {
		driver.close();
	}

}
