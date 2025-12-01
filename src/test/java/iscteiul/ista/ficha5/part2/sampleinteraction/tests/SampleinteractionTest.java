package iscteiul.ista.ficha5.part2.sampleinteraction.tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import iscteiul.ista.ficha5.part2.sampleinteraction.pages.SamplerInteractionPage;

@Epic("Parte 2")
@Feature("Sampler 5 - Aceder a um componente do tipo Interaction")
public class SampleinteractionTest {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1366x768";
    }

    @Test
    void deveAcederAoComponenteInteractionButtonEClickar() throws InterruptedException {
        new SamplerInteractionPage()
                .openSamplerHome()
                .goToUserInterfaceSection()
                .openInteractionButtonComponent()
                .shouldShowButtonExample()
                .clickExampleButton()
                .shouldShowButtonClickedMessage();

        Thread.sleep(3000); // pausa final opcional
    }

}
