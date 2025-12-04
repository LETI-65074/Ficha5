package iscteiul.ista.ficha5.dropdown;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class DropdownPageTest {

    private WebDriver driver;
    private DropdownPage dropdownPage;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        dropdownPage = new DropdownPage(driver);
        dropdownPage.open();

        // ver a página
        pause(1000);
    }

    @AfterEach
    void tearDown() {
        // ver o estado final
        pause(1500);
        if (driver != null) {
            driver.quit();
        }
    }

    /** Pausa só para demo visual. */
    private void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Teste 1: estado inicial.
     * No site, por defeito: "Please select an option".
     */
    @Test
    void estadoInicialDoDropdown() {
        String selected = dropdownPage.getSelectedOptionText();

        pause(800);

        assertEquals("Please select an option", selected);
    }

    /**
     * Teste 2: selecionar Option 1.
     */
    @Test
    void selecionarOption1() {
        dropdownPage.selectByVisibleText("Option 1");

        pause(800);

        String selected = dropdownPage.getSelectedOptionText();
        assertEquals("Option 1", selected);
    }

    /**
     * Teste 3: selecionar Option 2 e garantir que é diferente da Option 1.
     */
    @Test
    void selecionarOption2() {
        dropdownPage.selectByVisibleText("Option 2");

        pause(800);

        String selected = dropdownPage.getSelectedOptionText();
        assertEquals("Option 2", selected);
        assertNotEquals("Option 1", selected);
    }
}
