package iscteiul.ista.ficha5.dynamicloading;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class DynamicLoadingTest {

    private WebDriver driver;
    private DynamicLoadingPage dynamicLoadingPage;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        dynamicLoadingPage = new DynamicLoadingPage(driver);
        dynamicLoadingPage.open();

        // pequena pausa só para veres a página aberta
        pause(1000);
    }

    @AfterEach
    void tearDown() {
        pause(1500); // dá tempo para ver o estado final antes de fechar
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Pausa "artificial" só para conseguir ver o que acontece no browser.
     * NÃO usar isto como mecanismo de sincronização em testes reais.
     */
    private void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Teste 1: cenário principal.
     * Depois de clicar em Start e esperar, deve aparecer o texto "Hello World!".
     */
    @Test
    void quandoCarregoEmStart_apareceHelloWorld() {
        // ver página antes de clicar
        pause(1000);

        dynamicLoadingPage.clickStart();

        // tempo para veres o loading a aparecer
        pause(1500);

        String texto = dynamicLoadingPage.waitForResultText();

        // tempo para veres o "Hello World!" na página
        pause(1500);

        assertEquals("Hello World!", texto);
    }

    /**
     * Teste 2: antes de clicar em Start, o resultado não deve estar visível.
     */
    @Test
    void antesDeCarregarStart_textoNaoEstaVisivel() {
        // ver a página um bocadinho
        pause(1000);

        boolean resultadoVisivel = dynamicLoadingPage.isResultDisplayed();
        assertFalse(resultadoVisivel, "O texto não devia estar visível antes de carregar em Start");

        // mais uma pausa só para poderes olhar para a página
        pause(1000);
    }

    /**
     * Teste 3: o spinner de loading aparece depois de clicar em Start
     * e desaparece quando o carregamento termina.
     */
    @Test
    void loadingApareceEDesapareceDuranteProcessamento() {
        // ver estado inicial
        pause(1000);

        dynamicLoadingPage.clickStart();

        // ver o momento imediatamente depois do clique
        pause(800);

        // aguardar ele ficar visível (sincronização "real")
        dynamicLoadingPage.waitUntilLoadingVisible();
        assertTrue(dynamicLoadingPage.isLoadingDisplayed(), "O spinner devia estar visível depois de clicar em Start");

        // mais um bocadinho para ver o spinner
        pause(1200);

        // aguardar ele desaparecer
        dynamicLoadingPage.waitUntilLoadingInvisible();
        assertFalse(dynamicLoadingPage.isLoadingDisplayed(), "O spinner devia desaparecer quando termina o carregamento");

        // ver o estado final (Hello World visível)
        pause(1500);
    }
}
