package iscteiul.ista.ficha5.samplerbasic.pages;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;

import com.codeborne.selenide.SelenideElement;


public class SamplerBasicPage {

    // 1) Abre o site principal do Sampler
    public SamplerBasicPage openSamplerHome() {
        open("https://demo.vaadin.com/sampler/");
        dormir(3000);
        return this;
    }

    // 2) Clica em "User interface"
    public SamplerBasicPage goToUserInterfaceSection() {
        // usa byText (procura pelo texto visível)
        $(byText("User interface"))
                .shouldBe(visible, enabled)
                .click();
        dormir(2000);
        return this;
    }

    // 3) No menu lateral: Basic features
    public SamplerBasicPage openBasicFeaturesSection() {
        $$("div.gwt-HTML.titlelabel.transitioned")
                .findBy(matchText("(?i)Basic\\s*Features"))
                .shouldBe(visible)
                .click();

        dormir(2500);

        // Clicar em "Keyboard Shortcuts"
        $$("div.gwt-HTML.titlelabel.transitioned")
                .findBy(matchText("(?i)Keyboard\\s*Shortcuts"))
                .shouldBe(visible)
                .click();

        dormir(3000);
        return this;
    }

    // 4) Verifica que a secção "Basic features" está carregada
    public SamplerBasicPage verifyBasicFeaturesLoaded() {
        $("body").shouldHave(text("Keyboard Shortcuts"));
        dormir(2000);
        return this;
    }

    // 5) Interage com um componente básico de exemplo (clica no botão se houver)
    public SamplerBasicPage clickExampleButton() {
        // procura O botão visível (div) e clica nele
        $$("div.v-button.v-widget")
                .findBy(visible)     // escolhe o que está visível
                .shouldBe(enabled)
                .click();

        dormir(1000);
        return this;

    }

    // 6) Verifica resultado da interação (ex.: mensagem "The button was clicked" ou outra indicação)
    public SamplerBasicPage verifyInteractionResult() {
        // tenta por texto exacto primeiro
        $(byText("Button L clicked"))
                .shouldBe(visible);
        dormir(2000);
        return this;
    }

    // utilitário para todos os Thread.sleep
    private void dormir(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}