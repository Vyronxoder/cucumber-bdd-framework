@cart
Feature: Shopping Cart Management
  As a logged-in customer
  I want to add and remove products from my cart
  So that I can manage my intended purchases before checkout

  Background:
    Given the user is logged in as "standard_user"

  @smoke @positive
  Scenario: Adding a product updates the cart badge
    When the user adds "Sauce Labs Backpack" to the cart
    Then the cart badge should display "1"

  @regression @positive
  Scenario: Adding multiple products reflects correct count
    When the user adds "Sauce Labs Backpack" to the cart
    And the user adds "Sauce Labs Bike Light" to the cart
    Then the cart badge should display "2"

  @regression @positive
  Scenario: Removing a product decreases the cart count
    Given the user has "Sauce Labs Backpack" in the cart
    When the user removes "Sauce Labs Backpack" from the cart
    Then the cart badge should not be visible

  @regression @positive
  Scenario: Cart page displays all added products
    When the user adds "Sauce Labs Backpack" to the cart
    And the user adds "Sauce Labs Fleece Jacket" to the cart
    And the user opens the cart
    Then the cart should contain "Sauce Labs Backpack"
    And the cart should contain "Sauce Labs Fleece Jacket"
