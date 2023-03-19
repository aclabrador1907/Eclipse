package aclabrador.resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentReportNG 
{
	WebDriver driver;
	//ExtentReports extent;
	
	@BeforeTest
	public static ExtentReports getReportObjet() {
		//ExtentReports, ExtentSparkReporter
		
		String path = System.getProperty("user.dir") + "//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		ExtentReports extent =  new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Ana Cecilia");
		
		return extent;
		
	}
	
	@Test
	public void initalDemo() {
		
		ExtentTest test = getReportObjet().createTest("InictialDemo");
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com");
		System.out.println(driver.getTitle());
		//test.fail("Result do not match");
		driver.close();
		getReportObjet().flush();
		
		
	}
   
}
