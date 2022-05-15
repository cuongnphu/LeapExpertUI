package com.leap.pageobject.web;

import com.leap.pageobject.WebBasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends WebBasePage {

    @FindBy(xpath = ".//input[@data-testid='usernameLogin']")
    private WebElement txtUser;

    @FindBy(xpath = ".//input[@data-testid='passwordLogin']")
    private WebElement txtPass;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void login(String username, String password){
        inputText(txtUser, username);
        inputText(txtPass, password);
        inputText(txtPass, Keys.ENTER);
    }



}
