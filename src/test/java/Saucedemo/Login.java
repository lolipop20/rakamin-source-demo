package Saucedemo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Login {
    @Test
    public void success_login_case(){
        WebDriver driver;
        String baseurl ="https://www.saucedemo.com/";

        WebDriverManager.chromedriver().setup();


//        apply chrome driver setup
//        membuka halaman Login
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseurl);
        String loginpageAssert = driver.findElement(By.xpath("//div[contains(text(),'Swag Labs')]")).getText();
        Assert.assertEquals(loginpageAssert,"Swag Labs");
//        input email
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
//        input password benar
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
//        input login
        driver.findElement(By.xpath("//input[@type='submit']")).click();

//        assert product
        String product = driver.findElement(By.xpath("//span[contains(text(),'Products')]")).getText();
        Assert.assertEquals(product,"Products");

//        quit
        driver.close();


    }

    @Test
    public void failed_login_case(){
        WebDriver driver;
        String baseurl ="https://www.saucedemo.com/";

        WebDriverManager.chromedriver().setup();


//        apply chrome driver setup
//        membuka halaman Login
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseurl);
        String loginPageAssert = driver.findElement(By.xpath("//div[contains(text(),'Swag Labs')]")).getText();
        Assert.assertEquals(loginPageAssert,"Swag Labs");
//        input email
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
//        input password salah
        driver.findElement(By.id("password")).sendKeys("secret_sauces");
//        input login
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        String errorLogin = driver.findElement(By.xpath("//h3[contains(text(),'')]")).getText();
        Assert.assertEquals(errorLogin,"Epic sadface: Username and password do not match any user in this service");

//        quit
//        driver.close();
    }

}
