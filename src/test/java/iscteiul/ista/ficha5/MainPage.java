package iscteiul.ista.ficha5;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

// Mapeamento dos elementos da página principal da JetBrains
public class MainPage {

    // Botão de destaque "Developer Tools" (XPath usado para maior especificidade)
    public SelenideElement btnMainDevTools = $x("//*[@data-test-marker='Developer Tools']");

    // Ícone da lupa no cabeçalho do site
    public SelenideElement btnSearchHeader = $("[data-test='site-header-search-action']");

    // Menu principal: Acesso às ferramentas de desenvolvimento
    public SelenideElement menuDevTools = $("button[data-test='main-menu-item-action'][aria-label*='Developer Tools']");

    // Link para encontrar ferramentas (dentro do submenu)
    public SelenideElement btnFindTools = $("a[data-test='suggestion-link']");
}