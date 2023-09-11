package driverFactory;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibrary;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class DriverScript extends AppUtil{
	String inputpath="./FileInput/LoginData.xlsx";
	String outputpath="./FileOutput/DataDrivenResults.xlsx";
	ExtentReports report;
	ExtentTest logger;
    @Test
    public void startTest() throws Throwable
    {
    	//define path of html
    	report= new ExtentReports("./Reports/LoginTest.html");
    	//Create reference object
    	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
    	//Count no.of rows in Login sheet
    	int rc = xl.rowCount("Login");
    	Reporter.log("No.of rows in Login Sheet:"+rc,true);
    	for(int i=1;i<=rc;i++)
    	{

    		//test case starts here
    		logger = report.startTest("Login Test");
    		logger.assignAuthor("Ankita");
    		//Read username and password
    		String username = xl.getCellData("Login",i,0);
    		String password = xl.getCellData("Login",i,1);
    		boolean res = FunctionLibrary.adminLogin(username, password);
    		if(res)
    		{
    			logger.log(LogStatus.PASS, "Login success");
    			//If true write as Login success into Result cell
    			xl.setCellData("Login", i, 2, "login success", outputpath);
    			//Write as pass into Status cell
    			xl.setCellData("Login", i, 3, "Pass", outputpath);
    		}
    		else
    		{
    			File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	        FileUtils.copyFile(screen, new File("./Screenshot/Iterations/"+i+"LoginPage.png"));
    		//Capture Error msg
    	        String error_Msg= driver.findElement(By.xpath(conpro.getProperty("ObjError"))).getText();
    	        xl.setCellData("Login", i, 2, error_Msg, outputpath);
    	        xl.setCellData("Login", i, 3, "Fail", outputpath);
    	        logger.log(LogStatus.FAIL, error_Msg);
    		}
    		report.endTest(logger);
    		report.flush();
    	}
    }
}
