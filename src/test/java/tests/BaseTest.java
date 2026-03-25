package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeAll
    static void setUp() {

        String browser = System.getProperty("browser", "chrome");
        String browserSize = System.getProperty("browserSize", "1920x1080");
        String browserVersion = System.getProperty("browserVersion", "127");
        boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        String baseUrl = System.getProperty("baseUrl");

        String loginSelenoid =  System.getProperty("loginSelenoid");
        String passwordSelenoid =  System.getProperty("passwordSelenoid");
        String urlSelenoid = System.getProperty("urlSelenoid");


        Configuration.browser = browser;
        Configuration.browserVersion = browserVersion;
        Configuration.browserSize = browserSize;
        Configuration.baseUrl = baseUrl;
//        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.pageLoadTimeout = 40000; // увеличил для "прогрева" при первом старте, из-за возможных нюансов с ВПН
        Configuration.headless = isHeadless;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
        Configuration.remote = "https://" + loginSelenoid + ":" + passwordSelenoid + "@" + urlSelenoid;
//        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

        closeWebDriver();

    }
}
