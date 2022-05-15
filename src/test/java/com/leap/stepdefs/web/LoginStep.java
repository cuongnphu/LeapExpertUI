package com.leap.stepdefs.web;

import com.leap.pageobject.web.CompanyPage;
import com.leap.pageobject.web.LoginPage;
import com.leap.pageobject.web.OtpPage;
import com.leap.stepdefs.WebBaseStep;
import io.cucumber.java.en.When;


public class LoginStep extends WebBaseStep {

    private LoginPage loginPage;
    private CompanyPage companyPage;
    private OtpPage otpPage;

    public LoginStep() {
        super();
        loginPage = new LoginPage(getWebDriver());
        companyPage = new CompanyPage(getWebDriver());
        otpPage = new OtpPage(getWebDriver());
    }

    @When("I bypass Company with {string}")
    public void inputCompanyName(String name){
        companyPage.inputCompanyName(name);
    }

    @When("I login LeapXpert with username as {string} and password as {string}")
    public void login(String username, String password){
        loginPage.login(username,password);
    }

    @When("I input OTP as {string}")
    public void inputOtp(String otp){
        otpPage.inputOtp(otp);
    }

}
