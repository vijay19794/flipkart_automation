package com.flipkart.automation.pages;

import org.openqa.selenium.WebDriver;

/**
 * Central factory that creates page objects lazily and reuses them.
 * Step definitions ask the manager for a page instead of "new"-ing pages
 * themselves. This keeps locators in one place and avoids duplicate objects.
 */
public class PageObjectManager {

    private final WebDriver driver;

    private HomePage homePage;
    private SearchResultsPage searchResultsPage;
    private ProductPage productPage;
    private CartPage cartPage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage(driver);
        }
        return homePage;
    }

    public SearchResultsPage getSearchResultsPage() {
        if (searchResultsPage == null) {
            searchResultsPage = new SearchResultsPage(driver);
        }
        return searchResultsPage;
    }

    public ProductPage getProductPage() {
        if (productPage == null) {
            productPage = new ProductPage(driver);
        }
        return productPage;
    }

    public CartPage getCartPage() {
        if (cartPage == null) {
            cartPage = new CartPage(driver);
        }
        return cartPage;
    }
}
