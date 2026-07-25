package com.flipkart.automation.runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

/**
 * JUnit 5 + Cucumber test runner.
 * Discovers the .feature files under src/test/resources/features and binds
 * them to the step-definition glue in the stepdefinitions package.
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(
        key = GLUE_PROPERTY_NAME,
        value = "com.flipkart.automation.stepdefinitions")
@ConfigurationParameter(
        key = PLUGIN_PROPERTY_NAME,
        value = "pretty, html:target/cucumber-reports/report.html, "
                + "json:target/cucumber-reports/report.json")
public class TestRunner {
    // No body needed — annotations drive the execution.
}
