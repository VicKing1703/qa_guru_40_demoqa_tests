package tests.AllureTests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

@DisplayName("Тесты в разных стилях с отчетами в Аллюр")
@Tag("Allure")
public class AllureReportsTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://github.com";
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void afterAll() {
        closeWebDriver();
    }

    private static String REPOSITORY = "VicKing1703/qa_guru_40_demoqa_tests";
    private static String ISSUE = "First issues";

    @Test
    @DisplayName("Чистый Selenide (с Listener)")
    public void issueSearchSelenideTest() {

        open("/");

        $(".input-button").click();
        $(".QueryBuilder-Input").sendKeys(REPOSITORY);
        $(".QueryBuilder-Input").submit();

        $(linkText(REPOSITORY)).click();
        $("#issues-tab").click();
        $(withText(ISSUE)).should(Condition.exist);
    }

    @Test
    @DisplayName("Лямбда шаги через step (name, () -> {})")
    public void issueSearchLambdaTest() {

        step("Открываем главную страницу GitHub", () -> {
            open("/");
        });
        step("Через строку поиска ищем репозиторий '" + REPOSITORY + "'", () -> {
            $(".input-button").click();
            $(".QueryBuilder-Input").sendKeys(REPOSITORY);
            $(".QueryBuilder-Input").submit();
        });

        step("Кликаем по ссылке репозитория '" + REPOSITORY +"'", () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Открываем таб Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем наличие Issue с текстом '" + ISSUE + "'", () -> {
            $(withText(ISSUE)).should(Condition.exist);
        });
    }

    @Test
    @DisplayName("Шаги с аннотацией @Step")
    public void issueSearchStepTest() {
        WebSteps step = new WebSteps();

        step.openMainPage();
        step.searchForRepository(REPOSITORY);
        step.searchForIssue(REPOSITORY);
        step.openIssuePage(ISSUE);
        step.findIssues(ISSUE);

    }
}
