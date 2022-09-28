package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;

import testcases.baseTest;



public class dashboardPage extends baseTest {
	WebDriver driver;
	By search= By.xpath("//input[@id=\"menuQuickSearch_menuQuickSearch\"]");
	By searchText= By.xpath("//div[@class=\"menu-title\"][text()=\"Locations\"]");
	By emp=By.xpath("//a[text()=\"Employee List \"]");
	
	public dashboardPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void searchAndClick() throws InterruptedException {
		waitAndClick(search);
		driver.findElement(search).sendKeys("Locations");
		waitAndClick(searchText);
		
	}
	
	public EmployeePage forEmp() throws InterruptedException {
		waitAndClick(emp);
		return new EmployeePage(driver);
	}
}