package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {
    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserVersion = "144";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.pageLoadTimeout = 35000; // увеличил для "прогрева" при первом старте, из-за возможных нюансов с ВПН

    }

    @AfterAll
    static void tearDown() {
        closeWebDriver();
    }
}
