package testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pages.LocationPage;
import pages.dashboardPage;
import pages.loginPage;


public class locationTestCase extends baseTest {
	//loginPage objLogin;
	LocationPage objLocation;
	dashboardPage objDashboard;
	@Test(dataProvider="testData")
  public void location(String office, String City, String Phone, String Zip, String Country,String Province) throws InterruptedException {
	    test.log(Status.INFO, "Create Location with "+ City+" , "+Country+" ");
		objDashboard=new dashboardPage(driver);
		objDashboard.searchAndClick();
		objLocation=new LocationPage(driver);
		try {
		super.verifyCurrentUrl(prop.getProperty("LocationUrl"));
		objLocation.Details(office,City,Phone,Zip,Country,Province);
		test.log(Status.PASS , "New Office: "+ office+ "added successfully. ");
		}catch(Exception e) {
			test.log(Status.FAIL , "Adding new office FAILED with error : " + e);
            Assert.fail("Adding New office failed");
		}
		
		try {
		objLocation.verifyLocationTest(office, City, Phone, Zip, Country);
		test.log(Status.PASS , "Location successfully Verified");
		}catch(Exception e) {
			test.log(Status.FAIL , "Failed to verify location");
			Assert.fail("Could not add location");
		}
		
	}	
	@DataProvider
	public Object[][] testData() {
		return new Object[][] { 
			    { "Office New22211","New York","1234567890","100020","United States","Alaska"}
				 };
	}
  
  
}