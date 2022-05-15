package com.leap.pageobject.mobile;

import com.leap.pageobject.AppBasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class WelcomePage extends AppBasePage {

    @AndroidFindBy(accessibility = "activation.skip")
    private MobileElement btnSkip;

    public WelcomePage(AppiumDriver<MobileElement> appDriver) {
        super(appDriver);
        PageFactory.initElements(new AppiumFieldDecorator(appDrive), this);
    }

    public void skipWelComePage(){
        buttonClick(btnSkip);
    }
}
