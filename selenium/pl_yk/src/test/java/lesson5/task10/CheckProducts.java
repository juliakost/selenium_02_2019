package lesson5.task10;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckProducts {
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
    public void checkGoods() {

        driver.get("http://localhost/litecard");
        String[] listMainPage = new String[9];
        String[] listItemPage = new String[9];

        WebElement itemOnMain = driver.findElement(By.xpath(".//div[@id='box-campaigns']//li[1]"));

        listMainPage[0] = itemOnMain.findElement(By.cssSelector("div.name")).getText();
        listMainPage[1] = itemOnMain.findElement(By.cssSelector("s.regular-price")).getText();
        listMainPage[2] = itemOnMain.findElement(By.cssSelector("s.regular-price")).getCssValue("color");
        listMainPage[3] = itemOnMain.findElement(By.cssSelector("s.regular-price")).getCssValue("text-decoration");
        listMainPage[4] = itemOnMain.findElement(By.cssSelector("s.regular-price")).getCssValue("font-size");

        listMainPage[5] = itemOnMain.findElement(By.cssSelector("strong.campaign-price")).getText();
        listMainPage[6] = itemOnMain.findElement(By.cssSelector("strong.campaign-price")).getCssValue("color");
        listMainPage[7] = itemOnMain.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-weight");
        listMainPage[8] = itemOnMain.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-size");

        itemOnMain.click();

        WebElement itemOnDetails = driver.findElement(By.cssSelector("div#box-product div.information"));

        listItemPage[0] = driver.findElement(By.cssSelector("h1.title")).getText();

        listItemPage[1] = itemOnDetails.findElement(By.cssSelector("s.regular-price")).getText();
        listItemPage[2] = itemOnDetails.findElement(By.cssSelector("s.regular-price")).getCssValue("color");
        listItemPage[3] = itemOnDetails.findElement(By.cssSelector("s.regular-price")).getCssValue("text-decoration");
        listItemPage[4] = itemOnDetails.findElement(By.cssSelector("s.regular-price")).getCssValue("font-size");

        listItemPage[5] = itemOnDetails.findElement(By.cssSelector("strong.campaign-price")).getText();
        listItemPage[6] = itemOnDetails.findElement(By.cssSelector("strong.campaign-price")).getCssValue("color");
        listItemPage[7] = itemOnDetails.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-weight");
        listItemPage[8] = itemOnDetails.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-size");

        System.out.println("^^^Compare names^^^");
        System.out.println("The item on Main Page: " + listMainPage[0]);
        System.out.println("The item on Item Page: " + listItemPage[0]);
        System.out.println("They are " + compareText(listMainPage[0], listItemPage[0]));

        System.out.println("^^^Compare regular price values^^^");
        System.out.println("The item on Main Page: " + listMainPage[1]);
        System.out.println("The item on Item Page: " + listItemPage[1]);
        System.out.println("They are " + compareText(listMainPage[1], listItemPage[1]));

        System.out.println("^^^Compare sale price values^^^");
        System.out.println("The item on Main Page: " + listMainPage[5]);
        System.out.println("The item on Item Page: " + listItemPage[5]);
        System.out.println("They are " + compareText(listMainPage[5], listItemPage[5]));

        System.out.println("^^^Regular price properties on Main page^^^");
        System.out.println("Color: " + listMainPage[2]);
        System.out.println("Text decor: " + listMainPage[3]);

        System.out.println("^^^Sale price properties on Main page^^^");
        System.out.println("Color: " + listMainPage[6]);
        System.out.println("Text decor: " + listMainPage[7]);

        System.out.println("^^^Regular price properties on Item page^^^");
        System.out.println("Color: " + listItemPage[2]);
        System.out.println("Text decor: " + listItemPage[3]);

        System.out.println("^^^Sale price properties on Item page^^^");
        System.out.println("Color: " + listMainPage[6]);
        System.out.println("Text decor: " + listItemPage[7]);

        System.out.println("^^^Main Page^^^");
        System.out.println("Regular price font size:" + listMainPage[4]);
        System.out.println("Campaign price font size: " + listMainPage[8]);
        System.out.println("Regular price Font" + compareFont(listMainPage[4], listMainPage[8]));

        System.out.println("^^^Item Page^^^");
        System.out.println("Regular price font size:" + listItemPage[4]);
        System.out.println("Campaign price font size: " + listItemPage[8]);
        System.out.println("Regular price Font " + compareFont(listItemPage[4], listItemPage[8]));
    }

    private String compareText(String t1, String t2) {
        if (t1.equals(t2))
            return "the same!";
        else
            return "different.";
    }

    private String compareFont(String f1, String f2) {
        if (f1.compareTo(f2) > 0)
            return " is higher";
        else
            return " is smaller";
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}