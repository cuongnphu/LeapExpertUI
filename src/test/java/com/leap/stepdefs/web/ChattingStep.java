package com.leap.stepdefs.web;

import com.leap.pageobject.web.ChattingPage;
import com.leap.stepdefs.WebBaseStep;
import io.cucumber.java.en.When;

public class ChattingStep extends WebBaseStep {

    private ChattingPage chattingPage;

    public ChattingStep() {
        super();
        chattingPage = new ChattingPage(getWebDriver());
    }

    @When("I verify last content sms as {string}")
    public void verifyContent(String content){
        chattingPage.validateLastContent(content);
    }

}
