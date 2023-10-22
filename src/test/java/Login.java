import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Login {
    @Test //test untuk script
    public void open_browser(){
        WebDriver driver;
        String baseurl ="https://kasirdemo.belajarqa.com";

        WebDriverManager.chromedriver().setup();


//        apply chrome driver setup
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseurl);
        String title = driver.getTitle();
        System.out.println(title);

        String username_button = "username";

//        get form
        WebElement ele1 = driver.findElement(By.id(username_button));
        WebElement ele2 = driver.findElement(By.className("button"));

        ele1.click();
        ele1.sendKeys("email.com");
        ele1.getText();

        ele2.isDisplayed();
        ele2.click();

        driver.findElement(By.xpath("/*contains")).isDisplayed();
        driver.findElement(By.cssSelector("#button")).isDisplayed();


        driver.close();
    }
}
