package lesson6.task11;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddNewAccount {

    private WebDriver driver;
    public WebDriverWait wait;


    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void main() throws InterruptedException {
        driver.get("http://localhost/litecard");

        String email = "juliakost@uapl.com";
        String password = "777888";

        newAccountData(email, password);
        Thread.sleep(900);

        logout();
        Thread.sleep(900);

        login(email, password);
        Thread.sleep(900);

        logout();
        Thread.sleep(900);
    }

    private void newAccountData(String email, String password) {
        driver.findElement(By.cssSelector("form[name='login_form'] table tr:last-child")).click();
        driver.findElement(By.name("firstname")).sendKeys("Yuliia2");
        driver.findElement(By.name("lastname")).sendKeys("Kostitsyna");
        driver.findElement(By.name("address1")).sendKeys("Walonska");
        driver.findElement(By.name("postcode")).sendKeys("78780");
        driver.findElement(By.name("city")).sendKeys("New York");

        Select country = new Select(driver.findElement(By.name("country_code")));
        country.selectByVisibleText("United States");

        Select zone = new Select(driver.findElement(By.cssSelector("select[name='zone_code']")));
        zone.selectByVisibleText("Alabama");

        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("phone")).sendKeys("+15555566666");
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("confirmed_password")).sendKeys(password);
        driver.findElement(By.name("create_account")).click();
    }

    private void login(String email, String password) {
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
    }

    private void logout() {
        driver.findElement(By.cssSelector("div#box-account div.content li:last-child a")).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}