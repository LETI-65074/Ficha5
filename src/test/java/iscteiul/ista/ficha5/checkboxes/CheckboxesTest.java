package iscteiul.ista.ficha5.checkboxes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class CheckboxesTest {

    private WebDriver driver;
    private CheckboxesPage checkboxesPage;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        checkboxesPage = new CheckboxesPage(driver);
        checkboxesPage.open();

        // tempo para veres a página
        pause(1000);
    }

    @AfterEach
    void tearDown() {
        // tempo para veres o estado final
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
     * No site, por defeito: primeira checkbox desmarcada, segunda marcada.
     */
    @Test
    void estadoInicialDasCheckboxes() {
        boolean first = checkboxesPage.isFirstChecked();
        boolean second = checkboxesPage.isSecondChecked();

        pause(1000);

        assertFalse(first, "A primeira checkbox devia começar desmarcada");
        assertTrue(second, "A segunda checkbox devia começar marcada");
    }

    /**
     * Teste 2: conseguimos marcar e desmarcar a primeira checkbox.
     */
    @Test
    void marcarEDesmarcarPrimeiraCheckbox() {
        // garantir estado inicial conhecido
        checkboxesPage.uncheckFirst();
        pause(500);
        assertFalse(checkboxesPage.isFirstChecked());

        // marcar
        checkboxesPage.checkFirst();
        pause(800);
        assertTrue(checkboxesPage.isFirstChecked(), "A primeira checkbox devia ficar marcada");

        // desmarcar de novo
        checkboxesPage.uncheckFirst();
        pause(800);
        assertFalse(checkboxesPage.isFirstChecked(), "A primeira checkbox devia voltar a ficar desmarcada");
    }

    /**
     * Teste 3: conseguimos deixar as duas checkboxes marcadas.
     */
    @Test
    void marcarAsDuasCheckboxes() {
        // garantir as duas marcadas
        checkboxesPage.checkFirst();
        checkboxesPage.checkSecond();

        pause(1000);

        assertTrue(checkboxesPage.isFirstChecked(), "Primeira checkbox devia estar marcada");
        assertTrue(checkboxesPage.isSecondChecked(), "Segunda checkbox devia estar marcada");
    }
}
