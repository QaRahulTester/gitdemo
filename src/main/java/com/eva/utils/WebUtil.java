package com.eva.utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import freemarker.core.ReturnInstruction.Return;

public class WebUtil {
	
	private WebDriver driver;
		
	private ExtentReports extReport;
	private ExtentTest extTest;
	
	
	public WebDriver getDriver() {
       return  driver;
	}     
       
    public ExtentTest getExtentTest() {
    	
    	return extTest;
    } 
    public void createExtentReport(String browserName) {
    	  extReport= new ExtentReports();
    	  ExtentSparkReporter spark= new ExtentSparkReporter("results\\"+browserName+"makemytripAutomationReport.html");
    	  extReport.attachReporter(spark);
    	
    }
    
    public void createExtentTestObject(String TestName) {
    	 extTest= extReport.createTest(TestName);
    	
    }
    public void flushTest() {
    	    extReport.flush();
    	
    }
    public void attachScreenshot(String path) {
    	
    	   extTest.addScreenCaptureFromPath(path);
    }
    
    public WebDriver LaunchBrowser(String browserName) {
    	
    	if (browserName.equalsIgnoreCase("chrome")) {
			driver= new ChromeDriver();
		}else if (browserName.equalsIgnoreCase("firefox")) {
			driver =new FirefoxDriver();
		}else if (browserName.equalsIgnoreCase("edge")) {
			driver =new EdgeDriver();
		}else if (browserName.equalsIgnoreCase("safari")) {
			driver =new SafariDriver();
		}
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    	return driver;
    }
    public void OpenURL(String Url) {
    	try {
    	   driver.get(Url);
    	   extTest.log(Status.INFO, Url+"open successfully");
    	}catch(Exception e) {
    		extTest.log(Status.FAIL, Url+"Failed to opene URL");
    	}
    }
    
    
    
}
