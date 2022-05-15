package com.leap.pageobject.web;

import com.leap.pageobject.WebBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage extends WebBasePage {

    @FindBy(xpath = ".//div[contains(@class, 'Account_tab-buttons__tab-on-desktop')]//div//div[contains(text(),'Devices')]")
    private WebElement tabDevices;

    @FindBy(xpath = ".//div[contains(@class,'DeviceTab_title')]//button")
    private WebElement btnLinkDevice;

    @FindBy(xpath = ".//div[contains(@class,'LinkDeviceModal_code-name')]")
    private WebElement txtQrCode;

    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void gotoDevicePage(){
        buttonClick(tabDevices);
    }

    public void clickLinkDevice(){
        buttonClick(btnLinkDevice);
    }

    public String getQrCode(){
        return getText(txtQrCode);
    }

}
