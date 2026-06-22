@authentication
Feature: User Authentication
  As a customer of Sauce Demo
  I want to be able to log in and log out
  So that I can securely access the shopping platform

  Background:
    Given the user is on the Sauce Demo login page

  @smoke @positive
  Scenario: Standard user logs in successfully
    When the user enters username "standard_user" and password "secret_sauce"
    And clicks the login button
    Then the user should be redirected to the inventory page

  @regression @negative
  Scenario Outline: Login fails with invalid credentials
    When the user enters username "<username>" and password "<password>"
    And clicks the login button
    Then an error message containing "<errorFragment>" should be displayed

    Examples:
      | username       | password      | errorFragment                       |
      | standard_user  | wrong_pass    | Username and password do not match  |
      |                | secret_sauce  | Username is required                |
      | standard_user  |               | Password is required                |
      | unknown_user   | secret_sauce  | Username and password do not match  |

  @regression @negative
  Scenario: Locked-out user is blocked from the application
    When the user enters username "locked_out_user" and password "secret_sauce"
    And clicks the login button
    Then an error message containing "Sorry, this user has been locked out" should be displayed
