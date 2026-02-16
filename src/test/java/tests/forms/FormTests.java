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
        // отключил прямой переход, т.к. выдаёт ошибку
//        open("/automation-practice-form");
        open("/");
        $(byText("Forms")).click();
        $(byText("Practice Form")).click();
    }

    @Test
    void successOnlyRequiredFieldsTest() {
        // Заполняем только необходимые поля
        $("#firstName").setValue("John");
        $("#lastName").setValue("Cena");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("1234567890");

        // Кликаем по кнопке "Submit"
        $("#submit").click();

        // Проверяем результат
        $(".modal-header").shouldHave(text("Thanks for submitting the form"));
        $(".modal-body").$(byText("Student Name")).parent().shouldHave(text("John " + "Cena"));
        $(".modal-body").$(byText("Gender")).parent().shouldHave(text("Male"));
        $(".modal-body").$(byText("Mobile")).parent().shouldHave(text("1234567890"));
    }

    @Test
    void successAllFieldTest() {
        // Заполняем только необходимые поля
        $("#firstName").setValue("Hulk");
        $("#lastName").setValue("Hogan");
        $("#userEmail").setValue("hulkhogan@wwe.com");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("1234567890");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").$(byText("1953")).click();
        $(".react-datepicker__month-select").$(byText("August")).click();
        $(".react-datepicker__month").$(byText("11")).click();
        $("#subjectsInput").setValue("English").pressEnter();
        $("#hobbies-checkbox-1").click();
        $("#uploadPicture").uploadFromClasspath("hogan.jpg");
        $("#currentAddress").setValue("Клируотер, Флорида, США (пригород Тампы)");
        $("#state").click();
        $("#state").$(byText("NCR")).click();
        $("#city").click();
        $("#city").$(byText("Delhi")).click();

        // Кликаем по кнопке "Submit"
        $("#submit").click();

        // Проверяем результат
        $(".modal-header").shouldHave(text("Thanks for submitting the form"));
        $(".modal-body").$(byText("Student Name")).parent().shouldHave(text("Hulk " + "Hogan"));
        $(".modal-body").$(byText("Student Email")).parent().shouldHave(text("hulkhogan@wwe.com"));
        $(".modal-body").$(byText("Gender")).parent().shouldHave(text("Male"));
        $(".modal-body").$(byText("Mobile")).parent().shouldHave(text("1234567890"));
        $(".modal-body").$(byText("Date of Birth")).parent().shouldHave(text("11 August,1953"));
        $(".modal-body").$(byText("Subjects")).parent().shouldHave(text("English"));
        $(".modal-body").$(byText("Hobbies")).parent().shouldHave(text("Sports"));
        $(".modal-body").$(byText("Picture")).parent().shouldHave(text("hogan.jpg"));
        $(".modal-body").$(byText("Address")).parent().shouldHave(text("Клируотер, Флорида, США (пригород Тампы)"));
        $(".modal-body").$(byText("State and City")).parent().shouldHave(text("NCR Delhi"));


    }

    @Test
    void errorFieldTest() {
        // без заполнения полей сразу кликнем по кнопке Submit
        $("#submit").click();

        // Проверяем результат
        $(".modal-header").shouldNot(visible);
    }

}
