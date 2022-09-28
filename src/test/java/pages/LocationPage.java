package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import testcases.baseTest;

import java.util.List;
import java.util.concurrent.TimeUnit;
public class LocationPage extends baseTest {
  
	WebDriver driver;
	By addBtn= By.xpath("//i[@class=\"material-icons\"][text()=\"add\"]");
	By name= By.cssSelector("#name");
	By city=By.cssSelector("#city");
	By phone=By.cssSelector("#phone");
	By zip=By.cssSelector("#zipCode");
	String countryList="//ul[@id=\"select-options-844b3be1-6133-f984-6765-7371cc517eca\"]";
	String provinceList="//*[@id=\"select-options-9681f5e0-53dd-8ab9-a254-271ad6868ea2";
	String country="//*[@id=\"countryCode_inputfileddiv\"]/div/input";
	String province="//*[@id=\"state_inputfileddiv\"]/div/input";
	By save= By.xpath("//*[@id=\"locationAddModal\"]/form/div[2]/a[1]");
	By tableElement = By.xpath("//*[@id=\"locationDiv\"]/crud-panel/div/div/list/table");
	public LocationPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void Details(String NAME, String CITY,String PHONE, String ZIP, String COUNTRY,String Province) throws InterruptedException {
		waitAndClick(addBtn);
		waitAndClick(name);
		driver.findElement(name).sendKeys(NAME);
		driver.findElement(city).sendKeys(CITY);
		driver.findElement(phone).sendKeys(PHONE);
		driver.findElement(zip).sendKeys(ZIP);
		
		dropDown(countryList,country, COUNTRY);
		dropDown(provinceList,province,Province);
		driver.findElement(save).click();
		driver.navigate().to("https://sanjay29-trials76.orangehrmlive.com/client/#/dashboard");
		Thread.sleep(2000);
	}
	
	public void dropDown(String itemList,String input, String chosen ) {
		List<WebElement> opt = driver.findElements(By.xpath(itemList).tagName("li") );
		
		System.out.println(opt.size());
		System.out.println("entered");
		System.out.println(opt.size());
		driver.findElement(By.xpath(input)).click();
	      for( int j = 0; j< opt.size();j++){
	         if( opt.get(j).getText().equals(chosen)){
	            opt.get(j).click();
	            break; 
	         }
	      }
	}
	
	public boolean verifyLocationTest(String office , String city , String phone , String zip , String country){

		driver.get(prop.getProperty("LocationUrl"));
		test.log(Status.INFO , "Details : " + office + " " + zip + " " + city + " " + phone + " " + country);

		waitTillClickable(tableElement);
		WebElement table = driver.findElement(tableElement);
		List<WebElement> rows = table.findElements(By.tagName("span"));
		for(WebElement elem : rows){
			if(elem.getText().toString().equals(office)){
				
				return true;
			}
		}
		return false;
	}
}