# Cucumber BDD Test Automation Framework

![Java](https://img.shields.io/badge/Java-17-orange)
![Cucumber](https://img.shields.io/badge/Cucumber-7.18-brightgreen)
![Selenium](https://img.shields.io/badge/Selenium-4.21-green)
![Allure](https://img.shields.io/badge/Reports-Allure-orange)
![CI](https://img.shields.io/badge/CI-GitHub_Actions-blue)

A **Behavior Driven Development (BDD)** test framework using **Cucumber + Selenium WebDriver + TestNG**, targeting [saucedemo.com](https://www.saucedemo.com/). Feature files are written in **Gherkin** — plain English readable by non-technical stakeholders — and linked to executable step definitions. Reports generated with **Allure**.

## Why BDD?

In Agile teams, test scenarios double as living documentation. A product manager can read `login.feature` and understand exactly what the system is expected to do — without reading Java code.

## Architecture

```
src/
├── main/java/
│   ├── pages/       → POM classes (BasePage, LoginPage, InventoryPage, CartPage, CheckoutPage)
│   └── utils/       → ConfigReader, DriverManager (thread-local driver)
└── test/
    ├── java/
    │   ├── hooks/          → Hooks.java: @Before driver setup, @AfterStep screenshot on fail
    │   ├── runners/        → TestRunner.java: CucumberOptions, parallel DataProvider
    │   └── stepdefinitions/ → LoginSteps, CartSteps, CheckoutSteps
    └── resources/features/
        ├── login.feature    → @smoke @positive @negative @regression scenarios
        ├── cart.feature     → add/remove product scenarios
        └── checkout.feature → end-to-end purchase + validation error scenarios
```

## Test Tags

| Tag | Purpose |
|---|---|
| `@smoke` | Core happy-path — run before every deployment |
| `@regression` | Full suite — run before releases |
| `@positive` | Valid-input scenarios |
| `@negative` | Invalid-input and error handling |
| `@e2e` | Full end-to-end flows |
| `@wip` | Work in progress — excluded from CI |

## Running Tests

```bash
# Full suite
mvn clean test

# Smoke tests only
mvn clean test -Dcucumber.filter.tags="@smoke"

# Regression suite
mvn clean test -Dcucumber.filter.tags="@regression"

# Specific feature tag
mvn clean test -Dcucumber.filter.tags="@cart"

# Run headless off (local debugging)
mvn clean test -Dheadless=false
```

## Generating Allure Report

```bash
mvn allure:serve    # generates + opens in browser
mvn allure:report   # generates to target/site/allure-maven-plugin/
```

## Tech Stack

Java 17 · Cucumber 7 · Selenium WebDriver 4 · TestNG · WebDriverManager · Allure Reports · GitHub Actions
