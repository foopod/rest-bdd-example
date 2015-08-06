package com.foopod.rest.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = { "pretty", "html:target/cucumber" },
        glue = "com.foopod.rest.steps",
        features = "classpath:"
)
public class RunRestTest {
}
