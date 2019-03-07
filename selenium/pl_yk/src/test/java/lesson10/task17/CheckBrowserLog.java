package lesson10.task17;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;

public class CheckBrowserLog {
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        //driver = new InternetExplorerDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test

    public void getBrowserLogs() throws NoSuchElementException {
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        driver.findElement(By.xpath("//ul[@class='list-vertical']//*[.='Catalog']")).click();

        WebElement table = driver.findElement(By.cssSelector("td#content table.dataTable tbody"));
        table.findElement(By.xpath("./tr[@class='row'][2]/td[3]/a")).click();
        WebElement table1 = driver.findElement(By.cssSelector("td#content table.dataTable tbody"));
        table1.findElement(By.xpath("./tr[@class='row'][3]/td[3]/a")).click();

        WebElement table2 = driver.findElement(By.cssSelector("td#content table.dataTable tbody"));
        List<WebElement> links = table2.findElements(By.tagName("img"));

        for (int i = 0; i < links.size(); i++) {

            WebElement table3 = driver.findElement(By.cssSelector("td#content table.dataTable tbody"));
            List<WebElement> links2 = table3.findElements(By.tagName("img"));

            WebElement productImg = links2.get(i);

            String productItem = productImg.findElement(By.xpath("../..")).getAttribute("rowIndex");
            int item = Integer.parseInt(productItem);

            table3.findElement(By.xpath("./tr[@class='row'][" + item + "]/td[3]/a")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
            driver.findElement(By.name("cancel")).click();

            List<LogEntry> logList = driver.manage().logs().get("browser").getAll();
            if (logList.size() != 0) {
                for (LogEntry l : logList)
                    System.out.println(l);
            } else
                System.out.println("The Log has not messeges!");
            System.out.println();
        }

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
