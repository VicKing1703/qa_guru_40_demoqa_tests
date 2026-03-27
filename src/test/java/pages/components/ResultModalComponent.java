package pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ResultModalComponent {

    private final SelenideElement modal = $(".modal-content");

    @Step("Появилось модальное окно \"Thanks for submitting the form\"")
    public ResultModalComponent shouldAppear() {
        modal.shouldBe(visible)
                .shouldHave(text("Thanks for submitting the form"));
        return this;
    }

    @Step("Проверяем формы в модальном окне параметр \"{label}\" с значением \"{value}\"")
    public ResultModalComponent shouldHaveValue(String label, String value) {
        modal.$(byText(label)).parent().shouldHave(text(value));
        return this;
    }

}
