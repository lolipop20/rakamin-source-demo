Feature: Login to SauceDemo

  @Regression @Positive
  Scenario: Successful login with valid credentials
    Given User is on the login page
    When User enters username "standard_user" and password "secret_sauce"
    And User clicks the Login button
    Then User is redirected to the products page

  @Regression @Negative
  Scenario: Failed login with invalid credentials
    Given User is on the login page
    When User enters username "invalid_user" and password "invalid_password"
    And User clicks the Login button
    Then Error message is displayed

    @TDD
    Scenario Outline: User Login to Saucedemo
      Given I is on the login page
      When I input <username> as username and <password> as password
      And I clicks the Login button
      Then I is verify <status> login result

      Examples:
        | username     | password           | status     |
        | standard_user | secret_sauce       | success    |
        | invalid_user  | invalid_password   | failed     |
