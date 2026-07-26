package com.flipkart.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Set;

/**
 * Product Details page: select variant (storage / colour) and add to cart.
 * Flipkart usually opens the product in a NEW browser tab from search results.
 */
public class ProductPage extends BasePage {

    private final By addToCartBtn =
            By.xpath("//button[normalize-space()='Add to cart' or contains(.,'ADD TO CART')]");
    private final By productTitle = By.xpath("//span[@class='VU-ZEz'] | //h1//span");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    /** Switch WebDriver focus to the newly opened product tab, if any. */
    public void switchToProductTab() {
        String current = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(current)) {
                driver.switchTo().window(handle);
                return;
            }
        }
    }

    /** Selects a variant swatch (storage or colour) by its visible text. */
    public void selectVariant(String variantText) {
        By variant = By.xpath("//li[contains(@class,'aksm')][contains(.,'" + variantText + "')]"
                + " | //div[@class='V3Zflw'][contains(.,'" + variantText + "')]");
        if (isDisplayed(variant)) {
            click(variant);
        }
    }

    public void addToCart() {
        click(addToCartBtn);
    }

    public String getProductTitle() {
        return getText(productTitle);
    }
}
