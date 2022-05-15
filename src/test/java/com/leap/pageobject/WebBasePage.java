package com.leap.pageobject;

import com.leap.helper.PropertiesHelper;
import com.leap.pageobject.utils.GenericUtils;
import com.kirwa.nxgreport.NXGReports;
import com.kirwa.nxgreport.logging.LogAs;
import com.kirwa.nxgreport.selenium.reports.CaptureScreen;
import org.openqa.selenium.*;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;


public abstract class WebBasePage {

    protected static final String LEAP_DATA = "leap.properties";
    protected static WebDriver webDriver;
    protected String timeWait = PropertiesHelper.getConfigValue(LEAP_DATA, "timeWait");

    public enum Element_Type {
        DISPLAYED, IS_ENABLE, IS_SELECTED, HIDDEN, EQUAL_TEXT_VALUE, NOT_EXIST, CONTAINS_TEXT_VALUE, TEXT_VALUE_CONTAINED
    }

    /**
     * Constructor
     * @param webDriver
     */
    public WebBasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
    }

    /**
     * Get url in Browser
     * @param url
     */
    public void getUrl(String url) {
        webDriver.get(url);
    }

    /**
     * Validate WebElement with expected value
     */
    public void validateElement(WebElement webElement, String expected, Element_Type type) {
        switch (type) {
            case DISPLAYED:
                try {
                    Assert.assertTrue(webElement.isDisplayed(), expected + " is not displayed. ");
                } catch (NoSuchElementException e) {
                    NXGReports.addStep(expected + " is not found", LogAs.FAILED,
                            new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
                    throw new AssertionError(e.getMessage());
                } catch (AssertionError e) {
                    NXGReports.addStep(e.getMessage(), LogAs.FAILED,
                            new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
                    throw new AssertionError(e.getMessage());
                }
                break;
            case IS_ENABLE:
                try {
                    Assert.assertTrue(webElement.isEnabled(), expected + " is not enabled.");
                } catch (NoSuchElementException e) {
                    NXGReports.addStep(expected + " is not found", LogAs.FAILED,
                            new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
                    throw new AssertionError(e.getMessage());
                } catch (AssertionError e) {
                    NXGReports.addStep(e.getMessage(), LogAs.FAILED,
                            new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
                    throw new AssertionError(e.getMessage());
                }
                break;
            case IS_SELECTED:
                try {
                    Assert.assertTrue(webElement.isSelected(), expected + " is not selected  ");
                } catch (NoSuchElementException e) {
                    NXGReports.addStep(expected + " is not found", LogAs.FAILED,
                            new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
                    throw new AssertionError(e.getMessage());
                } catch (AssertionError e) {
                    NXGReports.addStep(e.getMessage(), LogAs.FAILED,
                            new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
                    throw new AssertionError(e.getMessage());
                }
                break;
            case HIDDEN:
                try {
                    Assert.assertFalse(webElement.isDisplayed(), expected + " is not hidden.");
                } catch (NoSuchElementException e) {
                    NXGReports.addStep(expected + " is not found", LogAs.FAILED,
                            new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
                    throw new AssertionError(e.getMessage());
                } catch (AssertionError e) {
                    NXGReports.addStep(e.getMessage(), LogAs.FAILED,
                            new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
                    throw new AssertionError(e.getMessage());
                }
                break;
            case NOT_EXIST:
                try {
                    boolean isPresent = checkElementToBeClickable(webElement);
                    Assert.assertFalse(isPresent, expected + " does exist.");
                } catch (NoSuchElementException e) {
                    NXGReports.addStep(expected + "exists", LogAs.FAILED,
                            new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
                    throw new AssertionError(e.getMessage());
                } catch (AssertionError e) {
                    NXGReports.addStep(e.getMessage(), LogAs.FAILED,
                            new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
                    throw new AssertionError(e.getMessage());
                }
                break;
            case EQUAL_TEXT_VALUE:
                try {
                    Assert.assertEquals(getText(webElement), expected);
                } catch (NoSuchElementException e) {
                    NXGReports.addStep(expected + " is not found", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
                    throw new AssertionError(e.getMessage());
                } catch (AssertionError e) {
                    NXGReports.addStep(e.getMessage(), LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
                    throw new AssertionError(e.getMessage());
                }
                break;
            case CONTAINS_TEXT_VALUE:
                try {
                    Assert.assertTrue(getText(webElement).contains(expected));
                } catch (NoSuchElementException e) {
                    NXGReports.addStep(expected + " is not found", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
                    throw new AssertionError(e.getMessage());
                } catch (AssertionError e) {
                    NXGReports.addStep(e.getMessage(), LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
                    throw new AssertionError(e.getMessage());
                }
                break;
            case TEXT_VALUE_CONTAINED:
                try {
                    Assert.assertTrue(expected.contains(getText(webElement)));
                } catch (NoSuchElementException e) {
                    NXGReports.addStep(expected + " is not found", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
                    throw new AssertionError(e.getMessage());
                } catch (AssertionError e) {
                    NXGReports.addStep(e.getMessage(), LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
                    throw new AssertionError(e.getMessage());
                }
                break;
            default:
                break;
        }
    }

    /**
     * GET Element text
     */
    public String getText(WebElement webElement) {
        if (webElement.getTagName().equals("input") || webElement.getTagName().equals("textarea")) {
            return webElement.getAttribute("value");
        }else {
            return webElement.getText();
        }
    }

    /**
     * SELECT option
     */
    public void selectOptionByText(WebElement ele, String item) {
        Select select = new Select(ele);
        select.selectByVisibleText(item);
    }

    public void selectOptionByValue(WebElement ele, String val) {
        Select select = new Select(ele);
        select.selectByValue(val);
    }

    public void selectOptionByIndex(WebElement ele, int index) {
        Select select = new Select(ele);
        select.selectByIndex(index);
    }

    /**
     * Windows / Tab in Browser
     */
    public void switchToOtherTab(int tabIndex) {
        List<String> tabs = new ArrayList<String>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(tabIndex));
    }

    public void switchToOtherTabByTitle(String title) {
        List<String> tabs = new ArrayList<String>(webDriver.getWindowHandles());
        for (String winTab : tabs) {
            if (webDriver.switchTo().window(winTab).getTitle().equals(title)) {
                break;
            } else {
                webDriver.switchTo().defaultContent();
            }
        }
    }

    // Close tab
    public void closeOtherTabByIndex(int tabIndex) {
        List<String> tabs = new ArrayList<String>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(tabIndex));
        webDriver.close();
        switchToOtherTab(0);
    }

    public void closeOtherTabByTitle(String title) {
        List<String> tabs = new ArrayList<String>(webDriver.getWindowHandles());
        for (String winTab : tabs) {
            if (webDriver.switchTo().window(winTab).getTitle().equals(title)) {
                webDriver.close();
                switchToOtherTab(0);
                break;
            } else {
                webDriver.switchTo().defaultContent();
            }
        }
    }

    // Open new Tab
    public void openNewTab(String url) {
        ((JavascriptExecutor) webDriver).executeScript("window.open()");
        switchToOtherTab(1);
        webDriver.get(url);
    }

    public void openNewTabBlank() {
        ((JavascriptExecutor) webDriver).executeScript("window.open()");
    }

    // Close browser - still exist keys in localStore
    public void closeBrowser() {
        openNewTabBlank();
        webDriver.close();
        switchToOtherTab(0);
    }

    // Quit browser - Clear all keys in localStore
    public void quitBrowser() {
        openNewTabBlank();
        webDriver.close();
        switchToOtherTab(0);
        clearLocalStore();
    }

    /**
     * Wait until Element
     */
    public void waitUntilElementClickable(WebElement webElement, long timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, timeOut);
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
        } catch (TimeoutException e) {
            NXGReports.addStep("Unable to check element is clickable", false);
        }
    }

    // Wait until Element Text present
    public void waitUntilTextPresent(WebElement webElement, long timeOut, String text) {
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, timeOut);
            wait.until(ExpectedConditions.textToBePresentInElement(webElement, text));
        } catch (TimeoutException e) {
            NXGReports.addStep(e.getMessage(), LogAs.FAILED,
                    new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
            throw new AssertionError(e.getMessage());
        }
    }

    // Wait until element hidden
    public void waitUntilElementHidden(By by, long timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, timeOut);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (TimeoutException e) {
            NXGReports.addStep(e.getMessage(), LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
            throw new AssertionError(e.getMessage());
        }
    }

    /**
     * Switch to other frame
     */
    public void switchToFrame(String name) {
        webDriver.switchTo().frame(name);
    }

    public void switchToFrame(int id) {
        webDriver.switchTo().frame(id);
    }

    public void switchToFrame(WebElement eleFrame) {
        webDriver.switchTo().frame(eleFrame);
    }

    public void switchToDefaultFrame() {
        webDriver.switchTo().defaultContent();
    }

    /**
     * Verify CSS value of element
     */
    public void verifyCssValue(WebElement webElement, String cssName, String expected) {
        try {
            String actualValue = webElement.getCssValue(cssName);
//            if(cssName.contains("color")){
//                actualValue = GenericLib.parseRgbTohex(actualValue);
//            }
            Assert.assertEquals(actualValue, expected);
        } catch (AssertionError e) {
            NXGReports.addStep(e.getMessage(), LogAs.FAILED,
                    new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
            throw new AssertionError(e.getMessage());
        }
    }

    /**
     * Action with WebElement
     */
    public void inputText(WebElement webElement, String text) {
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, Integer.parseInt(timeWait));
            wait.until(ExpectedConditions.visibilityOf(webElement));
            webElement.sendKeys(text);
        } catch (TimeoutException e) {
            NXGReports.addStep("TextBox " + webElement + " is not display", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
        }
    }

    public void inputText(WebElement webElement, Keys keys) {
        WebDriverWait wait = new WebDriverWait(webDriver, Integer.parseInt(timeWait));
        wait.until(ExpectedConditions.visibilityOf(webElement));
        webElement.sendKeys(keys);
    }

    public void clearText(WebElement webElement){
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, Integer.parseInt(timeWait));
            wait.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
        } catch (TimeoutException e) {
            NXGReports.addStep("TextBox " + webElement + " is not display", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
        }
    }

    // button click
    public void buttonClick(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, Integer.parseInt(timeWait));
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
            Actions actions = new Actions(webDriver);
            actions.moveToElement(webElement);
            actions.perform();
            webElement.click();
        } catch (TimeoutException e) {
            NXGReports.addStep("Button " + webElement + " is not displayed for Clicking", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
        }
    }

    /**
     * Wait for javascript to load
     */
    public boolean wait_page_load() {
        WebDriverWait wait = new WebDriverWait(webDriver, Integer.parseInt(timeWait));
        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    // no jQuery present
                    return true;
                }
            }
        };

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    // wait page loading with loadingIcon
    public void waitPageLoading(WebElement loadingIcon, long timeOut) {
        if (loadingIcon != null) {
            WebDriverWait wait = new WebDriverWait(webDriver, timeOut);
            wait.until(ExpectedConditions.stalenessOf(loadingIcon));
        }
    }

    /**
     * Select Random option in dropdown
     */
    public String selectRandomOption(WebElement dropdown) {
        try {
            wait_page_load();
            WebDriverWait wait = new WebDriverWait(webDriver, Integer.parseInt(timeWait));
            wait.until(ExpectedConditions.visibilityOf(dropdown));
            Select select = new Select(dropdown);
            List<WebElement> options = select.getOptions();
            int count = 0;
            while (options.size() == 0 && count < 3){
                GenericUtils.wait(2000);
                select = new Select(dropdown);
                options = select.getOptions();
                count++;
            }
            int optionIndex = 0;
            if(options.size() == 1){
                optionIndex = 0;
            }else{
               optionIndex = GenericUtils.getRandomNumberInRange(0, options.size() - 1);
            }
            select.selectByIndex(optionIndex);
            String optionValue = select.getOptions().get(optionIndex).getText();
            return optionValue;
        } catch (TimeoutException e) {
            NXGReports.addStep("Dropdown " + dropdown + " is not displayed for selecting", LogAs.FAILED,
                    new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
        }

        return "No option selected";
    }

    /**
     * Upload file to Upload popup
     */
    public void uploadImage(WebElement webElement, String pathFile) {
        webElement.sendKeys(pathFile);
    }

    /**
     * Check element exist
     */
    public boolean checkElementToBeClickable(WebElement webElement) {
        Boolean isClickable = false;
        waitUntilElementClickable(webElement, Long.parseLong(timeWait));
        try {
            if (webElement.isDisplayed()) {
                isClickable = true;
            }
        } catch (NoSuchElementException e) {
            return false;
        }

        return isClickable;
    }

    /**
     * Compare not equal between 2 int
     */
    public boolean compareNumberNotEqual(int first, int second) {
        return first != second;
    }

    /**
     * Compare equal between 2 int
     */
    public boolean compareNumberEqual(int first, int second) {
        return first == second;
    }

    /**
     * Get localStore by key
     */
    public String getLocalStorageWeb(String key) {
        // Initialize WebStore & LocalStore
        WebStorage webStorage = (WebStorage) new Augmenter().augment(webDriver);
        LocalStorage localStorage = webStorage.getLocalStorage();

        // Get value for key
        String value = localStorage.getItem(key);
        return value;
    }

    // Clear web local_Store
    public void clearLocalStore() {
        // Initialize WebStore & LocalStore
        WebStorage webStorage = (WebStorage) new Augmenter().augment(webDriver);
        LocalStorage localStorage = webStorage.getLocalStorage();
        localStorage.clear();
    }

}
