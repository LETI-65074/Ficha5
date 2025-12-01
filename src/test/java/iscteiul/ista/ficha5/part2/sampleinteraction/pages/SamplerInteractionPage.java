package iscteiul.ista.ficha5.part2.sampleinteraction.pages;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;

public class SamplerInteractionPage {

    // 1) Abre o site principal do Sampler
    public SamplerInteractionPage openSamplerHome() {
        open("https://demo.vaadin.com/sampler/");
        dormir(3000);
        return this;
    }

    // 2) Clica em "User interface" no ecrã inicial
    public SamplerInteractionPage goToUserInterfaceSection() {
        $(byText("User interface"))
                .shouldBe(visible, enabled)
                .click();
        dormir(3000);
        return this;
    }

    // 3) No menu lateral: Interaction -> Button
    public SamplerInteractionPage openInteractionButtonComponent() {
        $(byText("Interaction"))
                .shouldBe(visible, enabled)
                .click();
        dormir(2000);

        $(byText("Button"))
                .shouldBe(visible, enabled)
                .click();
        dormir(3000);
        return this;
    }

    // 4) Verifica que estamos na página "Button"
    public SamplerInteractionPage shouldShowButtonExample() {
        $("body").shouldHave(text("Button"));
        dormir(2000);
        return this;
    }

    // 5) Clica no botão "Click" do exemplo
    public SamplerInteractionPage clickExampleButton() {
        // procura O botão visível (div) e clica nele
        $$("div.v-button.v-widget")
                .findBy(visible)     // escolhe o que está visível
                .shouldBe(enabled)
                .click();

        dormir(1000);
        return this;
    }

    // 6) Verifica a mensagem "The button was clicked"
    public SamplerInteractionPage shouldShowButtonClickedMessage() {
        $(byText("The button was clicked"))
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
