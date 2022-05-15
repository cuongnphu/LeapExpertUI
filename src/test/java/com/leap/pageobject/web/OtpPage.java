package com.leap.pageobject.web;

import com.leap.pageobject.WebBasePage;
import com.leap.pageobject.utils.GenericUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OtpPage extends WebBasePage {

    @FindBy(xpath = ".//div[contains(@class,'OTPInput_container')]//div[1]//input")
    private WebElement txtOtp;

    public OtpPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void inputOtp(String otp){
        GenericUtils.wait(1500);
        inputText(txtOtp, otp);
        GenericUtils.wait(3000);
    }

}
