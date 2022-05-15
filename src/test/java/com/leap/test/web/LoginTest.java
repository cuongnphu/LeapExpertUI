package com.leap.test.web;

import com.leap.test.BaseTest;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.ITest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


@CucumberOptions(
        features= "src/test/resources/features/web/Login.feature",
        glue={"com.leap.stepdefs"},
        plugin= {"pretty","html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "rerun:target/cucumber-reports/re-run.txt"}
)
public class LoginTest extends BaseTest implements ITest {

    @Test(description="TC: Login LeapXpert",dataProvider="scenarios")
    public void login(PickleWrapper pickle, FeatureWrapper cucumberFeature) {
        testRunner = new TestNGCucumberRunner(this.getClass());
        testRunner.runScenario(pickle.getPickle());
    }

    @Override
    public String getTestName() {
        return getFeatureName();
    }

    @DataProvider
    public Object[][] scenarios() {
        return testRunner.provideScenarios();
    }

}
