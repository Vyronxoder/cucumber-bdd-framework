@checkout
Feature: Checkout Process
  As a logged-in customer with items in my cart
  I want to complete the checkout process
  So that I can place my order successfully

  Background:
    Given the user is logged in as "standard_user"
    And the user has "Sauce Labs Backpack" in the cart

  @smoke @e2e
  Scenario: Successful end-to-end purchase flow
    When the user opens the cart
    And proceeds to checkout
    And fills in first name "Gaurav", last name "Chaubey", and postal code "110001"
    And clicks Finish
    Then the order confirmation message "Thank you for your order!" should be displayed

  @regression @negative
  Scenario: Checkout fails when required fields are empty
    When the user opens the cart
    And proceeds to checkout
    And fills in first name "", last name "", and postal code ""
    Then a checkout error message should be displayed
