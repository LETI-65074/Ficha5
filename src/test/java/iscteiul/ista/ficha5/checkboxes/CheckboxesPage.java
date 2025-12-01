package iscteiul.ista.ficha5.checkboxes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckboxesPage {

    private final WebDriver driver;

    private static final String PAGE_URL =
            "https://the-internet.herokuapp.com/checkboxes";

    private final By firstCheckbox  = By.cssSelector("#checkboxes input:nth-of-type(1)");
    private final By secondCheckbox = By.cssSelector("#checkboxes input:nth-of-type(2)");

    public CheckboxesPage(WebDriver driver) {
        this.driver = driver;
    }

    /** Abre a página das checkboxes. */
    public void open() {
        driver.get(PAGE_URL);
    }

    private WebElement getFirstCheckbox() {
        return driver.findElement(firstCheckbox);
    }

    private WebElement getSecondCheckbox() {
        return driver.findElement(secondCheckbox);
    }

    /** Marca a primeira checkbox se ainda não estiver marcada. */
    public void checkFirst() {
        WebElement cb = getFirstCheckbox();
        if (!cb.isSelected()) {
            cb.click();
        }
    }

    /** Desmarca a primeira checkbox se estiver marcada. */
    public void uncheckFirst() {
        WebElement cb = getFirstCheckbox();
        if (cb.isSelected()) {
            cb.click();
        }
    }

    /** Marca a segunda checkbox se ainda não estiver marcada. */
    public void checkSecond() {
        WebElement cb = getSecondCheckbox();
        if (!cb.isSelected()) {
            cb.click();
        }
    }

    /** Desmarca a segunda checkbox se estiver marcada. */
    public void uncheckSecond() {
        WebElement cb = getSecondCheckbox();
        if (cb.isSelected()) {
            cb.click();
        }
    }

    /** Estado da primeira checkbox. */
    public boolean isFirstChecked() {
        return getFirstCheckbox().isSelected();
    }

    /** Estado da segunda checkbox. */
    public boolean isSecondChecked() {
        return getSecondCheckbox().isSelected();
    }
}
