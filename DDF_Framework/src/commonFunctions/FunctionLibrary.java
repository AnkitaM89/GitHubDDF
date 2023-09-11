package commonFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.AppUtil;

public class FunctionLibrary extends AppUtil {
	public static boolean adminLogin(String username, String password)
	{
		driver.get(conpro.getProperty("Url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath(conpro.getProperty("ObjUser"))).sendKeys(username);
		driver.findElement(By.xpath(conpro.getProperty("ObjPass"))).sendKeys(password);
		driver.findElement(By.xpath(conpro.getProperty("ObjLogin"))).click();
		String expected = "dashboard";
		String actual = driver.getCurrentUrl();
		if(actual.contains(expected))
		{
			Reporter.log("Login success:"+expected+ "   "+actual,true);
			return true;
		}
		else
		{
			Reporter.log("Login failed:"+expected+ "   "+actual,true);
			return false;
		}
	}
}
