package iscteiul.ista.ficha5.part2.samplerDataInput.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;

import iscteiul.ista.ficha5.part2.samplerDataInput.pages.SamplerDataInputPage;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Suite de Testes: Sampler 6 - Data Input (Textual)")
public class SamplerDataInputPageTest {

    private SamplerDataInputPage page;

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1366x768";
    }

    @Test
    void deveAcederAoComponenteDataInput() throws InterruptedException {
        new SamplerDataInputPage()
                .openSampler()
                .navigateToDataInput()
                .selectTextFieldComponent();
                //.shouldShowButtonExample()
                //.clickExampleButton()
                //.shouldShowButtonClickedMessage();

        Thread.sleep(3000); // pausa final opcional
    }
}