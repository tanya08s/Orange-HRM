package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
//import org.testng.annotations.DataProvider;



import pages.loginPage;

public class LoginTestCase extends baseTest{
	loginPage obj;
	
	@BeforeTest
	public void loginTest() throws InterruptedException {
		obj=new loginPage(driver);
		System.out.println("Opening the browser");
		//System.out.println(info);
		obj.login("Admin", "XwFwrZ28@Y");
		 String url=driver.getCurrentUrl();
			String curl= "https://sanjay29-trials76.orangehrmlive.com/client/#/dashboard";
			Assert.assertEquals(curl,url, "passed");
			
	}
}
	

	
	


