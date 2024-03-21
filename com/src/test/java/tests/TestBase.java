package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import Utilities.helper;

public class TestBase {

    public static WebDriver driver;
    public static String downloadPath = System.getProperty("user.dir") + "\\Downloads";

   

    @SuppressWarnings("deprecation")
	@BeforeSuite
    @Parameters({ "browser" })
    public void startDriver(@Optional("edge") String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
           
        } else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
            
        } else if (browserName.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "/drivers/msedgedriver.exe");
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
        driver.navigate().to("https://www.bing.com");
    }

    @AfterMethod
    public void stopDriver() {
        driver.quit();
    }

    // Take a screenshot when a test case fails and add it to the Screenshot folder
    @AfterMethod
    public void captureScreenshotOnFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("Test case failed!");
            System.out.println("Taking a screenshot....");
            helper.captureScreenshot(driver, result.getName());
        }
    }
}