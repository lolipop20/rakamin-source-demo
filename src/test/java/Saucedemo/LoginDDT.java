package Saucedemo;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LoginDDT {
    @Test
    public void login_ddt() {
        WebDriver driver;

        String baseUrl = "https://www.saucedemo.com/";

        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();
        opt.setHeadless(false);

        String csvDir = System.getProperty("user.dir") + "/src/test/data/test-data.csv";

        try (CSVReader reader = new CSVReader(new FileReader(csvDir))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                String username = nextLine[0];
                String password = nextLine[1];
                String status = nextLine[2];

                driver = new ChromeDriver(opt);
                driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                driver.manage().window().maximize();

                // Navigasi ke halaman login
                driver.get(baseUrl);

                // Isi form dengan data dari CSV
                driver.findElement(By.id("user-name")).sendKeys(username);
                driver.findElement(By.id("password")).sendKeys(password);
                driver.findElement(By.id("login-button")).click();

                if (status.equals("success")) {
                    // Assertion jika login sukses
                    String product = driver.findElement(By.xpath("//div[text()='Products']")).getText();
                    Assert.assertEquals(product, "Products");
                } else {
                    // Assertion jika login gagal
                    String errorLogin = driver.findElement(By.xpath("//h3[text()='Epic sadface: Username and password do not match any user in this service']")).getText();
                    Assert.assertEquals(errorLogin, "Epic sadface: Username and password do not match any user in this service");
                }

                driver.quit(); // Tutup browser setelah pengujian satu data selesai
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
    }
}
