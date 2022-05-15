package com.leap.stepdefs.mobile;

import com.leap.pageobject.mobile.ChattingPage;
import com.leap.stepdefs.AppBaseStep;
import io.cucumber.java.en.When;

public class ChattingStep extends AppBaseStep {

    private ChattingPage chattingPage;

    public ChattingStep() {
        super();
        chattingPage = new ChattingPage(getAppDriver());
    }

    @When("I send a message as {string}")
    public void sendMessage(String sms){
        chattingPage.sendMessage(sms);
    }

    @When("I reply message")
    public void replyMessage(){
        chattingPage.replyCurrentMessage();
    }
}
