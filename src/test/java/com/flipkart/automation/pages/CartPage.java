package com.flipkart.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Cart / Checkout page: validate that the chosen product and its price
 * are carried over correctly.
 */
public class CartPage extends BasePage {

    private final By checkoutProductName =
            By.xpath("//div[contains(@class,'D0Yg3W')] | //a[contains(@class,'T2CNXf')]");
    private final By checkoutPrice =
            By.xpath("//span[contains(@class,'Nx9bqj')] | //div[contains(@class,'yRaY8j')]");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getCheckoutProductName() {
        return getText(checkoutProductName);
    }

    /** Extracts the numeric price value, stripping ₹, commas and spaces. */
    public double getCheckoutPrice() {
        String raw = getText(checkoutPrice);
        String digits = raw.replaceAll("[^0-9]", "");
        return digits.isBlank() ? -1 : Double.parseDouble(digits);
    }

    public boolean isProductDisplayed(String expectedProduct) {
        String actual = getCheckoutProductName().toLowerCase();
        for (String token : expectedProduct.toLowerCase().split("\\s+")) {
            if (actual.contains(token)) {
                return true;
            }
        }
        return false;
    }

    public boolean isPriceWithinRange(double min, double max) {
        double price = getCheckoutPrice();
        return price >= min && price <= max;
    }
}
