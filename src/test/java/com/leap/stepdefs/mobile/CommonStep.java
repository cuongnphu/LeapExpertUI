package com.leap.stepdefs.mobile;

import com.leap.pageobject.mobile.ActivationPage;
import com.leap.pageobject.mobile.LoginPage;
import com.leap.pageobject.mobile.OtpPage;
import com.leap.pageobject.mobile.WelcomePage;
import com.leap.stepdefs.AppBaseStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class CommonStep extends AppBaseStep {

    private WelcomePage welComePage;
    private ActivationPage activationPage;
    private LoginPage loginPage;
    private OtpPage otpPage;

    public CommonStep() {
        super();
        welComePage = new WelcomePage(getAppDriver());
        activationPage = new ActivationPage(getAppDriver());
        loginPage = new LoginPage(getAppDriver());
        otpPage = new OtpPage(getAppDriver());
    }

    @Given("I skip welcome page")
    public void openLeapXpert(){
        welComePage.skipWelComePage();
    }

    @When("I input activation code")
    public void inputActivationCode(){
        activationPage.inputActivationCode((String) scenarioContext.getContext("qrCode"));
    }

    @When("I Login App as {string} and {string}")
    public void loginApp(String user, String pass){
        loginPage.login(user, pass);
    }

    @When("I input Otp app as {string}")
    public void inputAppOtp(String otp){
        otpPage.inputOtp(otp);
    }

}
