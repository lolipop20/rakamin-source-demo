package Saucedemo.cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class loginTDD {
    WebDriver driver;

    @When("User input (.*) as username and (.*) as password$")
    public void userInputUsernameAndPassword(String username, String password) {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("User Click (.*)the login button$")
    public void userClicksTheLoginButton() {
        driver.findElement(By.xpath("//input[@type='submit']")).click();

    }

    @Then("User is verify (.*) login result$")
    public void userIsVerifyLoginResult(String status){
        if (status.equals("PASS")) { // Ubah "success" menjadi "PASS"
            // Assertion jika login sukses
            String product = driver.findElement(By.xpath("//div[text()='Products']")).getText();
            Assert.assertEquals(product, "Products");
        } else {
            // Assertion jika login gagal
            String errorLogin = driver.findElement(By.xpath("//*[contains(text(), 'Epic sadface: Username and password do not match any user in this service')]")).getText();
            Assert.assertEquals(errorLogin, "Epic sadface: Username and password do not match any user in this service");
        }
        driver.close();
    }

    @Given("I is on the login page")
    public void iIsOnTheLoginPage() {
    }

    @When("I input <username> as username and <password> as password")
    public void iInputUsernameAsUsernameAndPasswordAsPassword() {
    }

    @And("I clicks the Login button")
    public void iClicksTheLoginButton() {
    }

    @Then("I is verify <status> login result")
    public void iIsVerifyStatusLoginResult() {
    }
}