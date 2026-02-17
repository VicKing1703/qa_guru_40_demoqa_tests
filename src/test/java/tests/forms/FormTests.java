package tests.forms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static tests.TestData.*;

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
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#genterWrapper").$(byText(sex)).click();
        $("#userNumber").setValue(userNumber);

        // Кликаем по кнопке "Submit"
        $("#submit").scrollTo().click(); // добавил скролл до кнопки

        // Проверяем результат
        $(".modal-header").shouldHave(text("Thanks for submitting the form"));
        $(".modal-body").$(byText("Student Name")).parent()
                .shouldHave(text(firstName + " " + lastName));
        $(".modal-body").$(byText("Gender")).parent().shouldHave(text(sex));
        $(".modal-body").$(byText("Mobile")).parent().shouldHave(text(userNumber));
    }

    @Test
    void successAllFieldTest() {
        // Заполняем только необходимые поля
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $("#genterWrapper").$(byText(sex)).click();
        $("#userNumber").setValue(userNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").$(byText(yearBirth)).click();
        $(".react-datepicker__month-select").$(byText(monthBirth)).click();
        $(".react-datepicker__month").$(byText(dayBirth)).click();
        $("#subjectsInput").setValue(subjects).pressEnter();
        $("#hobbiesWrapper").$(byText(hobbies)).click();
        $("#uploadPicture").uploadFromClasspath(imageName);
        $("#currentAddress").setValue(currentAddress);
        $("#state").click();
        $("#state").$(byText(state)).click();
        $("#city").click();
        $("#city").$(byText(city)).click();

        // Кликаем по кнопке "Submit"
        $("#submit").scrollTo().click(); // добавил скролл до кнопки

        // Проверяем результат
        $(".modal-header").shouldHave(text("Thanks for submitting the form"));
        $(".modal-body").$(byText("Student Name")).parent()
                .shouldHave(text(firstName + " " + lastName));
        $(".modal-body").$(byText("Student Email")).parent().shouldHave(text(userEmail));
        $(".modal-body").$(byText("Gender")).parent().shouldHave(text(sex));
        $(".modal-body").$(byText("Mobile")).parent().shouldHave(text(userNumber));
        $(".modal-body").$(byText("Date of Birth")).parent()
                .shouldHave(text(dayBirth + " " + monthBirth +"," + yearBirth));
        $(".modal-body").$(byText("Subjects")).parent().shouldHave(text(subjects));
        $(".modal-body").$(byText("Hobbies")).parent().shouldHave(text(hobbies));
        $(".modal-body").$(byText("Picture")).parent().shouldHave(text(imageName));
        $(".modal-body").$(byText("Address")).parent().shouldHave(text(currentAddress));
        $(".modal-body").$(byText("State and City")).parent().shouldHave(text(state + " " + city));


    }

    @Test
    void errorFieldTest() {
        // без заполнения полей сразу кликнем по кнопке Submit
        $("#submit").scrollTo().click(); // добавил скролл до кнопки

        // Проверяем результат
        $(".modal-header").shouldNot(visible);
        $("#userForm").shouldHave(cssClass("was-validated"));
        $("#firstName").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("#lastName").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("#userNumber").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $$("input[name='gender']").forEach(
                        it -> it.shouldHave(cssValue(
                                "border-color", "rgb(220, 53, 69)"
                        ))
                );
    }

}
