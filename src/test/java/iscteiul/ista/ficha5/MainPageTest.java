package iscteiul.ista.ficha5;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainPageTest {
    MainPage mainPage = new MainPage();

@BeforeAll    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

@BeforeEach    public void setUp() {
        open("https://www.jetbrains.com/");
    }

    @Test
    public void search() throws InterruptedException {
        // Abre o campo de pesquisa no header
        mainPage.searchButton.shouldBe(visible).click();

        // Seleciona o input correto
        SelenideElement searchInput = $("input[data-test-id='search-input']")
                .shouldBe(visible);

        // Escreve "Selenium"
        searchInput.setValue("Selenium");

        // Verifica que o valor foi mesmo escrito (aqui o site ainda não limpou)
        searchInput.shouldHave(attribute("value", "Selenium"));

        // (Opcional) Se quiseres mesmo lançar a pesquisa:
        // searchInput.pressEnter();

        // Pausa só para conseguires ver o que aconteceu antes do fecho
        Thread.sleep(2000); // NÃO usar thread.wait()
    }


    @Test
    public void toolsMenu() throws InterruptedException {
        // Clica no botão "Developer Tools"
        mainPage.toolsMenu.shouldBe(visible).click();

        // Verifica que o link "All IDEs" do submenu ficou visível
        $("a[href='/ides/'][data-test='main-submenu-item-link']")
                .shouldBe(visible);

        // Só para conseguires ver o submenu aberto
        Thread.sleep(3000);
    }





    @Test
    public void navigationToAllTools() throws InterruptedException {
        mainPage.seeDeveloperToolsButton.shouldBe(visible).click();
        mainPage.findYourToolsButton.shouldBe(visible).click();
        $("#products-page").shouldBe(visible);
        assertEquals("All Developer Tools and Products by JetBrains", Selenide.title());
        Thread.sleep(3000);
    }


}
