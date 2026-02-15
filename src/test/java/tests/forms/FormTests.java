package tests.forms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FormTests extends BaseTest {

    @BeforeEach
    void openRightURLPath() {
        open("/automation-practice-form/");
    }

    @Test
    void successOnlyRequiredFieldsTest() {
        // Заполняем только необходимые поля
        $("[data-testid=ClearIcon]").click();
        $("input[data-testid='firstName']").setValue("John");
        $("input[data-testid='lastName']").setValue("Cena");
        $("input[data-testid='email']").setValue("johncena@wwe.com");
        $("input[data-testid='phone']").setValue("8001234567");
        $("input[value='Male']").click();

        // Кликаем по кнопке "Submit"
        $("button[type='submit']").scrollTo().click();

        // Проверяем результат
        $(".MuiTypography-h4.css-rq8zac").scrollTo().shouldHave(text("Thank you for submitting the form"));
        $x("//div[contains(@class,'css-1gya3ze')][.//p[normalize-space()='firstName']]/div[2]//p")
                .shouldHave(text("John"));
        $x("//div[contains(@class,'css-1gya3ze')][.//p[normalize-space()='lastName']]/div[2]//p")
                .shouldHave(text("Cena"));
        $x("//div[contains(@class,'css-1gya3ze')][.//p[normalize-space()='email']]/div[2]//p")
                .shouldHave(text("johncena@wwe.com"));
        $x("//div[contains(@class,'css-1gya3ze')][.//p[normalize-space()='gender']]/div[2]//p")
                .shouldHave(text("Male"));
        $x("//div[contains(@class,'css-1gya3ze')][.//p[normalize-space()='phone']]/div[2]//p")
                .shouldHave(text("+18001234567"));
    }

    @Test
    void successAllFieldTest() {
        // Заполняем все поля
        $("[data-testid=ClearIcon]").click();
        $("input[data-testid='firstName']").setValue("John");
        $("input[data-testid='lastName']").setValue("Cena");
        $("input[data-testid='email']").setValue("johncena@wwe.com");
        $("input[data-testid='phone']").setValue("8001234567");
        $x("//label[normalize-space()='Language']/following::div[@role='combobox'][1]").click();
        $x("//li[@role='option' and @data-value='English']").click();
        $("input[data-testid='dateOfBirth']").click();
        $("input[data-testid='dateOfBirth']").setValue("01021900");
        $("input[value='Male']").click();

        // Кликаем по кнопке "Submit"
        $("button[type='submit']").scrollTo().click();

        // Проверяем результат
        $(".MuiTypography-h4.css-rq8zac").scrollTo().shouldHave(text("Thank you for submitting the form"));
        $x("//div[contains(@class,'css-1gya3ze')][.//p[normalize-space()='firstName']]/div[2]//p")
                .shouldHave(text("John"));
        $x("//div[contains(@class,'css-1gya3ze')][.//p[normalize-space()='lastName']]/div[2]//p")
                .shouldHave(text("Cena"));
        $x("//div[contains(@class,'css-1gya3ze')][.//p[normalize-space()='email']]/div[2]//p")
                .shouldHave(text("johncena@wwe.com"));
        $x("//div[contains(@class,'css-1gya3ze')][.//p[normalize-space()='gender']]/div[2]//p")
                .shouldHave(text("Male"));
        $x("//div[contains(@class,'css-1gya3ze')][.//p[normalize-space()='phone']]/div[2]//p")
                .shouldHave(text("+18001234567"));
    }

    @Test
    void errorFieeldTest() {

    }

}
