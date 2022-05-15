Feature: QuizTest LeapXpert QA

  Scenario Outline: QuizTest LeapXpert test
    Given I open LeapXpert website
    When I bypass Company with "<company>"
    And I login LeapXpert with username as "<username>" and password as "<password>"
    And I input OTP as "<otp>"
    And I go to Profile page
    And I go to Device page
    And I link device
    And I get QRCode
    Then I verify QrCode exist
    And I skip welcome page
    And I input activation code
    And I Login App as "<username>" and "<password>"
    And I input Otp app as "<otp>"
    And I go to Contact page
    And I search Contact by Team as "<team>"
    And I select contact for chatting
    And I send a message as "<sms>"
    And I reply message
    And I quit browser
    And I open LeapXpert website
    And I bypass Company with "<company>"
    And I login LeapXpert with username as "<user2>" and password as "<password>"
    And I input OTP as "<otp>"
    And I select channel chatting by "<username>"
    Then I verify last content sms as "<sms>"
    Examples:
      | company | username                  | password  | otp | team | sms | user2 |
      | auto_testing | automation_auto_2021   | Testing@123 | 111111 | automation_auto_2022 | cuong_ne | automation_auto_2022 |
