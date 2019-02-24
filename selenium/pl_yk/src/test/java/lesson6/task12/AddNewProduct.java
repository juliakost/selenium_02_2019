package lesson6.task11.task12;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AddNewProduct {
    private WebDriver driver;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @Test
    public void main() throws InterruptedException {
        driver.get("http://localhost/litecard/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        driver.findElement(By.xpath("//ul[@class='list-vertical']//*[.='Catalog']")).click();

        //^^^General^^^
        driver.findElement(By.cssSelector("td#content div a.button:nth-child(2)")).click();

        String relativePath = "./src/test/resources/cat.png";
        Path filePath = Paths.get(relativePath);
        String photoPath = filePath.normalize().toAbsolutePath().toString();

        driver.findElement(By.cssSelector("input[name=status][value='1']")).click();
        driver.findElement(By.name("name[en]")).sendKeys("Big Funny Fluffy Cat");
        driver.findElement(By.name("code")).sendKeys("cat007");
        driver.findElement(By.cssSelector("input[type=checkbox][value='1']")).click();
        driver.findElement(By.cssSelector("input[type=checkbox][value='1-3']")).click();
        driver.findElement(By.name("quantity")).sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
        driver.findElement(By.name("quantity")).sendKeys("14");

        Select selectQuantity = new Select(driver.findElement(By.name("quantity_unit_id")));
        selectQuantity.selectByIndex(1);
        Select selectDeliveryStatus = new Select(driver.findElement(By.name("delivery_status_id")));
        selectDeliveryStatus.selectByIndex(1);
        Select selectSoldOutStatus = new Select(driver.findElement(By.name("sold_out_status_id")));
        selectSoldOutStatus.selectByIndex(2);

        driver.findElement(By.name("new_images[]")).sendKeys(photoPath);

        driver.findElement(By.name("date_valid_from")).sendKeys("02/22/2019");
        driver.findElement(By.name("date_valid_to")).sendKeys("02/22/2020");

        //^^^Information^^^
        driver.findElement(By.cssSelector("ul.index li:nth-child(2)")).click();
        Thread.sleep(1000);

        Select manufact = new Select(driver.findElement(By.name("manufacturer_id")));
        manufact.selectByVisibleText("ACME Corp.");

        driver.findElement(By.name("short_description[en]")).sendKeys("Cat is fluffy and big");
        driver.findElement(By.className("trumbowyg-editor")).sendKeys("The British Shorthair is the pedigreed version of the traditional British domestic cat");

        //^^^Prices^^^
        driver.findElement(By.cssSelector("ul.index li:nth-child(4)")).click();
        Thread.sleep(1000);

        driver.findElement(By.name("purchase_price")).sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
        driver.findElement(By.name("purchase_price")).sendKeys("12");
        Select curr_code = new Select(driver.findElement(By.name("purchase_price_currency_code")));
        curr_code.selectByVisibleText("Euros");
        driver.findElement(By.name("prices[USD]")).sendKeys("15");

        //^^^Save^^^
        driver.findElement(By.cssSelector("button[name=save]")).click();
        Thread.sleep(1000);

        //^^^Check^^^
        String name;

        WebElement root = driver.findElement(By.cssSelector("table.dataTable tbody"));
        List<WebElement> list = root.findElements(By.xpath(".//tr/td[3]/a"));
        for (WebElement we : list) {
            name = we.getText();
            if (name.equals("Big Funny Fluffy Cat")) {
                System.out.println("New item \"Big Funny Fluffy Cat\" is added!");
            }
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}