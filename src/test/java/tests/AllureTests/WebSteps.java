package tests.AllureTests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {

    @Step("Открываем главную страницу GitHub")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Через строку поиска ищем репозиторий '{repository}'")
    public void searchForRepository(String repository) {
        $(".input-button").click();
        $(".QueryBuilder-Input").sendKeys(repository);
        $(".QueryBuilder-Input").submit();
    }

    @Step("Кликаем по ссылке репозитория '{repository}'")
    public void searchForIssue(String repository) {
        $(linkText(repository)).click();
    }

    @Step("Открываем таб Issues")
    public void openIssuePage(String repository) {
        $("#issues-tab").click();
    }

    @Step("Проверяем наличие Issue с текстом '{issues}'")
    public void findIssues(String issues) {
        $(withText(issues)).should(Condition.exist);
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}

