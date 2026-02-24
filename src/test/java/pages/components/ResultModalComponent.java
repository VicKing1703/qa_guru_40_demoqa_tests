package pages.components;

import com.codeborne.selenide.SelenideElement;

import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ResultModalComponent {

    private final SelenideElement modal = $(".modal-content");

    public ResultModalComponent shouldAppear() {
        modal.shouldBe(visible)
                .shouldHave(text("Thanks for submitting the form"));
        return this;
    }

    public ResultModalComponent shouldHaveValue(String label, String value) {
        modal.$(byText(label)).parent().shouldHave(text(value));
        return this;
    }

}
