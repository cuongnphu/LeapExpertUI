package com.leap.pageobject.web;

import com.leap.pageobject.WebBasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CompanyPage extends WebBasePage {

    @FindBy(xpath = ".//input[@placeholder='Company']")
    private WebElement txtCompany;

    public CompanyPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void inputCompanyName(String name){
        inputText(txtCompany, name);
        inputText(txtCompany, Keys.ENTER);
    }
}
