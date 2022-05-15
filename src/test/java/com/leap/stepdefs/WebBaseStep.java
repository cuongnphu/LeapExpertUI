package com.leap.stepdefs;


import com.leap.cucumber.ScenarioContext;
import com.leap.helper.PropertiesHelper;
import com.kirwa.nxgreport.NXGReports;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.util.concurrent.TimeUnit;


public abstract class WebBaseStep extends GeneralStep{
    protected static WebDriver stepDriver;
    protected static String url = PropertiesHelper.getConfigValue(LEAP_DATA, "url");
    protected String browser = PropertiesHelper.getConfigValue(LEAP_DATA, "browser");
    protected String timeWait = PropertiesHelper.getConfigValue(LEAP_DATA, "timeWait");

    enum BrowserType {
        CHROME, FIREFOX, IE
    }

    /**
     * Constructor
     */
    public WebBaseStep() {
        // Constructor share webDriver
        if (stepDriver == null) {
            BrowserType type = BrowserType.valueOf(browser.toUpperCase());
            switch (type) {
                case CHROME:
                    WebDriverManager.chromedriver().setup();
                    stepDriver = new ChromeDriver();
                    stepDriver.manage().timeouts().implicitlyWait(Integer.parseInt(timeWait), TimeUnit.SECONDS);
                    stepDriver.manage().timeouts().pageLoadTimeout(Integer.parseInt(timeWait), TimeUnit.SECONDS);
                    break;
                case FIREFOX:
                    WebDriverManager.firefoxdriver().setup();
                    stepDriver = new FirefoxDriver();
                    break;
                case IE:
                    WebDriverManager.iedriver().setup();
                    stepDriver = new InternetExplorerDriver();
                    break;
                default:
                    break;
            }

            // Set webdriver NXGReport
            NXGReports.setWebDriver(stepDriver);
        }

        // Constructor share data scenarioContext
        if (scenarioContext == null) {
            scenarioContext = new ScenarioContext();
        }
    }

    /**
     * Setter & Getter
     */
    public static WebDriver getWebDriver() {
        return stepDriver;
    }

    public static void setWebDriver(WebDriver webDriver) {
        WebBaseStep.stepDriver = webDriver;
    }

    /**
     * Close/Quit static shared webdriver
     */
    public static void downStep() {
        stepDriver.close();
        stepDriver.quit();
        stepDriver = null;
        scenarioContext = null;
    }

}
