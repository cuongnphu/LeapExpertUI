package com.leap.pageobject.mobile;

import com.leap.pageobject.AppBasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class OtpPage extends AppBasePage {

    @AndroidFindBy(accessibility = "otp_0")
    private MobileElement txtOtp;

    public OtpPage(AppiumDriver<MobileElement> appDriver) {
        super(appDriver);
        PageFactory.initElements(new AppiumFieldDecorator(appDriver), this);
    }

    public void inputOtp(String otp){
        inputText(txtOtp, otp);
    }
}
