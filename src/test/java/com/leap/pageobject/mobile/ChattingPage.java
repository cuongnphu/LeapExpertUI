package com.leap.pageobject.mobile;

import com.leap.pageobject.AppBasePage;
import com.leap.pageobject.utils.GenericUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.cucumber.java.en_scouse.An;
import org.openqa.selenium.support.PageFactory;

public class ChattingPage extends AppBasePage {

    @AndroidFindBy(accessibility = "send_to_room")
    private MobileElement txtMessage;

    @AndroidFindBy(accessibility = "chatDetail_sendMessage")
    private MobileElement btnSend;

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='cuong ne']")
    private MobileElement fldMessage;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='reply']")
    private MobileElement btnReply;

    public ChattingPage(AppiumDriver<MobileElement> appDriver) {
        super(appDriver);
        PageFactory.initElements(new AppiumFieldDecorator(appDriver), this);
    }

    public void sendMessage(String sms){
        buttonClick(txtMessage);
        GenericUtils.wait(2000);
        keyBoardText(txtMessage, sms);
        GenericUtils.wait(2000);
        buttonClick(btnSend);
    }

    public void replyCurrentMessage(){
        longTouch(fldMessage);
        GenericUtils.wait(1000);
        buttonClick(btnReply);
    }
}
