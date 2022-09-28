package testcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.loginPage;

public class baseTest {
	
	public static WebDriver driver;
	
	public static FileInputStream propInput;
	public static Properties prop;
	public static  ExtentSparkReporter spark ;
	public static  ExtentReports extent ;
	public static ExtentTest test;
	
	
	@BeforeTest
	public void before() {
		spark = new ExtentSparkReporter("ExtentSpark.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);
		
	}
		
		@AfterTest
		public void after() {
			extent.flush();
		}
		
	 @BeforeMethod
	 public void beforeMethod(Method method) {
		 test= extent.createTest(method.getName());
	 }
	loginPage obj;
	@BeforeClass
	public void setUp() throws InterruptedException {
		try {
			propInput= new FileInputStream("src/test/java/config/config.properties");
			prop= new Properties();
			prop.load(propInput);
			
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(prop.getProperty("url"));
			System.out.println("opened");
			obj=new loginPage(driver);
			System.out.println("Opening the browser");
			obj.login(prop.getProperty("username"), prop.getProperty("password"));
			Thread.sleep(2000);
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}	
}
	
	
	@AfterClass
	public void close() {
		driver.close();
	}

	public void verifyCurrentUrl(String url) {
		// TODO Auto-generated method stub
		if(!url.equals(driver.getCurrentUrl())){
			test.log(Status.FAIL,"Current URL:"
					+ driver.getCurrentUrl()+ " Expected URL: " + url);
			Assert.assertTrue(false);
			}
		else{
			test.log(Status.PASS,"Current URL:"
					+ driver.getCurrentUrl()+ " Expected URL: " + url);
			}
		
	}
	
	//Reusable method
	public void waitAndClick(By element){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).click();
    }
	public void waitTillClickable(By element){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

}