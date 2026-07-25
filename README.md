# Flipkart E-Commerce Automation Framework

A **Selenium + Cucumber (BDD)** UI automation framework built with **Java + Maven** that
automates and validates an end-to-end e-commerce shopping flow on Flipkart (or any
similar site).

---

## 🎯 Scenario Under Test

| Step | Action | Validation |
|------|--------|------------|
| 1 | Launch the site and log in (optional) | Home page / login is displayed |
| 2 | Search for a product | Search box accepts the term |
| 3 | Validate search results | Results match the searched keyword |
| 4 | Select a product by criteria | e.g. *iPhone 16, 256 GB, Black* |
| 5 | Add to cart & go to checkout | Product & price appear (within expected range) |

---

## 🧱 Framework Structure

```
flipkart-automation/
├── pom.xml                         # Maven dependencies & build config
├── README.md
├── .gitignore
└── src
    └── test
        ├── java/com/flipkart/automation
        │   ├── pages/              # Page Objects (locators + actions)
        │   │   ├── BasePage.java
        │   │   ├── HomePage.java
        │   │   ├── SearchResultsPage.java
        │   │   ├── ProductPage.java
        │   │   ├── CartPage.java
        │   │   └── PageObjectManager.java   # Central page factory / manager
        │   ├── stepdefinitions/    # Glue code (Given/When/Then implementation)
        │   │   ├── ProductSearchSteps.java
        │   │   └── Hooks.java
        │   ├── runners/
        │   │   └── TestRunner.java  # Cucumber JUnit runner
        │   └── utils/
        │       └── DriverManager.java  # WebDriver lifecycle
        └── resources/features
            └── product_search_checkout.feature   # BDD feature file
```

### Layer responsibilities
- **Feature file** – Business-readable scenario written in Gherkin.
- **Step definitions** – Translate each Gherkin step into Selenium actions.
- **Page Objects** – Encapsulate locators and page-level actions.
- **Page Object Manager** – Single place to create/reuse page objects.
- **Driver Manager** – Creates, configures and quits the WebDriver.

---

## ⚙️ Prerequisites
- Java 17+
- Maven 3.8+
- Chrome browser (Selenium Manager auto-resolves the driver)

## ▶️ How to Run
```bash
# Run all scenarios
mvn clean test

# Run by tag
mvn clean test -Dcucumber.filter.tags="@checkout"
```

Reports are generated under `target/cucumber-reports/`.

---

## 🌿 Git Workflow Used in This Repo
1. `main` – stable branch (this README committed here first).
2. `feature/flipkart-search-checkout` – feature branch created from `main`.
3. Framework code committed to the feature branch.
4. Pull Request raised from the feature branch → `main`.
5. PR reviewed and merged into `main`.

---

## 👤 Author
Vijay Singh Rathor — Software Engineering / Test Automation
