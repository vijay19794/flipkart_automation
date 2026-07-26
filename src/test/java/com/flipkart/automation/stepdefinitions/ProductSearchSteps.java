package com.flipkart.automation.stepdefinitions;

import com.flipkart.automation.pages.PageObjectManager;
import com.flipkart.automation.utils.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Glue code: implements each Gherkin step from
 * product_search_checkout.feature using the page objects.
 */
public class ProductSearchSteps {

    private final PageObjectManager pages =
            new PageObjectManager(DriverManager.getDriver());

    @Given("the user is on the Flipkart home page")
    public void the_user_is_on_the_flipkart_home_page() {
        pages.getHomePage().open();
        assertTrue(pages.getHomePage().isLoaded(),
                "Home page did not load / search box not visible");
    }

    @When("the user searches for {string}")
    public void the_user_searches_for(String product) {
        pages.getHomePage().searchFor(product);
    }

    @Then("the search results should be related to {string}")
    public void the_search_results_should_be_related_to(String keyword) {
        boolean matched = pages.getSearchResultsPage().resultsMatchKeyword(keyword);
        assertTrue(matched,
                "Search results are not related to the keyword: " + keyword);
    }

    @When("the user selects the product with storage {string} and color {string}")
    public void the_user_selects_the_product_with_storage_and_color(String storage, String color) {
        pages.getSearchResultsPage().openProductByCriteria(storage, color);
        pages.getProductPage().switchToProductTab();
        pages.getProductPage().selectVariant(color);
        pages.getProductPage().selectVariant(storage);
    }

    @When("the user adds the selected product to the cart")
    public void the_user_adds_the_selected_product_to_the_cart() {
        pages.getProductPage().addToCart();
    }

    @When("the user proceeds to the checkout page")
    public void the_user_proceeds_to_the_checkout_page() {
        // On Flipkart, "Add to cart" already lands on the cart/checkout summary.
        // Additional navigation clicks can be added here if required.
    }

    @Then("the checkout page should display the {string} product")
    public void the_checkout_page_should_display_the_product(String product) {
        assertTrue(pages.getCartPage().isProductDisplayed(product),
                "Expected product not found on checkout page: " + product);
    }

    @And("the checkout price should be within the range {int} and {int}")
    public void the_checkout_price_should_be_within_the_range(Integer min, Integer max) {
        assertTrue(pages.getCartPage().isPriceWithinRange(min, max),
                "Checkout price is outside expected range [" + min + " - " + max + "]");
    }
}
