package iscteiul.ista.ficha5.samplerbasic.test;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import iscteiul.ista.ficha5.samplerbasic.pages.SamplerBasicPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@Epic("Parte 2")
@Feature("Sampler 8 - Aceder a um componente do tipo Basic features")
public class SamplerBasicTest {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1366x768";
    }

    @Test
    void deveAcederAoComponenteBasicFeaturesEInteragir() throws InterruptedException {
        new SamplerBasicPage()
                .openSamplerHome()
                .goToUserInterfaceSection()
                .openBasicFeaturesSection()
                .verifyBasicFeaturesLoaded()
                .clickExampleButton()
                .verifyInteractionResult();

        Thread.sleep(2000); // pausa final opcional para observação
    }
}

