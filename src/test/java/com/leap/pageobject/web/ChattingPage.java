package com.leap.pageobject.web;

import com.leap.pageobject.WebBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChattingPage extends WebBasePage {

    @FindBy(xpath = "(.//div[@data-testid='message-text'])[last()]")
    private WebElement smsLast;

    public ChattingPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void validateLastContent(String sms){
        validateElement(smsLast, sms, Element_Type.CONTAINS_TEXT_VALUE);
    }

}
