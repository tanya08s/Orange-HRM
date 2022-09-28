package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import pages.EmployeePage;
import pages.dashboardPage;

public class EmployeeTestCase extends baseTest {
	dashboardPage objDashboard;
	EmployeePage objEmployee;
  @Test(priority=0, dataProvider="testData")
  public void employee(String NAME, String LNAME, String DATE, String OFFICE) throws InterruptedException {
	  objDashboard= new dashboardPage(driver);
	  objEmployee= new EmployeePage(driver);
	  objDashboard.forEmp();
	  
	  try{
         objEmployee.addEmp(NAME,LNAME,DATE,OFFICE);
         test.log(Status.PASS , "Employee details added successfully");
      }catch (Exception e){
          test.log(Status.FAIL , "Adding Employee Details FAILED with error : \n" + e);
          Assert.fail("Adding Employee failed");
      }
	 
	  try{
          Boolean searchRes = objEmployee.verifyEmployee(NAME+" "+LNAME , OFFICE);
         if(searchRes){
               test.log(Status.PASS , "Employee Verified. Add Successful");
        
        }         else{
             test.log(Status.FAIL , "Could not find employee using search");
             Assert.fail("Could not find employee using search");
         }
     }catch (Exception e){
    	 test.log(Status.FAIL , "Failed to verify Employee  NAME : " +NAME + " "  + LNAME + "STATUS : " + e);
          Assert.fail("Failed to verify employee");
      }
  }

  @Test( dependsOnMethods = "employee")
  public void clearFiltersTest(){
      EmployeePage emp = new EmployeePage(driver);
      try{
          emp.clearFilters();
          test.log(Status.PASS , "Successfully Cleared filters on Employee Page");
      }catch (Exception e){
          test.log(Status.FAIL , "Error showing employee details after adding. ERROR : " + e);
          Assert.fail("Failed to clear filters");
      }
  }


@DataProvider
public Object[][] testData(){
    return new Object[][] {
            {"ABC11111" , "DEF22222" , "2022-09-29","France Office"}
    };
}
}