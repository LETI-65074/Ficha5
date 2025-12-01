package iscteiul.ista.ficha5.inputs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InputsPage {

    private final WebDriver driver;

    private static final String PAGE_URL =
            "https://the-internet.herokuapp.com/inputs";

    private final By numberInput = By.tagName("input");

    public InputsPage(WebDriver driver) {
        this.driver = driver;
    }

    /** Abre a página dos inputs numéricos. */
    public void open() {
        driver.get(PAGE_URL);
    }

    private WebElement getInput() {
        return driver.findElement(numberInput);
    }

    /** Limpa o campo. */
    public void clear() {
        getInput().clear();
    }

    /** Introduz o valor indicado no input. */
    public void typeValue(String value) {
        WebElement input = getInput();
        input.clear();
        input.sendKeys(value);
    }

    /** Devolve o value atual do input. */
    public String getValue() {
        return getInput().getAttribute("value");
    }
}
