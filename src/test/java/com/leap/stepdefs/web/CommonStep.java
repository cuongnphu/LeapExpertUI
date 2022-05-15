package com.leap.stepdefs.web;

import com.leap.pageobject.web.*;
import com.leap.stepdefs.WebBaseStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;


public class CommonStep extends WebBaseStep {

    private LoginPage loginPage;

    public CommonStep() {
        super();
        loginPage = new LoginPage(getWebDriver());
    }

    @Given("I open LeapXpert website")
    public void openLeapXpert(){
        getWebDriver().get(url);
        getWebDriver().manage().window().maximize();
    }

    @When("I open browser with url")
    public void openWebsiteByUrl(String webUrl){
        getWebDriver().get(webUrl);
        getWebDriver().manage().window().maximize();
    }

    /** still exist keys in localStore */
    @When("I close browser")
    public void closeBrowser(){
        loginPage.closeBrowser();
    }

    /** Clear all keys in localStore */
    @When("I quit browser")
    public void quitBrowser(){
        loginPage.quitBrowser();
    }


}
