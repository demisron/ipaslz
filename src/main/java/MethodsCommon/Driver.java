package MethodsCommon;

import com.thoughtworks.gauge.BeforeSpec;
import com.thoughtworks.gauge.AfterSpec;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

public class Driver {

    private static final Integer DEFAULT_SECONDS_WAIT = 20;
    public static Integer getDefaultSecondsWait(){ return DEFAULT_SECONDS_WAIT; }

    // Holds the WebDriver instance
    public static WebDriver driver;

    // Initialize a driver instance of required browser
    // Since this does not have a significance in the application's business domain, the BeforeSuite hook is used to instantiate the driver
    @BeforeSpec
    public void initializeDriver(){
        driver = DriverFactory.getDriver();
        driver.manage().timeouts().implicitlyWait(DEFAULT_SECONDS_WAIT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    // Close the driver instance
    @AfterSpec
    public void closeDriver(){
        driver.quit();
    }

}

