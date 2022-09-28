package pages;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import testcases.baseTest;

public class EmployeePage extends baseTest{
  WebDriver driver;
  
  //WebElements on the Employee page
  By addbtn = By.xpath("//a[@id=\"addEmployeeButton\"]//i");
  By firstName= By.id("first-name-box");
  By lastName= By.id("last-name-box");
  By empId= By.id("employeeId");    //auto assigned
  By joinedDate= By.id("joinedDate");
  By location= By.xpath("//*[@id=\"modal-holder\"]/div/div/div/div[2]/form/oxd-decorator/div/div[2]/div/div[2]/div/div[3]/div/div[1]/button/div/div");
  By save= By.id("modal-save-button");
  By goBack = By.xpath("//*[@id=\"ribbon-actions\"]/ui-view/ul/li[3]/a");
  By empFilter = By.id("emp_search_mdl_employee_name_filter_value");
  By filterSearch = By.xpath("//*[@id=\"employee_list_search_modal\"]/div[2]/a[1]");
  By employeeManagementButton = By.id("menu_item_128");
  public EmployeePage(WebDriver driver) {
	  this.driver=driver;
  }
  
  
  public void addEmp(String NAME, String LNAME, String DATE, String OFFICE) throws InterruptedException {
	  waitAndClick(addbtn);
	  WebDriverWait wait = new WebDriverWait(driver,3);
	  WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(firstName));
	  
	  driver.findElement(firstName).sendKeys(NAME);
	  driver.findElement(lastName).sendKeys(LNAME);
	  driver.findElement(joinedDate).clear();
	  driver.findElement(joinedDate).sendKeys(DATE);  
	  selectLocation(OFFICE);
	  waitAndClick(save);
	  Thread.sleep(3000);
	  waitAndClick(employeeManagementButton);
  }
  
  
  public void selectLocation(String office) throws InterruptedException
	{
		driver.findElement(location).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText(office)).click();
	}
  

  public void clearFilters() throws InterruptedException {
      waitAndClick(goBack);
      waitAndClick(empFilter);
      waitAndClick(filterSearch);
  }
  

  public Boolean verifyEmployee(String name , String location) throws InterruptedException {
      SearchPage search = new SearchPage(driver);
      return search.searchEmp(name , location);
  }
  
}