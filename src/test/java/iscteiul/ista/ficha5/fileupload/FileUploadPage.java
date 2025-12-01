package iscteiul.ista.ficha5.fileupload;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FileUploadPage {

    private final WebDriver driver;

    private static final String PAGE_URL =
            "https://the-internet.herokuapp.com/upload";

    private final By fileInput     = By.id("file-upload");
    private final By uploadButton  = By.id("file-submit");
    private final By uploadedFiles = By.id("uploaded-files");
    private final By uploadedHeader = By.tagName("h3"); // "File Uploaded!"

    public FileUploadPage(WebDriver driver) {
        this.driver = driver;
    }

    /** Abre a página de upload do HerokuApp. */
    public void open() {
        driver.get(PAGE_URL);
    }

    /**
     * Faz upload de um ficheiro enviando o caminho absoluto para o input
     * e carregando no botão de submit.
     */
    public void uploadFile(String absolutePath) {
        driver.findElement(fileInput).sendKeys(absolutePath);
        driver.findElement(uploadButton).click();
    }

    /** Espera até a área de ficheiros enviados estar visível. */
    public void waitForUploadResult() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(uploadedFiles));
    }

    /** Devolve o nome do ficheiro apresentado após o upload. */
    public String getUploadedFileName() {
        return driver.findElement(uploadedFiles).getText().trim();
    }

    /** Devolve o texto do cabeçalho (normalmente "File Uploaded!"). */
    public String getHeaderText() {
        return driver.findElement(uploadedHeader).getText().trim();
    }

    /** Verifica se o cabeçalho de sucesso está visível. */
    public boolean isSuccessHeaderDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(uploadedHeader));
        return driver.findElement(uploadedHeader).isDisplayed();
    }
}
