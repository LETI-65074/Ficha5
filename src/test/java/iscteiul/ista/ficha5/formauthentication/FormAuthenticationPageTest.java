package iscteiul.ista.ficha5.formauthentication;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class FormAuthenticationPageTest {

    private WebDriver driver;
    private FormAuthenticationPage formPage;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        formPage = new FormAuthenticationPage(driver);
        formPage.open();

        // ver a página de login
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
     * Teste 1: login com credenciais válidas deve entrar na área segura
     * e mostrar a mensagem de sucesso.
     */
    @Test
    void loginComCredenciaisValidas() {
        String username = "tomsmith";
        String password = "SuperSecretPassword!";

        formPage.login(username, password);

        pause(1000);

        String flash = formPage.getFlashMessageText();
        boolean atSecure = formPage.isAtSecureArea();

        assertTrue(flash.contains("You logged into a secure area!"));
        assertTrue(atSecure, "Devíamos estar na área segura após login válido");
    }

    /**
     * Teste 2: password errada deve mostrar mensagem de erro
     * "Your password is invalid!" e não deve ir para a área segura.
     */
    @Test
    void loginComPasswordInvalida() {
        String username = "tomsmith";
        String passwordErrada = "senhaErrada";

        formPage.login(username, passwordErrada);

        pause(1000);

        String flash = formPage.getFlashMessageText();
        boolean atSecure = formPage.isAtSecureArea();

        assertTrue(flash.contains("Your password is invalid!"));
        assertFalse(atSecure, "Não devíamos entrar na área segura com password errada");
    }

    /**
     * Teste 3: username errado deve mostrar mensagem de erro
     * "Your username is invalid!".
     */
    @Test
    void loginComUsernameInvalido() {
        String usernameErrado = "utilizadorErrado";
        String password = "SuperSecretPassword!";

        formPage.login(usernameErrado, password);

        pause(1000);

        String flash = formPage.getFlashMessageText();
        boolean atSecure = formPage.isAtSecureArea();

        assertTrue(flash.contains("Your username is invalid!"));
        assertFalse(atSecure, "Não devíamos entrar na área segura com username errado");
    }
}
