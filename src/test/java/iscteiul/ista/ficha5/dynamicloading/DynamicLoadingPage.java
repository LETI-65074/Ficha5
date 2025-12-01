package iscteiul.ista.ficha5.dynamicloading;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DynamicLoadingPage {

    private final WebDriver driver;

    private static final String PAGE_URL =
            "https://the-internet.herokuapp.com/dynamic_loading/1";

    private final By startButton = By.cssSelector("#start button");
    private final By finishText  = By.id("finish");
    private final By loading     = By.id("loading");

    public DynamicLoadingPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(PAGE_URL);
    }

    public void clickStart() {
        driver.findElement(startButton).click();
    }

    public String waitForResultText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(finishText));
        return driver.findElement(finishText).getText();
    }

    public boolean isResultDisplayed() {
        return driver.findElement(finishText).isDisplayed();
    }

    public boolean isLoadingDisplayed() {
        return driver.findElement(loading).isDisplayed();
    }

    public void waitUntilLoadingVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loading));
    }

    public void waitUntilLoadingInvisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loading));
    }
}
