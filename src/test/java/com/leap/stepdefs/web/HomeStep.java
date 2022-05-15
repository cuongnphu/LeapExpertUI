package com.leap.stepdefs.web;

import com.leap.pageobject.web.HomePage;
import com.leap.stepdefs.WebBaseStep;
import io.cucumber.java.en.When;

public class HomeStep extends WebBaseStep {

    private HomePage homePage;

    public HomeStep() {
        super();
        homePage = new HomePage(getWebDriver());
    }

    @When("I go to Profile page")
    public void goToProfilePage(){
        homePage.goToProfilePage();
    }

    @When("I select channel chatting by {string}")
    public void selectChattingChannelByName(String name){
        homePage.selectRoomChattingByName(name);
    }
}
