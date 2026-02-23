package pages.components;


import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.cssValue;

public class ValidationComponent {

    private static final String errorRedBorderColor = "rgb(220, 53, 69)";
    private static final String errorRedColor = "rgba(220, 53, 69, 1)";

    public ValidationComponent shouldHaveRedBorder(SelenideElement element) {
        element.shouldHave(cssValue("border-color", errorRedBorderColor));
        return this;
    }

    public ValidationComponent shouldHaveRedText(SelenideElement element) {
        element.shouldHave(cssValue("color", errorRedColor));
        return this;
    }
}
