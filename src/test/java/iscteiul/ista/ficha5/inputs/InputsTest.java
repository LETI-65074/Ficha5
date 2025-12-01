package iscteiul.ista.ficha5.inputs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class InputsTest {

    private WebDriver driver;
    private InputsPage inputsPage;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        inputsPage = new InputsPage(driver);
        inputsPage.open();

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
     * O input deve começar vazio.
     */
    @Test
    void estadoInicialInputVazio() {
        String value = inputsPage.getValue();

        pause(800);

        assertTrue(value == null || value.isEmpty(),
                "O input devia começar vazio");
    }

    /**
     * Teste 2: escrever um número positivo (ex.: 123).
     */
    @Test
    void escreverNumeroPositivo() {
        inputsPage.typeValue("123");

        pause(800);

        String value = inputsPage.getValue();
        assertEquals("123", value);
    }

    /**
     * Teste 3: escrever um número negativo e garantir que o valor muda.
     */
    @Test
    void escreverNumeroNegativo() {
        inputsPage.typeValue("10");
        pause(500);
        String first = inputsPage.getValue();
        assertEquals("10", first);

        inputsPage.typeValue("-5");
        pause(800);
        String second = inputsPage.getValue();

        assertEquals("-5", second);
        assertNotEquals(first, second, "O valor devia ser atualizado para o novo número");
    }
}
