package com.leap.pageobject.web;

import com.leap.pageobject.WebBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends WebBasePage {

    @FindBy(xpath = ".//a[@data-testid='link-to-profile-page']")
    private WebElement iconProfile;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void goToProfilePage(){
        buttonClick(iconProfile);
    }

    public void selectRoomChattingByName(String name){
        String xpath = ".//div[@data-testid='room-name']//span[contains(text(),'" + name + "')]";
        WebElement element = webDriver.findElement(By.xpath(xpath));
        buttonClick(element);
    }

}
