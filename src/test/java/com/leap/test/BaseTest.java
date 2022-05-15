package com.leap.test;


import com.leap.stepdefs.WebBaseStep;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.annotations.*;


public abstract class BaseTest {

    protected TestNGCucumberRunner testRunner;
    protected String featureName;

    public String getFeatureName() {
        return featureName;
    }

    @BeforeClass
    public void setup(){
        testRunner = new TestNGCucumberRunner(this.getClass());
    }

    @BeforeMethod
    public void beforeMethod(Object[] params) {
        PickleWrapper pickle = (PickleWrapper) params[0];
        featureName = pickle.getPickle().getName();
    }

    @AfterMethod
    public void tearDownMethod(){
        WebBaseStep.downStep();
    }

    @AfterClass
    public void tearDown(){
        testRunner.finish();
    }

}
