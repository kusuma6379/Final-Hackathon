package StepDefinitions;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.util.Assert;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import Utils.DriverFactory;
import Utils.ExtentReportManager;
import Utils.ScreenshotUtils;

import java.io.File;
import java.nio.file.Files;

public class BaseTest {

    protected WebDriver driver;
    protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    
   

    @BeforeSuite
    public void beforeSuite() {
        extent = ExtentReportManager.getInstance();
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({"baseUrl"})
    public void setup(String baseUrl) {
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();

        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "https://blazedemo.com";
        }
        driver.get(baseUrl);
       


        if (extent == null) {
            extent = ExtentReportManager.getInstance();
        }

        String testName = this.getClass().getSimpleName() + "." + Thread.currentThread().getStackTrace()[2].getMethodName();
        test.set(extent.createTest(testName));
        
        
        
       
    }
    
   

    @AfterMethod(alwaysRun = true)
    public void teardown(ITestResult result) {
       
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ScreenshotUtils.captureScreenshot(driver, result.getName());
            try {
                test.get().fail(result.getThrowable());
                test.get().addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.get().log(Status.PASS, "Test Passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.get().log(Status.SKIP, "Test Skipped");
        }

        DriverFactory.quitDriver();
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownSuite() {
        if (extent != null) {
            extent.flush(); 
        }
    }
}

