package iscteiul.ista.ficha5.dropdown;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropdownPage {

    private final WebDriver driver;

    private static final String PAGE_URL =
            "https://the-internet.herokuapp.com/dropdown";

    private final By dropdown = By.id("dropdown");

    public DropdownPage(WebDriver driver) {
        this.driver = driver;
    }

    /** Abre a página do dropdown. */
    public void open() {
        driver.get(PAGE_URL);
    }

    private Select getSelect() {
        WebElement element = driver.findElement(dropdown);
        return new Select(element);
    }

    /** Seleciona uma opção pelo texto visível (ex.: "Option 1"). */
    public void selectByVisibleText(String text) {
        getSelect().selectByVisibleText(text);
    }

    /** Seleciona uma opção pelo value (ex.: "1", "2"). */
    public void selectByValue(String value) {
        getSelect().selectByValue(value);
    }

    /** Devolve o texto da opção atualmente selecionada. */
    public String getSelectedOptionText() {
        return getSelect().getFirstSelectedOption().getText().trim();
    }
}
