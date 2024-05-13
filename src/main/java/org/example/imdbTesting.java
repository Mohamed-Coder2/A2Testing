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

public class imdbTesting {

    @Test
    public void testSearch() {
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

        // Click on the search button
        WebElement searchButton = webDriver.findElement(By.id("suggestion-search-button"));
        searchButton.click();

        // Check if the first result has the name of the title we searched for
        String actual = webDriver.findElement(By.className("ipc-metadata-list-summary-item__t")).getText();
        String expected = "One Piece";

        // Assert
        Assert.assertEquals(expected,actual);

        // Close the browser
        webDriver.quit();
    }

    @Test
    public void testTop250() {
        // If you want to test it yourself don't forget to change these hardcoded values
        System.setProperty("webdriver.firefox.driver","E:\\Moo\\Chrome Downloads\\geckodriver-v0.34.0-win-aarch64\\geckodriver.exe");

        // Initialize a Web Driver with your favourite browser (Chrome didn't work for me)
        WebDriver webDriver = new FirefoxDriver();

        // Navigate to a IMDB
        webDriver.navigate().to("https://www.imdb.com");

        // open the menu in the nav
        webDriver.findElement(By.id("imdbHeader-navDrawerOpen")).click();

        // click the top 250 movies link
        webDriver.findElement(By.xpath("/html/body/div[2]/nav/div[2]/aside[1]/div/div[2]/div/div[1]/span/div/div/ul/a[2]")).click();

        // get the header in the page that we got redirected to after clicking the previous element
        String actual = webDriver.findElement(By.xpath("/html/body/div[2]/main/div/div[3]/section/div/div[1]/div/div[2]/hgroup/h1")).getText();
        String expected = "IMDb Top 250 Movies";

        // Assert
        Assert.assertEquals(expected,actual);

        webDriver.close();
    }
}