package iscteiul.ista.ficha5;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPageTest {

    // Instanciação da Page Object
    private final MainPage mainPage = new MainPage();

    @BeforeAll
    public static void globalSetup() {
        // Configurações globais do browser e logs
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void init() {
        // Garante que a página inicial é carregada antes de cada teste
        open("https://www.jetbrains.com/");
    }

    @Test
    @DisplayName("Teste de interação com a barra de pesquisa")
    public void verifySearchFunction() throws InterruptedException {
        // 1. Abrir a barra de pesquisa
        mainPage.btnSearchHeader.shouldBe(visible).click();

        // 2. Localizar o input e inserir texto
        var searchInput = $("input[data-test-id='search-input']");
        searchInput.shouldBe(visible).setValue("Selenium");

        // 3. Validar se o texto foi inserido corretamente
        searchInput.shouldHave(attribute("value", "Selenium"));

        // Pausa para validação visual (remover em produção)
        Thread.sleep(2000);
    }

    @Test
    @DisplayName("Teste de abertura do menu de Ferramentas")
    public void checkToolsMenuExpansion() throws InterruptedException {
        // Clicar no menu superior
        mainPage.menuDevTools.shouldBe(visible).click();

        // Verificar se o link "All IDEs" aparece no submenu
        $("a[href='/ides/'][data-test='main-submenu-item-link']").shouldBe(visible);

        // Aguarda brevemente para observar o menu aberto
        Thread.sleep(3000);
    }

    @Test
    @DisplayName("Navegação completa para a página de produtos")
    public void navigateToProducts() throws InterruptedException {
        // Fluxo de navegação através dos botões principais
        mainPage.btnMainDevTools.shouldBe(visible).click();
        mainPage.btnFindTools.shouldBe(visible).click();

        // Validações da página de destino
        $("#products-page").shouldBe(visible);

        String pageTitle = Selenide.title();
        assertEquals("All Developer Tools and Products by JetBrains", pageTitle);

        Thread.sleep(3000);
    }
}