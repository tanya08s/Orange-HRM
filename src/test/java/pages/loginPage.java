package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class loginPage {
	WebDriver driver;

	By email=By.id("txtUsername");
	By password= By.id("txtPassword");
	By login=By.xpath("//div//button");
	public loginPage(WebDriver driver) {
		this.driver= driver;
		System.out.println(driver.getTitle());
		
	}
	

	public dashboardPage login(String user, String pass) throws InterruptedException{
		 WebDriverWait wait = new WebDriverWait(driver,2);
		  WebElement element = wait.until(ExpectedConditions.visibilityOf(driver.findElement(email)));
		driver.findElement(email).sendKeys(user);
		driver.findElement(password).sendKeys(pass);
		driver.findElement(login).click();
		Thread.sleep(3000);
		return new dashboardPage(driver);
	}
}