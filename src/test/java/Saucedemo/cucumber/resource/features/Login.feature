Feature: Login to SauceDemo

  Scenario: Successful login with valid credentials
    Given User is on the login page
    When User enters username "standard_user" and password "secret_sauce"
    And User clicks the Login button
    Then User is redirected to the products page

  Scenario: Failed login with invalid credentials
    Given User is on the login page
    When User enters username "invalid_user" and password "invalid_password"
    And User clicks the Login button
    Then Error message is displayed