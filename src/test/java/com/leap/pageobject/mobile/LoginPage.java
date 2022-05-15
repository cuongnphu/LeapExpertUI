package com.leap.pageobject.mobile;

import com.leap.pageobject.AppBasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AppBasePage {

    @AndroidFindBy(accessibility = "login_username")
    private MobileElement txtUser;

    @AndroidFindBy(accessibility = "login_password")
    private MobileElement txtPass;

    @AndroidFindBy(accessibility = "login_signIn")
    private MobileElement btnSignIn;

    public LoginPage(AppiumDriver<MobileElement> appDriver) {
        super(appDriver);
        PageFactory.initElements(new AppiumFieldDecorator(appDriver), this);
    }

    public void login(String user, String pass){
        clearText(txtUser);
        inputText(txtUser, user);
        inputText(txtPass, pass);
        buttonClick(btnSignIn);
    }

}
