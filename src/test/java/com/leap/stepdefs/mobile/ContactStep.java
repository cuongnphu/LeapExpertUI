package com.leap.stepdefs.mobile;

import com.leap.pageobject.mobile.*;
import com.leap.stepdefs.AppBaseStep;
import io.cucumber.java.en.When;

public class ContactStep extends AppBaseStep {

    private ContactPage contactPage;

    public ContactStep() {
        super();
        contactPage = new ContactPage(getAppDriver());
    }

    @When("I go to Contact page")
    public void goToContactPage(){
        contactPage.goToContactPage();
    }

    @When("I search Contact by Team as {string}")
    public void searchContactByTeam(String name){
        contactPage.searchContactByTeam(name);
    }

    @When("I select contact for chatting")
    public void selectContact(){
        contactPage.selectContactChat();
    }

}
