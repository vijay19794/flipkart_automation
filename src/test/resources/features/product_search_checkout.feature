@ecommerce
Feature: Flipkart product search and checkout validation
  As a shopper
  I want to search for a product, pick the right variant and go to checkout
  So that I can confirm the correct product and price are carried forward

  Background:
    Given the user is on the Flipkart home page
    

  @search
  Scenario: Validate that search results match the searched keyword
    When the user searches for "iphone 16"
    Then the search results should be related to "iphone 16"

  @checkout
  Scenario Outline: Add a product by criteria and validate it on the checkout page
    When the user searches for "<product>"
    Then the search results should be related to "<product>"
    When the user selects the product with storage "<storage>" and color "<color>"
    And the user adds the selected product to the cart
    And the user proceeds to the checkout page
    Then the checkout page should display the "<product>" product
    And the checkout price should be within the range <minPrice> and <maxPrice>

    Examples:
      | product   | storage | color | minPrice | maxPrice |
      | iphone 16 | 256 GB  | Black | 70000    | 95000    |
