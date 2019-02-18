package lesson5.task9;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class SortingCountriesZones {
    public WebDriver driver;
    public WebDriverWait wait;

    public boolean isElementPresent(By locator) {

        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public boolean areElementsPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public boolean isSortedList(List<String> list) {
        String before = list.get(0);
        String current;
        boolean result;
        for (int i = 1; i < list.size(); i++) {
            current = list.get(i);
            if (current.compareTo(before) < 0) {
                result = false;
                break;
            }
            before = current;
        }
        return result = true;
    }

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void adminCheckCountries() throws NoSuchElementException {
        driver.get("http://localhost/litecard/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.findElement(By.xpath("//ul[@class='list-vertical']//*[.='Countries']")).click();

        List<WebElement> countryRows = driver.findElements(By.cssSelector("td#content table.dataTable tbody .row"));

        List<String> countries = new ArrayList<>();
        List<String> zonesNumber = new ArrayList<>();
        List<String> zones = new ArrayList<>();

        for (int i = 0; i < countryRows.size(); i++) {

            WebElement rowCountry = countryRows.get(i);
            String country = rowCountry.findElement(By.xpath("./td[5]")).getText();
            String zone = rowCountry.findElement(By.xpath("./td[6]")).getText();
            countries.add(country);
            zonesNumber.add(zone);
        }

        //**************** check sorting of Countries **********************

        System.out.println("Counties are sorted correctly? " + isSortedList(countries));

        //**************** check "non-zero" Zones ***************************
        int k = 1;
        for (String zonek : zonesNumber) {

            if (!zonek.equals("0")) {
                WebElement table = driver.findElement(By.cssSelector("td#content table.dataTable tbody"));
                System.out.println("Country with non-zero Zones: " + table.findElement(By.xpath("./tr[@class='row'][" + k + "]/td[5]")).getText());
                table.findElement(By.xpath("./tr[@class='row'][" + k + "]/td[5]/a")).click();

                List<WebElement> rowsZones = driver.findElements(By.cssSelector("#table-zones tr:not(.header)"));
                System.out.println("Number of zones: " + (rowsZones.size() - 1));

                for (int j = 0; j < rowsZones.size() - 1; j++) {
                    WebElement rowZone = rowsZones.get(j);
                    String zoneName = rowZone.findElement(By.xpath("./td[3]")).getText();
                    zones.add(zoneName);
                }

                //**************** check sorting of Zones***********************
                System.out.println("Are Zones sorted correctly? " + isSortedList(zones));

                driver.findElement(By.name("cancel")).click();
                driver.findElement(By.cssSelector("td#content table.dataTable tbody"));
            }
            k++;
        }
    }

    @Test
    public void adminCheckGeoZones() throws NoSuchElementException {
        driver.get("http://localhost/litecard/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.findElement(By.xpath("//ul[@class='list-vertical']//*[.='Geo Zones']")).click();

        List<WebElement> countryRows = driver.findElements(By.cssSelector("td#content table.dataTable tbody .row"));

        List<String> geoZones = new ArrayList<>();

        for (int i = 1; i < countryRows.size(); i++) {
            WebElement table = driver.findElement(By.cssSelector("td#content table.dataTable tbody"));
            System.out.println(table.findElement(By.xpath("./tr[@class='row'][" + i + "]/td[3]")).getText());
            table.findElement(By.xpath("./tr[@class='row'][" + i + "]/td[3]/a")).click();

            List<WebElement> zoneRows = driver.findElements(By.cssSelector("#table-zones tbody tr"));
            System.out.println("Number of Geozones: " + (zoneRows.size() - 2));

            for (int j = 1; j < zoneRows.size() - 1; j++) {
                WebElement geoZoneRow = zoneRows.get(j);

                if (isElementPresent(By.xpath(".//td[3]//option[@selected='selected']"))) {
                    String geoZoneName = geoZoneRow.findElement(By.xpath("./td[3]//option[@selected='selected']")).getAttribute("textContent");
                    System.out.println(j + geoZoneName);
                    geoZones.add(geoZoneName);
                }
            }
            System.out.println("Are Geozones sorted correctly? " + isSortedList(geoZones));
            driver.findElement(By.name("cancel")).click();
            driver.findElement(By.cssSelector("td#content table.dataTable tbody"));
        }
    }

    @After
    public void stop() {
        driver.quit();
    }
}