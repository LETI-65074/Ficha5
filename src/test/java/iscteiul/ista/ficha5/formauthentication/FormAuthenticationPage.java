package iscteiul.ista.ficha5.formauthentication;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormAuthenticationPage {

    private final WebDriver driver;

    private static final String PAGE_URL =
            "https://the-internet.herokuapp.com/login";

    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By loginButton   = By.cssSelector("button[type='submit']");
    private final By flashMessage  = By.id("flash");
    private final By secureAreaHeader = By.cssSelector("div.example h2"); // "Secure Area"

    public FormAuthenticationPage(WebDriver driver) {
        this.driver = driver;
    }

    /** Abre a página de login. */
    public void open() {
        driver.get(PAGE_URL);
    }

    /** Preenche username e password e carrega em Login. */
    public void login(String username, String password) {
        driver.findElement(usernameInput).clear();
        driver.findElement(usernameInput).sendKeys(username);

        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);

        driver.findElement(loginButton).click();
    }

    /** Texto da mensagem (sucesso ou erro) que aparece em cima. */
    public String getFlashMessageText() {
        return driver.findElement(flashMessage).getText().trim();
    }

    /** Devolve true se estivermos na página da área segura (/secure). */
    public boolean isAtSecureArea() {
        String currentUrl = driver.getCurrentUrl();
        String headerText = driver.findElement(secureAreaHeader).getText().trim();
        return currentUrl.contains("/secure") && headerText.equals("Secure Area");
    }
}
