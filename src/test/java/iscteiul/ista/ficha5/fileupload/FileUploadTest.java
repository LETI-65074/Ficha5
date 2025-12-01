package iscteiul.ista.ficha5.fileupload;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class FileUploadTest {

    private WebDriver driver;
    private FileUploadPage fileUploadPage;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        fileUploadPage = new FileUploadPage(driver);
        fileUploadPage.open();

        // tempo para veres a página inicial
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

    /**
     * Pausa artificial para conseguires ver o que acontece no browser.
     * Não é para usar como mecanismo de sincronização "a sério".
     */
    private void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /** Caminho absoluto para um ficheiro de teste em src/test/resources. */
    private String getTestFilePath(String fileName) {
        Path path = Paths.get("src", "test", "resources", fileName).toAbsolutePath();
        return path.toString();
    }

    /**
     * Teste 1: upload simples.
     * Depois de enviar o ficheiro, o nome deve aparecer na área de resultado.
     */
    @Test
    void uploadSimples_mostraNomeDoFicheiro() {
        String fileName = "test-upload.txt"; // cria este ficheiro em src/test/resources
        String absolutePath = getTestFilePath(fileName);

        // ver antes de enviar
        pause(800);

        fileUploadPage.uploadFile(absolutePath);

        // ver enquanto a página muda
        pause(1000);

        fileUploadPage.waitForUploadResult();
        String uploadedName = fileUploadPage.getUploadedFileName();

        // ver nome na página
        pause(1000);

        assertEquals(fileName, uploadedName);
    }

    /**
     * Teste 2: depois do upload, deve aparecer o cabeçalho "File Uploaded!".
     */
    @Test
    void uploadMostraMensagemDeSucesso() {
        String fileName = "test-upload.txt";
        String absolutePath = getTestFilePath(fileName);

        pause(800);

        fileUploadPage.uploadFile(absolutePath);

        fileUploadPage.waitForUploadResult();
        boolean headerVisivel = fileUploadPage.isSuccessHeaderDisplayed();
        String headerText = fileUploadPage.getHeaderText();

        pause(1000);

        assertTrue(headerVisivel, "O cabeçalho de sucesso devia estar visível");
        assertEquals("File Uploaded!", headerText);
    }

    /**
     * Teste 3: fazer dois uploads seguidos com ficheiros diferentes.
     * (precisas de criar também test-upload-2.txt em src/test/resources)
     */
    @Test
    void doisUploadsComFicheirosDiferentes() {
        String file1 = "test-upload.txt";
        String file2 = "test-upload-2.txt";

        // Primeiro upload
        fileUploadPage.uploadFile(getTestFilePath(file1));
        fileUploadPage.waitForUploadResult();
        String uploaded1 = fileUploadPage.getUploadedFileName();
        assertEquals(file1, uploaded1);

        pause(1200);

        // Voltar à página de upload para um segundo envio
        fileUploadPage.open();
        pause(800);

        // Segundo upload
        fileUploadPage.uploadFile(getTestFilePath(file2));
        fileUploadPage.waitForUploadResult();
        String uploaded2 = fileUploadPage.getUploadedFileName();

        pause(1200);

        assertEquals(file2, uploaded2);
        assertNotEquals(uploaded1, uploaded2, "Os ficheiros enviados deveriam ser diferentes");
    }
}
