package tests.AllureTests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

@DisplayName("Тесты в разных стилях с отчетами в Аллюр")
@Tag("Allure")
public class AllureReportsTests {

    private static String REPOSITORY = "VicKing1703/qa_guru_40_demoqa_tests";
    private static String ISSUE = "First issues";

    @Test
    @DisplayName("Чистый Selenide (с Listener)")
    public void issueSearchSelenideTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");

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
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу GitHub", () -> {
            open("https://github.com");
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
        SelenideLogger.addListener("allure", new AllureSelenide());

        step.openMainPage();
        step.searchForRepository(REPOSITORY);
        step.searchForIssue(REPOSITORY);
        step.openIssuePage(ISSUE);
        step.findIssues(ISSUE);

    }
}
