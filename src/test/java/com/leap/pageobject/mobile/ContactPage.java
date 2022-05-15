package com.leap.pageobject.mobile;

import com.leap.pageobject.AppBasePage;
import com.leap.pageobject.utils.GenericUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;

public class ContactPage extends AppBasePage {

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='bottomTab_contact']/android.view.ViewGroup")
    private MobileElement tabContact;

    @AndroidFindBy(accessibility = "contact_search")
    private MobileElement txtSearch;

    @AndroidFindBy(accessibility = "chat.team")
    private MobileElement tabTeam;

    @AndroidFindBy(accessibility = "teams_0_0")
    private MobileElement eleTeam0;

    @AndroidFindBy(accessibility = "chat.chat")
    private MobileElement btnChat;

    public ContactPage(AppiumDriver<MobileElement> appDriver) {
        super(appDriver);
        PageFactory.initElements(new AppiumFieldDecorator(appDriver), this);
    }

    public void goToContactPage(){
        buttonClick(tabContact);
    }

    public void searchContactByTeam(String search){
        buttonClick(tabTeam);
        buttonClick(txtSearch);
        inputText(txtSearch, search);
        GenericUtils.wait(6000);
    }

    public void selectContactChat(){
        buttonClick(eleTeam0);
        buttonClick(eleTeam0);
        GenericUtils.wait(2000);
        buttonClick(btnChat);
    }

}
