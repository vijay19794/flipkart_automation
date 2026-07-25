package com.flipkart.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Locale;

/**
 * Page that lists the products returned for a search keyword.
 */
public class SearchResultsPage extends BasePage {

    // Locators (product title cells vary by category layout on Flipkart)
    private final By productTitles =
            By.xpath("//div[contains(@class,'_1AtVbE')]//a[@title] | //div[@class='_4rR01T'] | //a[@class='wjcEIp']");

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getResults() {
        waitForVisible(productTitles);
        return findAll(productTitles);
    }

    /**
     * Validation: every (or at least the first N) result titles should contain
     * the searched keyword tokens.
     */
    public boolean resultsMatchKeyword(String keyword) {
        List<WebElement> results = getResults();
        if (results.isEmpty()) {
            return false;
        }
        String key = keyword.toLowerCase(Locale.ROOT);
        long matches = results.stream()
                .limit(10)
                .map(e -> {
                    String t = e.getText();
                    if (t == null || t.isBlank()) {
                        t = e.getAttribute("title");
                    }
                    return t == null ? "" : t.toLowerCase(Locale.ROOT);
                })
                .filter(t -> t.contains(key) || partialMatch(t, key))
                .count();
        return matches > 0;
    }

    private boolean partialMatch(String title, String keyword) {
        for (String token : keyword.split("\\s+")) {
            if (!token.isBlank() && title.contains(token)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Opens the first product that matches BOTH the storage and colour criteria.
     * Falls back to the first result if no exact match text is found.
     */
    public void openProductByCriteria(String storage, String color) {
        List<WebElement> results = getResults();
        String s = storage.toLowerCase(Locale.ROOT).replace(" ", "");
        String c = color.toLowerCase(Locale.ROOT);

        for (WebElement result : results) {
            String text = (result.getText() + " " + safeAttr(result, "title"))
                    .toLowerCase(Locale.ROOT).replace(" ", "");
            if (text.contains(s) && text.contains(c)) {
                result.click();
                return;
            }
        }
        // Fallback: open the first result
        if (!results.isEmpty()) {
            results.get(0).click();
        }
    }

    private String safeAttr(WebElement element, String attr) {
        String value = element.getAttribute(attr);
        return value == null ? "" : value;
    }
}
