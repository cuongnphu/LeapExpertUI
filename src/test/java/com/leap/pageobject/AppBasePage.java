package com.leap.pageobject;

import com.leap.helper.PropertiesHelper;
import com.leap.pageobject.utils.GenericUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.PageFactory;


public abstract class AppBasePage {

    protected static final String LEAP_DATA = "leap.properties";
    protected static AppiumDriver<MobileElement> appDrive;
    protected String timeWait = PropertiesHelper.getConfigValue(LEAP_DATA, "timeWait");

    public enum Element_Type {
        DISPLAYED, IS_ENABLE, IS_SELECTED, HIDDEN, EQUAL_TEXT_VALUE, NOT_EXIST, CONTAINS_TEXT_VALUE, TEXT_VALUE_CONTAINED
    }

    public AppBasePage(AppiumDriver<MobileElement> appDriver) {
        this.appDrive = appDriver;
        PageFactory.initElements(new AppiumFieldDecorator(appDrive), this);
    }

    public String getText(MobileElement appElement) {
        if (appElement.getTagName().equals("input") || appElement.getTagName().equals("textarea")) {
            return appElement.getAttribute("value");
        }else {
            return appElement.getText();
        }
    }

    public void inputText(MobileElement appElement, String text) {
        appElement.sendKeys(text);
    }

    public void inputText(MobileElement appElement, Keys keys) {
        appElement.sendKeys(keys);
    }

    public void keyBoardText(MobileElement appElement, String text){
        appDrive.getKeyboard().sendKeys(text);
        appDrive.getKeyboard().sendKeys(text);
    }

    public void hideKeyboard(){
        appDrive.hideKeyboard();
    }

    public void buttonClick(MobileElement appElement){
        if (appElement.isDisplayed()){
            appElement.click();
        }
    }

    public void clearText(MobileElement appElement){
        appElement.clear();
        GenericUtils.wait(1500);
    }

    public void longTouch(MobileElement appElement){
        TouchActions action = new TouchActions(appDrive);
        action.longPress(appElement);
        action.perform();
    }

}
