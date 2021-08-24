### Selenium Framework with Cucumber, TestNg

Features

1. BDD using Cucumber
2. Maven based framework
3. Page Object Model with Page Factory implementation
4. Report Generation (cucumber-reporting)
5. Helper class to handle web component such as (Button,Link etc)
6. Config.properties file for managing properties like browser
7. Log4j enabled for logging
8. Cli supports handling browser and headless features
9. Parallel execution support using Testng

### Understanding is the basic code:
####Add the Feature file
Add the feature file under `test\resources\features\featurefile.feature`

#### Create the Runner

Add the Test runner at `test\java\testRunner\`
```java
@CucumberOptions(features = { "src/test/resources/features" }, 
        glue = {"stepdefinition",},
        plugin = { "pretty", "json:target/SearchFeatureRunner.json" })
public class TestRunner extends AbstractTestNGCucumberTests {
}
``` 
Where: -
features: represent the location of feature files from the compiled build
plugin: html,json report properties
glue: where the hooks and step definition is defined


#### Use the testng.xml file to run the test cases

### Project Execution

#### To clean and compile the build

`mvn clean install -DskipTests`

#### To run the project use this command(default chrome in head mode)

`mvn clean test`

#### Cucumber Report

Pretty Cucumber-Html Report
`C:\Users\S730737\Documents\Sumit\target\cucumber-report\cucumber-html-reports\overview-feature.html`

##### Command to run on chrome in headless mode
`mvn clean test -Dbrowser=chrome -DisHeadless=true`

