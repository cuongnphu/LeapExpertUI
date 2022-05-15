package com.leap.stepdefs;

import com.leap.cucumber.ScenarioContext;
import com.leap.helper.PropertiesHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public abstract class AppBaseStep extends GeneralStep{
    protected static AppiumDriver<MobileElement> appStepDrive;
    protected String deviceName = PropertiesHelper.getConfigValue(LEAP_DATA, "deviceName");
    protected String udid = PropertiesHelper.getConfigValue(LEAP_DATA, "udid");
    protected String platformName = PropertiesHelper.getConfigValue(LEAP_DATA, "platformName");
    protected String platformVersion = PropertiesHelper.getConfigValue(LEAP_DATA, "platformVersion");
    protected String appPackage = PropertiesHelper.getConfigValue(LEAP_DATA, "appPackage");
    protected String appActivity = PropertiesHelper.getConfigValue(LEAP_DATA, "appActivity");
    protected String timeWait = PropertiesHelper.getConfigValue(LEAP_DATA, "timeWait");
    protected String host = PropertiesHelper.getConfigValue(LEAP_DATA, "host");

    /**
     * Constructor
     */
    public AppBaseStep() {
        if (appStepDrive == null) {
            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setCapability("deviceName", deviceName);
            cap.setCapability("udid", udid);
            cap.setCapability("platformName", platformName);
            cap.setCapability("platformVersion", platformVersion);
            cap.setCapability("appPackage", appPackage);
            cap.setCapability("appActivity", appActivity);
            cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 120);

            try {
                appStepDrive = new AppiumDriver<MobileElement>(new URL(host), cap);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            appStepDrive.manage().timeouts().implicitlyWait(Integer.parseInt(timeWait), TimeUnit.SECONDS);
        }

        // Constructor share data scenarioContext
        if (scenarioContext == null) {
            scenarioContext = new ScenarioContext();
        }
    }

    /**
     * Setter & Getter
     */
    public static AppiumDriver<MobileElement> getAppDriver() {
        return appStepDrive;
    }

    public static void setWebDriver(AppiumDriver<MobileElement> appDriver) {
        AppBaseStep.appStepDrive = appDriver;
    }

    /**
     * Close/Quit static shared appdriver
     */
    public static void downStep() {
        appStepDrive.close();
        appStepDrive.quit();
        appStepDrive = null;
        scenarioContext = null;
    }

}
