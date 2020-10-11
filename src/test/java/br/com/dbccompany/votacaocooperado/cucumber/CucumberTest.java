package br.com.dbccompany.votacaocooperado.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = "pretty", features = "src/test/features", format = {"json:target/cucumber.json"})
public class CucumberTest {

}