package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import testcases.baseTest;

public class SearchPage extends baseTest {
  
	 WebDriver driver;
	 By filterInput= By.id("employee_name_quick_filter_employee_list_value");
     By searchElement = By.xpath("//*[@id=\"ribbon-actions\"]/ui-view/ul/li[2]/a");
     By employeeButton = By.id("menu_item_128");
     By search=By.xpath("//*[@id=\"quick_search_icon\"]");
	    public SearchPage(WebDriver driver) {
	    	this.driver=driver;
	    }
		public void navigateToEmpPage(){
	    	waitAndClick(employeeButton);
	    }

	    public boolean searchEmp(String name , String location) throws InterruptedException {
	        Thread.sleep(1000);
	        waitAndClick(searchElement);
	        waitAndClick(filterInput);
	        driver.findElement(filterInput).sendKeys(name);
	        waitAndClick(search);
	        waitAndClick(searchElement);
	        Thread.sleep(2000);

	        WebElement employeesTable = driver.findElement(By.id("employeeListTable"));
	        List<WebElement> tableRows = employeesTable.findElements(By.tagName("tr"));

	        for(int i=0; i<tableRows.size(); i++){
	            WebElement row  = tableRows.get(i);

	            System.out.println(row.getText().toString().substring(5) +  " Provided Data : " + name + " " + location);

	            if(row.getText().toString().substring(5).equals(name + " " + location)){
	                return true;
	            }
	        }

	        return false;
	    }
}