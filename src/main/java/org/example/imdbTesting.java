package org.example;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class IMDbTesting {
    @Test
    public void testTop250() throws InterruptedException {
        // If you want to test it yourself don't forget to change these hardcoded values
        System.setProperty("webdriver.firefox.driver","E:\\Moo\\Chrome Downloads\\geckodriver-v0.34.0-win-aarch64\\geckodriver.exe");

        // Initialize a Web Driver with your favourite browser (Chrome didn't work for me)
        WebDriver webDriver = new FirefoxDriver();

        // Navigate to a IMDB
        webDriver.navigate().to("https://www.imdb.com");

        // open the menu in the nav
        webDriver.findElement(By.id("imdbHeader-navDrawerOpen")).click();
        Thread.sleep(2000);

        // click the top 250 movies link
        webDriver.findElement(By.xpath("/html/body/div[2]/nav/div[2]/aside[1]/div/div[2]/div/div[1]/span/div/div/ul/a[2]")).click();
        Thread.sleep(2000);

        // get the header in the page that we got redirected to after clicking the previous element
        String actual = webDriver.findElement(By.xpath("/html/body/div[2]/main/div/div[3]/section/div/div[1]/div/div[2]/hgroup/h1")).getText();
        String expected = "IMDb Top 250 Movies";

        // Assert
        Assert.assertEquals(expected,actual);

        // Another check on if the first movie is Shawshank redemption
        String actual1 = webDriver.findElement(By.xpath("/html/body/div[2]/main/div/div[3]/section/div/div[2]/div/ul/li[1]/div[2]/div/div/div[1]/a/h3")).getText();
        String expected1 = "1. The Shawshank Redemption";

        // Assert
        Assert.assertEquals(expected1,actual1);
        Thread.sleep(2000);

        webDriver.close();
    }
    @Test
    public void testAdvancedSearch() throws InterruptedException {
        // If you want to test it yourself don't forget to change these hardcoded values
        System.setProperty("webdriver.firefox.driver","E:\\Moo\\Chrome Downloads\\geckodriver-v0.34.0-win-aarch64\\geckodriver.exe");

        // Initialize a Web Driver with your favourite browser (Chrome didn't work for me)
        WebDriver webDriver = new FirefoxDriver();

        // Navigate to a IMDB
        webDriver.navigate().to("https://www.imdb.com");
        Thread.sleep(1000);

        // Click All
        webDriver.findElement(By.xpath("/html/body/div[2]/nav/div[2]/div[1]/form/div[1]")).click();
        Thread.sleep(1000);

        // Click Advanced Search
        webDriver.findElement(By.xpath("/html/body/div[2]/nav/div[2]/div[1]/form/div[1]/div/div/div/div/span/ul/a/span[1]")).click();
        Thread.sleep(1000);

        // Expand Title Type
        webDriver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div[3]/section/section/div/section/section/div[2]/div/section/div[2]/div[1]/section/div/div[2]/div[1]/label/span[1]/div")).click();

        // Click on Movie in Title Type
        webDriver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div[3]/section/section/div/section/section/div[2]/div/section/div[2]/div[1]/section/div/div[2]/div[2]/div/section/button[1]")).click();
        Thread.sleep(1000);

        // Minimize Title Type
        webDriver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div[3]/section/section/div/section/section/div[2]/div/section/div[2]/div[1]/section/div/div[2]/div[1]/label/span[1]/div")).click();

        // Expand Release Date
        webDriver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div[3]/section/section/div/section/section/div[2]/div/section/div[2]/div[1]/section/div/div[3]/div[1]/label/span[1]/div")).click();

        // enter date from 2010 to 2020
        WebElement fromYear = webDriver.findElement(By.cssSelector(".sc-b63d290d-2 > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > input:nth-child(1)"));
        fromYear.sendKeys("2010");
        Thread.sleep(1000);

        WebElement toYear = webDriver.findElement(By.cssSelector(".sc-b63d290d-2 > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > input:nth-child(1)"));
        toYear.sendKeys("2020");
        Thread.sleep(1000);

        // Minimize Release Date
        webDriver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div[3]/section/section/div/section/section/div[2]/div/section/div[2]/div[1]/section/div/div[3]/div[1]/label/span[1]/div")).click();

        // Expand Genre
        webDriver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div[3]/section/section/div/section/section/div[2]/div/section/div[2]/div[1]/section/div/div[6]/div[1]/label/span[1]/div")).click();

        // Choose Action
        webDriver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div[3]/section/section/div/section/section/div[2]/div/section/div[2]/div[1]/section/div/div[6]/div[2]/div/section/button[1]")).click();
        Thread.sleep(1000);

        // Minimize Genre
        webDriver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div[3]/section/section/div/section/section/div[2]/div/section/div[2]/div[1]/section/div/div[6]/div[1]/label/span[1]/div")).click();

        // click search
        webDriver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div[3]/section/section/div/section/section/div[2]/div/section/div[1]/button")).click();
        Thread.sleep(1000);

        // first movie is War for the planet of the Apes
        String actual = webDriver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div[3]/section/section/div/section/section/div[2]/div/section/div[2]/div[2]/ul/li[1]/div/div/div/div[1]/div[2]/div[1]/a/h3")).getText();
        String expected = "1. War for the Planet of the Apes";

        Assert.assertEquals(actual,expected);
        Thread.sleep(1000);

        webDriver.close();
    }
}