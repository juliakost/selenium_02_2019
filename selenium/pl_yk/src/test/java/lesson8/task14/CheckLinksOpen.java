package lesson8.task14;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class CheckLinksOpen {

    private WebDriver driver;
    public WebDriverWait wait;


    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void newCountryLinks() {
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.findElement(By.xpath("//ul[@class='list-vertical']//*[.='Countries']")).click();

        driver.findElement(By.cssSelector("td#content a.button")).click();

        List<WebElement> list = driver.findElements(By.cssSelector("i.fa-external-link"));

        for (WebElement link : list) {
            String currentWindow = driver.getWindowHandle();
            Set<String> oldWindows = driver.getWindowHandles();
            link.click();
            String newWindow = wait.until(anyWindowOtherThan(oldWindows));
            driver.switchTo().window(newWindow);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
            driver.close();
            driver.switchTo().window(currentWindow);
        }
    }

    public ExpectedCondition<String> anyWindowOtherThan(Set<String> oldWindows) {
        return new ExpectedCondition<String>() {
            @Override
            public String apply(WebDriver d) {
                Set<String> handles = d.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}