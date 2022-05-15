package com.leap.test.web;

import com.leap.pageobject.utils.GenericUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class test {

    public static void main(String[] args) throws MalformedURLException {

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("deviceName", "Oppo3");
        cap.setCapability("udid", "S8BMBUUK4SPZOBR4");
        cap.setCapability("platformName", "Android");
        cap.setCapability("platformVersion", "11");
        cap.setCapability("appPackage", "com.leapxpert.manager.qa");
        cap.setCapability("appActivity", "com.leapxpertapp.MainActivity");
        cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 120);

        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        AppiumDriver<MobileElement> driver = new AppiumDriver<MobileElement>(url, cap);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        MobileElement two = driver.findElementByAccessibilityId("activation.skip");
        two.click();


    }
}
