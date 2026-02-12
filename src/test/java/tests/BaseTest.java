package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {
    @BeforeAll
    static void beforeAll() {
        Configuration.browser = "chrome";
        Configuration.browserVersion = "145.0.7632.46";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://app.qa.guru";
//        Configuration.timeout = 10000;
    }

    @AfterAll
    static void tearDown() {
        closeWebDriver();
    }
}
