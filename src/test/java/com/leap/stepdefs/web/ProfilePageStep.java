package com.leap.stepdefs.web;

import com.leap.pageobject.utils.GenericUtils;
import com.leap.pageobject.web.ProfilePage;
import com.leap.stepdefs.WebBaseStep;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class ProfilePageStep extends WebBaseStep {

    private ProfilePage profilePage;

    public ProfilePageStep() {
        super();
        profilePage = new ProfilePage(getWebDriver());
    }

    @When("I go to Device page")
    public void goToDevicePage(){
        profilePage.gotoDevicePage();
    }

    @When("I link device")
    public void linkDevice(){
        profilePage.clickLinkDevice();
        GenericUtils.wait(8000);
    }

    @When("I get QRCode")
    public void getQrCode(){
        String qrCode = profilePage.getQrCode();
        scenarioContext.setContext("qrCode", qrCode);
    }

    @Then("I verify QrCode exist")
    public void verifyQrCode(){
        Assert.assertNotEquals(scenarioContext.getContext("qrCode").toString().length(), 0);
    }
}
