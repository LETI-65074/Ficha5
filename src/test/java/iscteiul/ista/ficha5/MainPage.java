package iscteiul.ista.ficha5;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

// page_url = https://www.jetbrains.com/
public class MainPage {

    // Card grande "Developer Tools" na homepage (se ainda precisares disto para outro teste)
    public SelenideElement seeDeveloperToolsButton =
            $x("//*[@data-test-marker='Developer Tools']");

    // Botão de pesquisa no header
    public SelenideElement searchButton =
            $("[data-test='site-header-search-action']");

    // Item de menu no topo: botão "Developer Tools" que abre o mega menu
    public SelenideElement toolsMenu =
            $("button[data-test='main-menu-item-action'][aria-label*='Developer Tools']");

    // Link "Find your tool" DENTRO do submenu (este é o que o Chrome mostra com:
    // <a href="/products/" ... data-test="suggestion-link">
    public SelenideElement findYourToolsButton =
            $("a[data-test='suggestion-link']");
}
