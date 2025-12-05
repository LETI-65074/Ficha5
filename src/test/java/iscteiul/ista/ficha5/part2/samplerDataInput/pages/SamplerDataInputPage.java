package iscteiul.ista.ficha5.part2.samplerDataInput.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class SamplerDataInputPage {

    public SamplerDataInputPage openSampler() {
        open("https://demo.vaadin.com/sampler/");
        dormir(3000);
        return this;
    }

    public SamplerDataInputPage navigateToDataInput(){
        $(byText("User interface"))
                .shouldBe(visible, enabled)
                .click();
        dormir(3000);
        return this;
    }

    public SamplerDataInputPage selectTextFieldComponent() {
        $(byText("Data input"))
                .shouldBe(visible, enabled)
                .click();
        dormir(3000);

        $(byText("Other"))
                .shouldBe(visible, enabled)
                .click();
        dormir(3000);

        $(byText("Color picker"))
                .shouldBe(visible, enabled)
                .click();
        dormir(5000);
        $(".v-button-v-colorpicker").shouldBe(visible).click();

        dormir(7000);

        SelenideElement gradiente = $(".v-colorpicker-gradient").shouldBe(visible);
        actions().moveToElement(gradiente, 50, 50).click().perform();

        dormir(7000);

        // 3. Clicar no botão OK
        $(".v-button.v-widget.v-has-width").shouldHave(text("OK")).click();
        dormir(6000);


        return this;
    }


    private void dormir(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}