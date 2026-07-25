package com.flipkart.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

/**
 * Flipkart home page: dismiss login popup and perform a product search.
 * NOTE: Locators are indicative and may need updating if the site changes.
 */
public class HomePage extends BasePage {

    private static final String URL = "https://www.flipkart.com/";

    // Locators
    private final By loginPopupClose = By.xpath("//button[contains(text(),'✕')]");
    private final By searchBox       = By.name("q");
    private final By searchButton    = By.xpath("//button[@type='submit']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(URL);
        dismissLoginPopupIfPresent();
    }

    public void dismissLoginPopupIfPresent() {
        if (isDisplayed(loginPopupClose)) {
            click(loginPopupClose);
        }
    }

    public void searchFor(String product) {
        type(searchBox, product);
        // Either click search or press ENTER
        waitForVisible(searchBox).sendKeys(Keys.ENTER);
    }

    public boolean isLoaded() {
        return isDisplayed(searchBox);
    }
}
