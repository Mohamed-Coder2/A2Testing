package org.example;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchTest {
    @Test
    public void testValidSearch() throws InterruptedException {
        // Set the path to GeckoDriver
        System.setProperty("webdriver.firefox.driver", "E:\\Moo\\Chrome Downloads\\geckodriver-v0.34.0-win-aarch64\\geckodriver.exe");

        // Initialize Firefox driver
        WebDriver webDriver = new FirefoxDriver();

        // Navigate to IMDb
        webDriver.navigate().to("https://www.imdb.com");

        // Wait for the search bar to be visible
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("suggestion-search")));

        // Enter text into the search bar using JavaScript
        // I'm using this in particular because accessing by class name normally
        // doesn't work (selenium can't find the bar using keyboard) ?
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].value='One Piece';", searchBar);
        Thread.sleep(2000);

        // Click on the search button
        WebElement searchButton = webDriver.findElement(By.id("suggestion-search-button"));
        searchButton.click();
        Thread.sleep(2000);

        // Check if the first result has the name of the title we searched for
        String actual = webDriver.findElement(By.className("ipc-metadata-list-summary-item__t")).getText();
        String expected = "One Piece";

        // Assert
        Assert.assertEquals(expected,actual);
        Thread.sleep(2000);

        // Close the browser
        webDriver.quit();
    }
    @Test
    public void testInvalidSearch() throws InterruptedException{
        // Set the path to GeckoDriver
        System.setProperty("webdriver.firefox.driver", "E:\\Moo\\Chrome Downloads\\geckodriver-v0.34.0-win-aarch64\\geckodriver.exe");

        // Initialize Firefox driver
        WebDriver webDriver = new FirefoxDriver();

        // Navigate to IMDb
        webDriver.navigate().to("https://www.imdb.com");

        // Wait for the search bar to be visible
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("suggestion-search")));

        // Enter text into the search bar using JavaScript
        // I'm using this in particular because accessing by class name normally
        // doesn't work (selenium can't find the bar using keyboard) ?
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].value='938iwuhrfcn ';", searchBar);
        Thread.sleep(2000);

        // Click on the search button
        WebElement searchButton = webDriver.findElement(By.id("suggestion-search-button"));
        searchButton.click();
        Thread.sleep(2000);

        // Check if the first result has the name of the title we searched for
        String actual = webDriver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div[3]/section/div/div[1]/section[2]/div[2]/div")).getText();
        String expected = "No results found for \"938iwuhrfcn \"";

        // Assert
        Assert.assertEquals(expected,actual);
        Thread.sleep(2000);

        // Close the browser
        webDriver.quit();
    }
}
