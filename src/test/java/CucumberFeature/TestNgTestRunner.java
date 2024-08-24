package CucumberFeature;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/CucumberFeature",glue = "CucumberStepDefinition",
monochrome = true,tags ="@errorValidation",plugin = {"html:reports/cucumberReport.html"})
public class TestNgTestRunner extends AbstractTestNGCucumberTests{

}
